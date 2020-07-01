package Cliente;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOImpl implements ClienteDAO {
	private static final String URL = "jdbc:mariadb://localhost:3306/saude?allowMultiQueries=true";
	private static final String USER = "root";
	private static final String PASS = "";

	public ClienteDAOImpl() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void adicionar(EntityCliente cliente) {
		try {
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			System.out.println("Conectado com Sucesso");
			String sql = "INSERT INTO cliente (idCliente, nomeCliente, CPFCliente , emailCliente ,	telefoneCliente, 	"
					+ " CEPCliente, enderecoCliente, numeroEndereco, 	complementoEndereco ) "
					+ "VALUES  (0, ?, ?, ?, ?, ?, ?, ?, ?)";
			//
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, cliente.getNomeCliente());
			stm.setString(2, cliente.getCPFCliente());
			stm.setString(3, cliente.getEmailCliente());
			stm.setInt(4, cliente.getTelefoneCliente());
			stm.setInt(5, cliente.getCEPCliente());
			stm.setString(6, cliente.getEnderecoCliente());
			stm.setInt(7, cliente.getNumeroEndereco());
			stm.setString(8, cliente.getComplementoEndereco());
			stm.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alterar(EntityCliente cliente) {
		try {
			Connection con = DriverManager.getConnection(URL, USER, PASS);

			String sql = "UPDATE cliente set nomeCliente = ?, CPFCliente = ? , emailCliente = ? ,	telefoneCliente = ?, 	"
					+ " CEPCliente = ?, enderecoCliente = ?, numeroEndereco = ?, 	complementoEndereco = ? where idCliente = ? ";

			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, cliente.getNomeCliente());
			stm.setString(2, cliente.getCPFCliente());
			stm.setString(3, cliente.getEmailCliente());
			stm.setInt(4, cliente.getTelefoneCliente());
			stm.setInt(5, cliente.getCEPCliente());
			stm.setString(6, cliente.getEnderecoCliente());
			stm.setInt(7, cliente.getNumeroEndereco());
			stm.setString(8, cliente.getComplementoEndereco());
			stm.setInt(9, cliente.getIdCliente());
			stm.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<EntityCliente> pesquisarPorNome(String nomeCliente) {
		List<EntityCliente> lista = new ArrayList<>();
		try {
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			System.out.println("Conectado com Sucesso");
			String sql = "SELECT * FROM cliente WHERE nomeCliente like ?";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, "%" + nomeCliente + "%");
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				EntityCliente cliente = new EntityCliente();
				cliente.setIdCliente(rs.getInt("idCliente"));
				cliente.setNomeCliente(rs.getString("nomeCliente"));
				cliente.setCPFCliente(rs.getString("CPFCliente"));
				cliente.setEmailCliente(rs.getString("emailCliente"));
				cliente.setTelefoneCliente(rs.getInt("telefoneCliente"));
				cliente.setCEPCliente(rs.getInt("CEPCliente"));
				cliente.setEnderecoCliente(rs.getString("enderecoCliente"));
				cliente.setNumeroEndereco(rs.getInt("numeroEndereco"));
				cliente.setComplementoEndereco(rs.getString("complementoEndereco"));
				lista.add(cliente);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
}