

import java.util.List;

public interface PetDAO {
	void adicionar(Entity p);
	List<Entity> pesquisarPorNome(String nome);
}
