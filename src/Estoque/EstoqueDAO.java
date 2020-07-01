package Estoque;

import java.util.List;

public interface EstoqueDAO {

	List<EntityEstoque> pesquisarPorNome(String nomeProduto);
}