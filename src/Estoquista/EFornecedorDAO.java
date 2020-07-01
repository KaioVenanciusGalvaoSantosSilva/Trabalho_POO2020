package Estoquista;


import java.util.List;

public interface EFornecedorDAO {
	void adicionar(EEntityFornecedor fornecedor);
	List<EEntityFornecedor> pesquisarPorNome(String nomeFornecedor);
}
