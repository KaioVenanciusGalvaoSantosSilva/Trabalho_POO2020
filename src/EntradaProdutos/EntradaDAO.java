package EntradaProdutos;

import java.util.List;

public interface EntradaDAO {
	void adicionar(EntityEntrada entrada);

	List<EntityEntrada> pesquisarPorNome(String nomeProd);

	void alterar(EntityEntrada entrada);
}