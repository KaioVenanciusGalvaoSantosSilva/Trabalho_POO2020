package Estoque;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstoqueDAOImpl implements EstoqueDAO {
	private static final String URL = "jdbc:mariadb://localhost:3306/saude?allowMultiQueries=true";
	private static final String USER = "root";
	private static final String PASS = "";

	public EstoqueDAOImpl() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<EntityEstoque> pesquisarPorNome(String nomeProduto) {
		List<EntityEstoque> lista = new ArrayList<>();
		try {
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			String sql = "Select idProduto, nomeProduto, valorUnitario, principioAtivo, quantidadeAtual, dataValidade, principioAtivo, nomeFornecedor from produto, estoque , entrada where idProduto = idProd And idEnt = idProduto AND nomeProduto like ?";

			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, "%" + nomeProduto + "%");
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				EntityEstoque entrada = new EntityEstoque();
				entrada.setIdProduto(rs.getInt("idProduto"));
				entrada.setNomeProduto(rs.getString("nomeProduto"));
				entrada.setPrincipioAtivo(rs.getString("principioAtivo"));
				entrada.setValorUnitario(rs.getDouble("valorUnitario"));
				entrada.setQuantidadeAtual(rs.getInt("quantidadeAtual"));
				entrada.setDataValidade(rs.getDate("dataValidade").toLocalDate());
				entrada.setNomeFornecedor(rs.getString("nomeFornecedor"));

				lista.add(entrada);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
}