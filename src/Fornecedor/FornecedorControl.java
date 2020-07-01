package Fornecedor;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class FornecedorControl {
	private FornecedorDAOImpl FornecedorDAO = new FornecedorDAOImpl();
	private ObservableList<EntityFornecedor> lista = FXCollections.observableArrayList();
	
	private Validator validator;
	
	public FornecedorControl() { 
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	private void alert(AlertType tipo, String title, String header, String content) {
		Alert alert = new Alert(tipo);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}

	public void adicionar(EntityFornecedor fornecedor) { 
		Set<ConstraintViolation<EntityFornecedor>> erros = validator.validate(fornecedor);
		if (erros.isEmpty()) { 
			FornecedorDAO.adicionar(fornecedor);
			alert(AlertType.INFORMATION, "Farmacia", null, 
					"Fornecedor " + fornecedor.getNomeFornecedor() + " cadastrado com sucesso");
			pesquisarPorNome("");
		} else { 
			String msgErros = "Erros: \n";
			for (ConstraintViolation<EntityFornecedor> erro : erros ) { 
				msgErros +=  erro.getMessage() + "\n";
			}
			alert(AlertType.ERROR, "Fornecedor", "ERRO: Não foi possivel cadastrar o fornecedor", msgErros);
		}
	}
	
	public void alterar(EntityFornecedor fornecedor) { 
		Set<ConstraintViolation<EntityFornecedor>> erros = validator.validate(fornecedor);
		if (erros.isEmpty()) { 
			FornecedorDAO.alterar(fornecedor);
			alert(AlertType.INFORMATION, "Farmacia", null, 
					"Fornecedor " + fornecedor.getNomeFornecedor() + " atualizado com sucesso");
			pesquisarPorNome("");
		} else { 
			String msgErros = "Erros: \n";
			for (ConstraintViolation<EntityFornecedor> erro : erros ) { 
				msgErros +=  erro.getMessage() + "\n";
			}
			alert(AlertType.ERROR, "Fornecedor", "ERRO: Não foi possivel atualizar os dados", msgErros);
		}
	}
		

	public EntityFornecedor pesquisarPorNome(String nomeFornecedor) { 
		lista.clear();
		List<EntityFornecedor> fornecedores = FornecedorDAO.pesquisarPorNome(nomeFornecedor);
		lista.addAll(fornecedores);
		return lista.get(0);
	}

	public ObservableList<EntityFornecedor> getLista() {
		return lista;
	}
}