package EntradaProdutos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EntradaDAOImpl implements EntradaDAO {
	private static final String URL = "jdbc:mariadb://localhost:3306/saude?allowMultiQueries=true";
	private static final String USER = "root";
	private static final String PASS = "";

	public EntradaDAOImpl() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void adicionar(EntityEntrada entrada) {

		try {
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			System.out.println("Conectado com Sucesso");
			String sql = "INSERT INTO entrada (idEntrada, nomeProd, nomeFornecedor , lote ,	valorTotal, 	"
					+ " quantidade, dataEntrada, dataValidade ) " + "VALUES  (0, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, entrada.getNomeProduto());
			stm.setString(2, entrada.getNomeFornecedor());
			stm.setString(3, entrada.getLote());
			stm.setDouble(4, entrada.getValorTotal());
			stm.setInt(5, entrada.getQuantidade());
			stm.setDate(6, java.sql.Date.valueOf(entrada.getDataEntrada()));
			stm.setDate(7, java.sql.Date.valueOf(entrada.getDataValidade()));

			stm.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alterar(EntityEntrada entrada) {
		try {
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			System.out.println("Conectado com Sucesso");

			String sql = "UPDATE entrada set nomeProd = ?, nomeFornecedor = ? , lote = ? ,	valorTotal = ?, 	"
					+ " quantidade = ?, dataEntrada = ?, 	dataValidade = ?  where idEntrada = ? ";

			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, entrada.getNomeProduto());
			stm.setString(2, entrada.getNomeFornecedor());
			stm.setString(3, entrada.getLote());
			stm.setDouble(4, entrada.getValorTotal());
			stm.setInt(5, entrada.getQuantidade());
			stm.setDate(6, java.sql.Date.valueOf(entrada.getDataEntrada()));
			stm.setDate(7, java.sql.Date.valueOf(entrada.getDataValidade()));
			stm.setInt(8, entrada.getIdEntrada());
			stm.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<EntityEntrada> pesquisarPorNome(String nomeProd) {
		List<EntityEntrada> lista = new ArrayList<>();
		try {
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			System.out.println("Conectado com Sucesso");
			String sql = "SELECT * FROM entrada WHERE nomeProd like ?";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, "%" + nomeProd + "%");
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				EntityEntrada entrada = new EntityEntrada();
				entrada.setIdEntrada(rs.getInt("idEntrada"));
				entrada.setNomeProduto(rs.getString("nomeProd"));
				entrada.setNomeFornecedor(rs.getString("nomeFornecedor"));
				entrada.setLote(rs.getString("lote"));
				entrada.setValorTotal(rs.getDouble("valorTotal"));
				entrada.setQuantidade(rs.getInt("quantidade"));
				entrada.setDataEntrada(rs.getDate("dataEntrada").toLocalDate());
				entrada.setDataValidade(rs.getDate("dataValidade").toLocalDate());
				lista.add(entrada);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
}