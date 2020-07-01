package Produto;

import java.util.List;

public interface ProdutoDAO {
	void adicionar(EntityProduto produto);

	List<EntityProduto> pesquisarPorNome(String nomeProduto);

	void alterar(EntityProduto produto);
}