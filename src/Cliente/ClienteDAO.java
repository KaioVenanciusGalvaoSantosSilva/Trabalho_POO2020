package Cliente;

import java.util.List;

public interface ClienteDAO {
	void adicionar(EntityCliente cliente);
	List<EntityCliente> pesquisarPorNome(String nomeCliente);
	
	void alterar(EntityCliente cliente);
}