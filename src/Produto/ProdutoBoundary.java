package Produto;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ProdutoBoundary extends Application implements EventHandler<ActionEvent> {
	private ProdutoControl control = new ProdutoControl();

	private TextField txtIdProduto = new TextField();
	private TextField txtNomeProduto = new TextField();
	private TextField txtValorUnitario = new TextField();
	private ComboBox<String> txtTipoProduto = new ComboBox<>(control.getTiposProdutos());
	private ComboBox<String> txtPrincipioAtivo = new ComboBox<>(control.getPrincipiosAtivos());

	private Button btnAdicionar = new Button("Adicionar");
	private Button btnPesquisar = new Button("P");
	private Button btnAlterar = new Button("Alterar");

	TableView<EntityProduto> tableView = new TableView<EntityProduto>(control.getLista());

	@SuppressWarnings("unchecked")
	public void generateTable() {
		TableColumn<EntityProduto, String> colNomeProduto = new TableColumn<>("Produto");
		colNomeProduto.setCellValueFactory(new PropertyValueFactory<EntityProduto, String>("nomeProduto"));

		TableColumn<EntityProduto, Double> colvalorUnitario = new TableColumn<>("Valor Unid.");
		colvalorUnitario.setCellValueFactory(new PropertyValueFactory<EntityProduto, Double>("valorUnitario"));
		colvalorUnitario.setMinWidth(80);

		TableColumn<EntityProduto, String> colTipoProduto = new TableColumn<>("Tipo de Produto");
		colTipoProduto.setCellValueFactory(new PropertyValueFactory<EntityProduto, String>("tipoProduto"));
		colTipoProduto.setMinWidth(100);

		TableColumn<EntityProduto, String> colprincipioAtivo = new TableColumn<>("Principio Ativo");
		colprincipioAtivo.setCellValueFactory(new PropertyValueFactory<EntityProduto, String>("principioAtivo"));
		colprincipioAtivo.setMinWidth(100);

		tableView.getColumns().addAll(colNomeProduto, colvalorUnitario, colTipoProduto, colprincipioAtivo);
		tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<EntityProduto>() {

			@Override
			public void changed(ObservableValue<? extends EntityProduto> Produto, EntityProduto antigo,
					EntityProduto novo) {
				entityToBoundary(novo);
			}
		});
	}

	@Override
	public void start(Stage stage) throws Exception {
		Pane pane = new Pane();
		Scene scn = new Scene(pane, 370, 570);
		// 350,28
		generateTable();
		Label lblProduto = new Label("Produto: ");
		lblProduto.relocate(10, 10);

		txtNomeProduto.setMinWidth(190);
		txtNomeProduto.relocate(90, 10);

		Label lblValorUnitario = new Label("Valor Unitário: ");
		lblValorUnitario.relocate(10, 40);

		txtValorUnitario.setMinWidth(230);
		txtValorUnitario.relocate(90, 40);

		Label lblTipoProduto = new Label("Tipo: ");
		lblTipoProduto.relocate(10, 70);
		;

		txtTipoProduto.setMinWidth(230);
		txtTipoProduto.relocate(90, 70);

		Label lblPrincipioAtivo = new Label("Principio Ativo: ");
		lblPrincipioAtivo.relocate(10, 100);

		txtPrincipioAtivo.setMinWidth(230);
		txtPrincipioAtivo.relocate(90, 100);

		btnAdicionar.relocate(65, 140);
		btnAdicionar.setMinWidth(150);

		btnAlterar.relocate(205, 140);
		btnAlterar.setMinWidth(120);

		btnPesquisar.relocate(290, 10);
		btnPesquisar.setMinSize(30, 5);

		tableView.relocate(10, 170);

		pane.getChildren().addAll(tableView, lblProduto, txtNomeProduto, lblValorUnitario, txtValorUnitario,
				lblTipoProduto, txtTipoProduto, lblPrincipioAtivo, txtPrincipioAtivo, btnAdicionar, btnPesquisar,
				btnAlterar);

		btnAdicionar.setMinWidth(120);

		// Evento botão Pesquisar
		btnPesquisar.setOnAction(this);
		// Evento botão adicionar
		btnAdicionar.setOnAction(this);
		// Evento botão alterar
		btnAlterar.setOnAction(this);

		stage.setScene(scn);
		stage.setTitle("Gerenciar Produtos");
		stage.setX(50);
		stage.setY(0);
		stage.setResizable(false);
		stage.show();

		control.pesquisarPorNome("");
	}

	public void handle(ActionEvent e) {
		// Evento botao Adicionar
		if (e.getTarget() == btnAdicionar) {

			EntityProduto produto = boundaryToEntity();
			control.adicionar(produto);

			entityToBoundary(new EntityProduto());

			// Evento boao 'P' Pesquisar
		} else if (e.getTarget() == btnPesquisar) {

			String nomeProduto = txtNomeProduto.getText();
			EntityProduto produto = control.pesquisarPorNome(nomeProduto);
			entityToBoundary(produto);

		} else if (e.getTarget() == btnAlterar) {

			EntityProduto produto = boundaryToEntity();

			control.alterar(produto);
			entityToBoundary(new EntityProduto());

			// Evento boao 'P' Pesquisar
		}
	}

	public EntityProduto boundaryToEntity() {
		EntityProduto produto = new EntityProduto();

		try {

			produto.setIdProduto(Integer.parseInt(txtIdProduto.getText()));
			produto.setNomeProduto(txtNomeProduto.getText());
			produto.setValorUnitario(Double.parseDouble(txtValorUnitario.getText()));
			produto.setTipoProduto(txtTipoProduto.getSelectionModel().getSelectedItem());
			produto.setPrincipioAtivo(txtPrincipioAtivo.getSelectionModel().getSelectedItem());

		} catch (Exception ex) {
			System.out.println("Erro ao computar os dados");
		}
		return produto;
	}

	public void entityToBoundary(EntityProduto produto) {

		if (produto != null) {

			txtIdProduto.setText(String.valueOf(produto.getIdProduto()));
			txtNomeProduto.setText(produto.getNomeProduto());
			txtValorUnitario.setText(String.valueOf(produto.getValorUnitario()));
			txtTipoProduto.getSelectionModel().select(produto.getTipoProduto());
			txtPrincipioAtivo.getSelectionModel().select(produto.getPrincipioAtivo());

		}
	}

	public static void main(String[] args) {

		Application.launch(ProdutoBoundary.class, args);
	}
}