package Estoquista;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EFornecedorDAOImpl implements EFornecedorDAO {
	private static final String URL = "jdbc:mariadb://localhost:3306/saude?allowMultiQueries=true";
	// ajustar bancodedados 'petshop' para o nome do banco
	private static final String USER = "root";
	private static final String PASS = "";

	public EFornecedorDAOImpl() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void adicionar(EEntityFornecedor fornecedor) {
		try {
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			System.out.println("Conectado com Sucesso");
			// ajustar o nome da tabela e os atributos
			String sql = "INSERT INTO fornecedor (idFornecedor, nomeFornecedor, CNPJFornecedor , emailFornecedor ,	telefoneFornecedor, 	"
					+ " CEPFornecedor, enderecoFornecedor, numeroEndereco, 	complementoEndereco ) "
					+ "VALUES  (0, ?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, fornecedor.getNomeFornecedor());
			stm.setString(2, fornecedor.getCNPJFornecedor());
			stm.setString(3, fornecedor.getEmailFornecedor());
			stm.setInt(4, fornecedor.getTelefoneFornecedor());
			stm.setInt(5, fornecedor.getCEPFornecedor());
			stm.setString(6, fornecedor.getEnderecoFornecedor());
			stm.setInt(7, fornecedor.getNumeroEndereco());
			stm.setString(8, fornecedor.getComplementoEndereco());

			stm.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<EEntityFornecedor> pesquisarPorNome(String nomeFornecedor) {
		List<EEntityFornecedor> lista = new ArrayList<>();
		try {
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			System.out.println("Conectado com Sucesso");
			String sql = "SELECT * FROM fornecedor WHERE nomeFornecedor like ?";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, "%" + nomeFornecedor + "%");
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				EEntityFornecedor fornecedor = new EEntityFornecedor();
				fornecedor.setNomeFornecedor(rs.getString("nomeFornecedor"));
				fornecedor.setCNPJFornecedor(rs.getString("CNPJFornecedor"));
				fornecedor.setEmailFornecedor(rs.getString("emailFornecedor"));
				fornecedor.setTelefoneFornecedor(rs.getInt("telefoneFornecedor"));
				fornecedor.setCEPFornecedor(rs.getInt("CEPFornecedor"));
				fornecedor.setEnderecoFornecedor(rs.getString("enderecoFornecedor"));
				fornecedor.setNumeroEndereco(rs.getInt("numeroEndereco"));
				fornecedor.setComplementoEndereco(rs.getString("complementoEndereco"));

				lista.add(fornecedor);
				

			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
}
