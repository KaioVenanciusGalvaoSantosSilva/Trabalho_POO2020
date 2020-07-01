package Login;


import java.util.List;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LoginControl {
	private LoginDAOImpl LoginDAO = new LoginDAOImpl();
	private ObservableList<EntityLogin> lista = FXCollections.observableArrayList();
	


	public EntityLogin pesquisarPorNome(String[] vetor) {
		// TODO Auto-generated method stub
		
		lista.clear();
		List<EntityLogin> logins = LoginDAO.pesquisarPorNome(vetor);
		lista.addAll(logins);
		
		return lista.get(0);
		
	}

	public ObservableList<EntityLogin> getLista() {
		return lista;
	}


	
}
