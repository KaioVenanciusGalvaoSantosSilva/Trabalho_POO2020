package Produto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAOImpl implements ProdutoDAO {
	private static final String URL = "jdbc:mariadb://localhost:3306/saude?allowMultiQueries=true";
	private static final String USER = "root";
	private static final String PASS = "";

	public ProdutoDAOImpl() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void adicionar(EntityProduto produto) {

		try {

			Connection con = DriverManager.getConnection(URL, USER, PASS);
			System.out.println("Conectado com Sucesso");

			String sql = "INSERT INTO produto (idProduto, nomeProduto, valorUnitario,"
					+ "  tipoProduto, principioAtivo) " + "VALUES  (0, ?, ?, ?, ?)";

			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, produto.getNomeProduto());
			stm.setDouble(2, produto.getValorUnitario());
			stm.setString(3, produto.getTipoProduto());
			stm.setString(4, produto.getPrincipioAtivo());

			stm.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alterar(EntityProduto produto) {
		try {
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			System.out.println("Conectado com Sucesso");

			String sql = "UPDATE produto set nomeProduto = ?,	valorUnitario = ?,"
					+ " tipoProduto = ?, 	principioAtivo = ?  where idProduto = ? ";

			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, produto.getNomeProduto());
			stm.setDouble(2, produto.getValorUnitario());
			stm.setString(3, produto.getTipoProduto());
			stm.setString(4, produto.getPrincipioAtivo());
			stm.setInt(5, produto.getIdProduto());

			stm.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<EntityProduto> pesquisarPorNome(String nomeProduto) {
		List<EntityProduto> lista = new ArrayList<>();
		try {
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			System.out.println("Conectado com Sucesso");
			String sql = "SELECT * FROM produto WHERE nomeProduto like ?";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, "%" + nomeProduto + "%");
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				EntityProduto produto = new EntityProduto();
				produto.setIdProduto(rs.getInt("idProduto"));
				produto.setNomeProduto(rs.getString("nomeProduto"));
				produto.setValorUnitario(rs.getDouble("valorUnitario"));
				produto.setTipoProduto(rs.getString("tipoProduto"));
				produto.setPrincipioAtivo(rs.getString("principioAtivo"));
				lista.add(produto);

			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
}