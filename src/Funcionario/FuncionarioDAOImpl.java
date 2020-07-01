package Funcionario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAOImpl implements FuncionarioDAO {
	private static final String URL = "jdbc:mariadb://localhost:3306/saude?allowMultiQueries=true";
	private static final String USER = "root";
	private static final String PASS = "";

	public FuncionarioDAOImpl() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void adicionar(EntityFuncionario funcionario) {

		try {

			Connection con = DriverManager.getConnection(URL, USER, PASS);
			System.out.println("Conectado com Sucesso");

			String sql = "INSERT INTO funcionario (idFuncionario, nomeFuncionario, CPFFuncionario , emailFuncionario ,	telefoneFuncionario, 	"
					+ " CEPFuncionario, enderecoFuncionario, numeroEndereco, 	complementoEndereco, area, funcao, dataAdmissao, dataDesligamento ) "
					+ "VALUES  (0, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, funcionario.getNomeFuncionario());
			stm.setString(2, funcionario.getCPFFuncionario());
			stm.setString(3, funcionario.getEmailFuncionario());
			stm.setInt(4, funcionario.getTelefoneFuncionario());
			stm.setInt(5, funcionario.getCEPFuncionario());
			stm.setString(6, funcionario.getEnderecoFuncionario());
			stm.setInt(7, funcionario.getNumeroEndereco());
			stm.setString(8, funcionario.getComplementoEndereco());
			stm.setString(9, funcionario.getArea());
			stm.setString(10, funcionario.getFuncao());
			stm.setDate(11, java.sql.Date.valueOf(funcionario.getDataAdmissao()));
			stm.setDate(12, java.sql.Date.valueOf(funcionario.getDataDesligamento()));

			stm.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alterar(EntityFuncionario funcionario) {
		try {
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			System.out.println("Conectado com Sucesso");

			String sql = "UPDATE funcionario set nomeFuncionario = ?, CPFFuncionario = ? , emailFuncionario = ? ,	telefoneFuncionario = ?, 	"
					+ " CEPFuncionario = ?, enderecoFuncionario = ?, numeroEndereco = ?, 	complementoEndereco = ?, 	area = ?, 	funcao = ?, 	dataAdmissao = ?, 	dataDesligamento = ?  where idFuncionario = ? ";

			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, funcionario.getNomeFuncionario());
			stm.setString(2, funcionario.getCPFFuncionario());
			stm.setString(3, funcionario.getEmailFuncionario());
			stm.setInt(4, funcionario.getTelefoneFuncionario());
			stm.setInt(5, funcionario.getCEPFuncionario());
			stm.setString(6, funcionario.getEnderecoFuncionario());
			stm.setInt(7, funcionario.getNumeroEndereco());
			stm.setString(8, funcionario.getComplementoEndereco());
			stm.setString(9, funcionario.getArea());
			stm.setString(10, funcionario.getFuncao());
			stm.setDate(11, java.sql.Date.valueOf(funcionario.getDataAdmissao()));
			stm.setDate(12, java.sql.Date.valueOf(funcionario.getDataDesligamento()));
			stm.setInt(13, funcionario.getIdFuncionario());

			stm.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<EntityFuncionario> pesquisarPorNome(String nomeFuncionario) {
		List<EntityFuncionario> lista = new ArrayList<>();
		try {
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			System.out.println("Conectado com Sucesso");
			String sql = "SELECT * FROM funcionario WHERE nomeFuncionario like ?";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, "%" + nomeFuncionario + "%");
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				EntityFuncionario funcionario = new EntityFuncionario();
				funcionario.setIdFuncionario(rs.getInt("idFuncionario"));
				funcionario.setNomeFuncionario(rs.getString("nomeFuncionario"));
				funcionario.setCPFFuncionario(rs.getString("CPFFuncionario"));
				funcionario.setEmailFuncionario(rs.getString("emailFuncionario"));
				funcionario.setTelefoneFuncionario(rs.getInt("telefoneFuncionario"));
				funcionario.setCEPFuncionario(rs.getInt("CEPFuncionario"));
				funcionario.setEnderecoFuncionario(rs.getString("enderecoFuncionario"));
				funcionario.setNumeroEndereco(rs.getInt("numeroEndereco"));
				funcionario.setComplementoEndereco(rs.getString("complementoEndereco"));
				funcionario.setArea(rs.getString("area"));
				funcionario.setFuncao(rs.getString("funcao"));
				funcionario.setDataAdmissao(rs.getDate("dataAdmissao").toLocalDate());
				// funcionario.setDataDesligamento(rs.getDate("dataDesligamento").toLocalDate());

				lista.add(funcionario);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
}