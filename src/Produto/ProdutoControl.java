package Produto;

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

public class ProdutoControl {
	private ProdutoDAOImpl ProdutoDAO = new ProdutoDAOImpl();
	private ObservableList<EntityProduto> lista = FXCollections.observableArrayList();

	private ObservableList<String> tiposProdutos = FXCollections.observableArrayList("Medicamento", "Bebida", "Doce",
			"Artigo de Higiene", "Contraceptivo");

	private ObservableList<String> principiosAtivos = FXCollections.observableArrayList("Dipirona", "Paracetamal",
			"Rivaroxabana", "Diclofenato de Sódio", "Pseudofedrina", "Nenhum");

	private Validator validator;

	public ProdutoControl() {
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

	public void adicionar(EntityProduto produto) {
		Set<ConstraintViolation<EntityProduto>> erros = validator.validate(produto);
		if (erros.isEmpty()) {
			ProdutoDAO.adicionar(produto);
			alert(AlertType.INFORMATION, "Farmacia", null,
					"Produto " + produto.getNomeProduto() + " cadastrado com sucesso");
			pesquisarPorNome("");
		} else {
			String msgErros = "Erros: \n";
			for (ConstraintViolation<EntityProduto> erro : erros) {
				msgErros += erro.getMessage() + "\n";
			}
			alert(AlertType.ERROR, "Produto", "ERRO: Não foi possivel cadastrar o produto", msgErros);
		}
	}

	public void alterar(EntityProduto produto) {
		Set<ConstraintViolation<EntityProduto>> erros = validator.validate(produto);
		if (erros.isEmpty()) {
			ProdutoDAO.alterar(produto);
			alert(AlertType.INFORMATION, "Farmacia", null,
					"Produto " + produto.getNomeProduto() + " atualizado com sucesso");
			pesquisarPorNome("");
		} else {
			String msgErros = "Erros: \n";
			for (ConstraintViolation<EntityProduto> erro : erros) {
				msgErros += erro.getMessage() + "\n";
			}
			alert(AlertType.ERROR, "Produto", "ERRO: Não foi possivel atualizar os dados", msgErros);
		}
	}

	public EntityProduto pesquisarPorNome(String nomeProduto) {
		lista.clear();
		List<EntityProduto> produtos = ProdutoDAO.pesquisarPorNome(nomeProduto);
		lista.addAll(produtos);
		return lista.get(0);
	}

	public ObservableList<EntityProduto> getLista() {
		return lista;
	}

	public ObservableList<String> getTiposProdutos() {
		return tiposProdutos;
	}

	public ObservableList<String> getPrincipiosAtivos() {
		return principiosAtivos;
	}
}