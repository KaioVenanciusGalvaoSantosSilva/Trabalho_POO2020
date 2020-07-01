package Estoque;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class EstoqueBoundary extends Application implements EventHandler<ActionEvent> {
	private EstoqueControl control = new EstoqueControl();

	private TextField txtIdProduto = new TextField();
	private TextField txtNomeProduto = new TextField();
	private TextField txtPrincipioAtivo = new TextField();
	private TextField txtnomeFornecedor = new TextField();
	private TextField txtValorUnitario = new TextField();
	private TextField txtQuantidadeAtual = new TextField();
	private TextField txtValidade = new TextField();

	private Button btnPesquisar = new Button("Pesquisar");

	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	TableView<EntityEstoque> tableView = new TableView<EntityEstoque>(control.getLista());

	@SuppressWarnings("unchecked")
	public void generateTable() {
		TableColumn<EntityEstoque, String> colNomeProduto = new TableColumn<>("Produto");
		colNomeProduto.setCellValueFactory(new PropertyValueFactory<EntityEstoque, String>("nomeProduto"));

		TableColumn<EntityEstoque, String> colPrincipioAtivo = new TableColumn<>("Principio Ativo");
		colPrincipioAtivo.setCellValueFactory(new PropertyValueFactory<EntityEstoque, String>("principioAtivo"));
		colPrincipioAtivo.setMinWidth(100);

		TableColumn<EntityEstoque, Double> colValorUnitario = new TableColumn<>("Valor Unid.");
		colValorUnitario.setCellValueFactory(new PropertyValueFactory<EntityEstoque, Double>("valorUnitario"));
		colValorUnitario.setMinWidth(90);

		TableColumn<EntityEstoque, Integer> colQuantidadeAtual = new TableColumn<>("Quantidade");
		colQuantidadeAtual.setCellValueFactory(new PropertyValueFactory<EntityEstoque, Integer>("quantidadeAtual"));
		colQuantidadeAtual.setMinWidth(90);

		TableColumn<EntityEstoque, String> colValidade = new TableColumn<>("Validade");
		colValidade
				.setCellValueFactory(item -> new ReadOnlyStringWrapper(dtf.format(item.getValue().getDataValidade())));
		colValidade.setMinWidth(90);

		TableColumn<EntityEstoque, String> colNomeFornecedor = new TableColumn<>("Fornecedor");
		colNomeFornecedor.setCellValueFactory(new PropertyValueFactory<EntityEstoque, String>("nomeFornecedor"));

		tableView.getColumns().addAll(colNomeProduto, colPrincipioAtivo, colValorUnitario, colQuantidadeAtual,
				colValidade, colNomeFornecedor);
		tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<EntityEstoque>() {

			@Override
			public void changed(ObservableValue<? extends EntityEstoque> Produto, EntityEstoque antigo,
					EntityEstoque novo) {
				entityToBoundary(novo);
			}
		});
	}

	@Override
	public void start(Stage stage) throws Exception {
		Pane pane = new Pane();
		Scene scn = new Scene(pane, 540, 500);
		// 350,280
		generateTable();

		Label lblEstoque = new Label("Estoque");
		lblEstoque.relocate(210, 10);
		lblEstoque.setStyle("-fx-font-size: 35");

		Label lblProduto = new Label("Produto: ");
		lblProduto.relocate(125, 75);

		txtNomeProduto.setMinWidth(190);
		txtNomeProduto.relocate(175, 70);

		btnPesquisar.relocate(370, 70);
		btnPesquisar.setMinSize(30, 5);

		tableView.relocate(10, 100);

		pane.getChildren().addAll(tableView, lblProduto, txtNomeProduto, btnPesquisar, lblEstoque);

		// Evento botão Pesquisar
		btnPesquisar.setOnAction(this);

		stage.setScene(scn);
		stage.setTitle("Gerenciar Estoque");
		stage.setX(400);
		stage.setY(50);
		stage.setResizable(false);
		stage.show();

		control.pesquisarPorNome("");
	}

	public void handle(ActionEvent e) {
		// Evento botao Adicionar
		if (e.getTarget() == btnPesquisar) {

			String nomeProduto = txtNomeProduto.getText();
			EntityEstoque entrada = control.pesquisarPorNome(nomeProduto);
			entityToBoundary(entrada);
		}
	}

	public EntityEstoque boundaryToEntity() {
		EntityEstoque entrada = new EntityEstoque();

		try {
			entrada.setIdProduto(Integer.parseInt(txtIdProduto.getText()));
			entrada.setNomeProduto(txtNomeProduto.getText());
			entrada.setPrincipioAtivo(txtPrincipioAtivo.getText());
			entrada.setValorUnitario(Double.parseDouble(txtValorUnitario.getText()));
			entrada.setQuantidadeAtual(Integer.parseInt(txtQuantidadeAtual.getText()));

			LocalDate dt2 = LocalDate.parse(txtValidade.getText(), dtf);
			entrada.setDataValidade(dt2);
			entrada.setNomeFornecedor(txtnomeFornecedor.getText());

		} catch (Exception ex) {
			System.out.println("Erro ao computar os dados");
		}
		return entrada;
	}

	public void entityToBoundary(EntityEstoque entrada) {

		if (entrada != null) {

			txtIdProduto.setText(String.valueOf(entrada.getIdProduto()));
			txtNomeProduto.setText(entrada.getNomeProduto());
			txtPrincipioAtivo.setText(entrada.getPrincipioAtivo());
			txtValorUnitario.setText(String.valueOf(entrada.getValorUnitario()));
			txtQuantidadeAtual.setText(String.valueOf(entrada.getQuantidadeAtual()));
			txtValidade.setText(dtf.format(entrada.getDataValidade()));
			txtnomeFornecedor.setText(entrada.getNomeFornecedor());
		}
	}

	public static void main(String[] args) {

		Application.launch(EstoqueBoundary.class, args);
	}
}