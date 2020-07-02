package Cliente;

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

public class ClienteControl {
	private ClienteDAOImpl ClienteDAO = new ClienteDAOImpl();
	private ObservableList<EntityCliente> lista = FXCollections.observableArrayList();
	
	private Validator validator;
	
	public ClienteControl() { 
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

	public void adicionar(EntityCliente cliente) { 
		Set<ConstraintViolation<EntityCliente>> erros = validator.validate(cliente);
		
		if (erros.isEmpty()) { 
			ClienteDAO.adicionar(cliente);
			alert(AlertType.INFORMATION, "Farmacia", null, 
					"Cliente " + cliente.getNomeCliente() + " cadastrado com sucesso");
			pesquisarPorNome("");
		} else {

			String msgErros = "Erros: \n";
			for (ConstraintViolation<EntityCliente> erro : erros ) { 
				msgErros +=  erro.getMessage() + "\n";
			}
			alert(AlertType.ERROR, "Cliente", "ERRO: Não foi possivel cadastrar o cliente", msgErros);
		}
	}
	
	public void alterar(EntityCliente cliente) { 
		Set<ConstraintViolation<EntityCliente>> erros = validator.validate(cliente);
		if (erros.isEmpty()) { 
			ClienteDAO.alterar(cliente);
			alert(AlertType.INFORMATION, "Farmacia", null, 
					"Cliente " + cliente.getNomeCliente() + " atualizado com sucesso");
			pesquisarPorNome("");
		} else { 
			String msgErros = "Erros: \n";
			for (ConstraintViolation<EntityCliente> erro : erros ) { 
				msgErros +=  erro.getMessage() + "\n";
			}
			alert(AlertType.ERROR, "Cliente", "ERRO: Não foi possivel atualizar os dados", msgErros);
		}
	}

	public EntityCliente pesquisarPorNome(String nomeCliente) { 
		lista.clear();
		List<EntityCliente> clientes = ClienteDAO.pesquisarPorNome(nomeCliente);
		lista.addAll(clientes);
		return lista.get(0);
	}

	public ObservableList<EntityCliente> getLista() {
		return lista;
	}
}