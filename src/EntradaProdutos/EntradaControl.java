package EntradaProdutos;

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

public class EntradaControl {
	private EntradaDAOImpl EntradaDAO = new EntradaDAOImpl();
	private ObservableList<EntityEntrada> lista = FXCollections.observableArrayList();

	private Validator validator;

	public EntradaControl() {
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

	public void adicionar(EntityEntrada entrada) {
		Set<ConstraintViolation<EntityEntrada>> erros = validator.validate(entrada);
		if (erros.isEmpty()) {
			EntradaDAO.adicionar(entrada);
			alert(AlertType.INFORMATION, "Farmacia", null,
					"Produto " + entrada.getNomeProduto() + " cadastrado com sucesso");
			pesquisarPorNome("");
		} else {
			String msgErros = "Erros: \n";
			for (ConstraintViolation<EntityEntrada> erro : erros) {
				msgErros += erro.getMessage() + "\n";
			}
			alert(AlertType.ERROR, "Produto", "ERRO: Não foi possivel cadastrar a entrada", msgErros);
		}
	}

	public void alterar(EntityEntrada entrada) {
		Set<ConstraintViolation<EntityEntrada>> erros = validator.validate(entrada);
		if (erros.isEmpty()) {
			EntradaDAO.alterar(entrada);
			alert(AlertType.INFORMATION, "Farmacia", null,
					"Produto " + entrada.getNomeProduto() + " atualizado com sucesso");
			pesquisarPorNome("");
		} else {
			String msgErros = "Erros: \n";
			for (ConstraintViolation<EntityEntrada> erro : erros) {
				msgErros += erro.getMessage() + "\n";
			}
			alert(AlertType.ERROR, "Produto", "ERRO: Não foi possivel atualizar os dados", msgErros);
		}
	}

	public EntityEntrada pesquisarPorNome(String nomeProd) {
		lista.clear();
		List<EntityEntrada> entradas = EntradaDAO.pesquisarPorNome(nomeProd);
		lista.addAll(entradas);
		return lista.get(0);
	}

	public ObservableList<EntityEntrada> getLista() {
		return lista;
	}
}