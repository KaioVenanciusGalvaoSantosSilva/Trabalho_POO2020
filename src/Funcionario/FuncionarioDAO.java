package Funcionario;

import java.util.List;

public interface FuncionarioDAO {
	void adicionar(EntityFuncionario funcionario);

	List<EntityFuncionario> pesquisarPorNome(String nomeFuncionario);

	void alterar(EntityFuncionario funcionario);
}