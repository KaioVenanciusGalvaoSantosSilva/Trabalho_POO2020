package exemplo;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PetDAOImpl implements PetDAO {
	private static final String URL = "jdbc:mariadb://localhost:3306/petshop?allowMultiQueries=true";
	//ajustar bancodedados 'petshop' para o nome do banco
	private static final String USER = "root";
	private static final String PASS = "";
	
	public PetDAOImpl() { 
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void adicionar(Entity p) {
		try {
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			System.out.println("Conectado com Sucesso");
			// ajustar o nome da tabela e os atributos
			String sql = "INSERT INTO pet (id, nome, raca, peso, nascimento) "
					+ "VALUES  (0, ?, ?, ?, ?)";		
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, p.getNome());
			stm.setString(2, p.getRaca());
			stm.setDouble(3, p.getPeso());
			stm.setDate(4, java.sql.Date.valueOf(p.getNascimento()));
			stm.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Entity> pesquisarPorNome(String nome) {
		List<Entity> lista = new ArrayList<>();
		try {
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			System.out.println("Conectado com Sucesso");
			// ajustar o nome da tabela 'pet'
			String sql = "SELECT * FROM pet WHERE nome like ?";			
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, "%" + nome + "%");
			ResultSet rs = stm.executeQuery();
			while (rs.next()) { 
				Entity p = new Entity();
				p.setNome(rs.getString("nome"));
				p.setRaca(rs.getString("raca"));
				p.setPeso(rs.getDouble("peso"));
				p.setNascimento(rs.getDate("nascimento").toLocalDate());
				lista.add(p);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
}
