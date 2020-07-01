package Login;


import java.util.List;

public interface LoginDAO {
	
	
	List<EntityLogin> pesquisarPorNome(String[] vetor);
	
	
}
