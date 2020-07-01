package Funcionario;

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

public class FuncionarioControl {
	private FuncionarioDAOImpl FuncionarioDAO = new FuncionarioDAOImpl();
	private ObservableList<EntityFuncionario> lista = FXCollections.observableArrayList();

	private ObservableList<String> areas = FXCollections.observableArrayList("Gestão", "Saúde", "Administração",
			"Logistica");

	private ObservableList<String> funcoes = FXCollections.observableArrayList("Gerente", "Farmacêutico", "Atendente",
			"Caixa", "Estoquista");

	private Validator validator;

	public FuncionarioControl() {
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

	public void adicionar(EntityFuncionario funcionario) {
		Set<ConstraintViolation<EntityFuncionario>> erros = validator.validate(funcionario);
		if (erros.isEmpty()) {
			FuncionarioDAO.adicionar(funcionario);
			alert(AlertType.INFORMATION, "Farmacia", null,
					"Funcionario " + funcionario.getNomeFuncionario() + " cadastrado com sucesso");
			pesquisarPorNome("");
		} else {
			String msgErros = "Erros: \n";
			for (ConstraintViolation<EntityFuncionario> erro : erros) {
				msgErros += erro.getMessage() + "\n";
			}
			alert(AlertType.ERROR, "Funcionario", "ERRO: Não foi possivel cadastrar o funcionario", msgErros);
		}
	}

	public void alterar(EntityFuncionario funcionario) {
		Set<ConstraintViolation<EntityFuncionario>> erros = validator.validate(funcionario);
		if (erros.isEmpty()) {
			FuncionarioDAO.alterar(funcionario);
			alert(AlertType.INFORMATION, "Farmacia", null,
					"Funcionario " + funcionario.getNomeFuncionario() + " atualizado com sucesso");
			pesquisarPorNome("");
		} else {
			String msgErros = "Erros: \n";
			for (ConstraintViolation<EntityFuncionario> erro : erros) {
				msgErros += erro.getMessage() + "\n";
			}
			alert(AlertType.ERROR, "Funcionario", "ERRO: Não foi possivel atualizar os dados", msgErros);
		}
	}

	public EntityFuncionario pesquisarPorNome(String nomeFuncionario) {
		lista.clear();
		List<EntityFuncionario> funcionarios = FuncionarioDAO.pesquisarPorNome(nomeFuncionario);
		lista.addAll(funcionarios);
		return lista.get(0);
	}

	public ObservableList<EntityFuncionario> getLista() {
		return lista;
	}

	public ObservableList<String> getAreas() {
		return areas;
	}

	public ObservableList<String> getFuncoes() {
		return funcoes;
	}
}
