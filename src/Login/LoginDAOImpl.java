package Login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginDAOImpl implements LoginDAO {
	private static final String URL = "jdbc:mariadb://localhost:3306/saude?allowMultiQueries=true";
	private static final String USER = "root";
	private static final String PASS = "";

	public LoginDAOImpl() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public List<EntityLogin> pesquisarPorNome(String[] vetor) {

		List<EntityLogin> lista = new ArrayList<>();
		try {
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			String sql = "SELECT cargo FROM login WHERE usuario = ? and senha = ?";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, "" + vetor[0] + "");

			stm.setString(2, "" + vetor[1] + "");
			ResultSet rs = stm.executeQuery();
			//FALTA O TRATAMENTO DO ERRO QUE APARECE QUANDO PESQUISA PELOS CAMPOS E NÃO RETORNA NENHUM
			while (rs.next()) {

				EntityLogin login = new EntityLogin();
				login.setCargo(rs.getString("cargo"));
				lista.add(login);
			}

			con.close();
		} catch (SQLException e) {
			// e.printStackTrace();.

		}

		return lista;
	}

}
