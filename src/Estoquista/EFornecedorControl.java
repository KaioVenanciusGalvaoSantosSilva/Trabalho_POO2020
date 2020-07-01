package Estoquista;


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

public class EFornecedorControl {
	private EFornecedorDAOImpl FornecedorDAO = new EFornecedorDAOImpl();
	private ObservableList<EEntityFornecedor> lista = FXCollections.observableArrayList();
	
	private Validator validator;
	
	public EFornecedorControl() { 
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

	public void adicionar(EEntityFornecedor fornecedor) { 
		Set<ConstraintViolation<EEntityFornecedor>> erros = validator.validate(fornecedor);
		if (erros.isEmpty()) { 
			FornecedorDAO.adicionar(fornecedor);
			alert(AlertType.INFORMATION, "Farmacia", null, 
					"farmacia " + fornecedor.getNomeFornecedor() + " cadastrado com sucesso");
			pesquisarPorNome("");
		} else { 
			String msgErros = "Erros: \n";
			for (ConstraintViolation<EEntityFornecedor> erro : erros ) { 
				msgErros += erro.getPropertyPath() + " - " + erro.getMessage() + "\n";
			}
			alert(AlertType.ERROR, "Farmacia", "ERRO: Não foi possivel cadastrar o fornecedor", msgErros);
		}
	}

	public EEntityFornecedor pesquisarPorNome(String nomeFornecedor) { 
		lista.clear();
		List<EEntityFornecedor> fornecedores = FornecedorDAO.pesquisarPorNome(nomeFornecedor);
		lista.addAll(fornecedores);
		return lista.get(0);
	}

	public ObservableList<EEntityFornecedor> getLista() {
		return lista;
	}


}
