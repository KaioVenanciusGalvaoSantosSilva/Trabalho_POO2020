package Fornecedor;

import java.util.List;

public interface FornecedorDAO {
	void adicionar(EntityFornecedor fornecedor);

	List<EntityFornecedor> pesquisarPorNome(String nomeFornecedor);

	void alterar(EntityFornecedor fornecedor);
}