package EntradaProdutos;

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

public class EntradaBoundary extends Application implements EventHandler<ActionEvent> {
	private EntradaControl control = new EntradaControl();

	private TextField txtIdEntrada = new TextField();
	private TextField txtNomeProduto = new TextField();// Produto
	private TextField txtNomeFornecedor = new TextField();// Fornecedor
	private TextField txtLote = new TextField();// Lote
	private TextField txtValorTotal = new TextField();// Valor Total
	private TextField txtQuantidade = new TextField();// Quantidade
	private TextField txtEntrada = new TextField();// Entrada
	private TextField txtValidade = new TextField();// Validade

	private Button btnAdicionar = new Button("Adicionar");
	private Button btnPesquisar = new Button("P");
	private Button btnAlterar = new Button("Alterar");

	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	TableView<EntityEntrada> tableView = new TableView<EntityEntrada>(control.getLista());

	@SuppressWarnings("unchecked")
	public void generateTable() {
		TableColumn<EntityEntrada, String> colNomeProduto = new TableColumn<>("Produto");
		colNomeProduto.setCellValueFactory(new PropertyValueFactory<EntityEntrada, String>("nomeProduto"));

		TableColumn<EntityEntrada, String> colNomeFornecedor = new TableColumn<>("Fornecedor");
		colNomeFornecedor.setCellValueFactory(new PropertyValueFactory<EntityEntrada, String>("nomeFornecedor"));
		colNomeFornecedor.setMinWidth(90);

		TableColumn<EntityEntrada, String> colLote = new TableColumn<>("Lote");
		colLote.setCellValueFactory(new PropertyValueFactory<EntityEntrada, String>("lote"));

		TableColumn<EntityEntrada, Double> colValorTotal = new TableColumn<>("Valor Total");
		colValorTotal.setCellValueFactory(new PropertyValueFactory<EntityEntrada, Double>("valorTotal"));
		colValorTotal.setMinWidth(90);

		TableColumn<EntityEntrada, Integer> colQuantidade = new TableColumn<>("Quantidade");
		colQuantidade.setCellValueFactory(new PropertyValueFactory<EntityEntrada, Integer>("quantidade"));
		colQuantidade.setMinWidth(90);

		TableColumn<EntityEntrada, String> colEntrada = new TableColumn<>("Entrada");
		colEntrada.setCellValueFactory(item -> new ReadOnlyStringWrapper(dtf.format(item.getValue().getDataEntrada())));
		colEntrada.setMinWidth(90);

		TableColumn<EntityEntrada, String> colValidade = new TableColumn<>("Validade");
		colValidade
				.setCellValueFactory(item -> new ReadOnlyStringWrapper(dtf.format(item.getValue().getDataValidade())));
		colValidade.setMinWidth(90);

		tableView.getColumns().addAll(colNomeProduto, colNomeFornecedor, colLote, colValorTotal, colQuantidade,
				colEntrada, colValidade);
		tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<EntityEntrada>() {

			@Override
			public void changed(ObservableValue<? extends EntityEntrada> Produto, EntityEntrada antigo,
					EntityEntrada novo) {
				entityToBoundary(novo);
				
			}
		});
	}

	@Override
	public void start(Stage stage) throws Exception {
		Pane pane = new Pane();
		Scene scn = new Scene(pane, 960, 410);
		// 350,280
		generateTable();
		Label lblProduto = new Label("Produto: ");
		lblProduto.relocate(10, 10);

		txtNomeProduto.setMinWidth(190);
		txtNomeProduto.relocate(90, 10);

		Label lblNomeFornecedor = new Label("Fornecedor: ");
		lblNomeFornecedor.relocate(10, 40);

		txtNomeFornecedor.setMinWidth(230);
		txtNomeFornecedor.relocate(90, 40);

		Label lblEmail = new Label("Lote: ");
		lblEmail.relocate(10, 70);

		txtLote.setMinWidth(230);
		txtLote.relocate(90, 70);

		Label lblValorTotal = new Label("Valor Total: ");
		lblValorTotal.relocate(10, 100);

		txtValorTotal.setMinWidth(230);
		txtValorTotal.relocate(90, 100);

		Label lblQuantidade = new Label("Quantidade: ");
		lblQuantidade.relocate(10, 130);

		txtQuantidade.setMinWidth(230);
		txtQuantidade.relocate(90, 130);

		Label lblArea = new Label("Entrada: ");
		lblArea.relocate(10, 160);

		txtEntrada.setMinWidth(230);
		txtEntrada.relocate(90, 160);

		Label lblFuncao = new Label("Validade: ");
		lblFuncao.relocate(10, 190);

		txtValidade.setMinWidth(230);
		txtValidade.relocate(90, 190);

		btnAdicionar.relocate(40, 250);
		btnAdicionar.setMinWidth(150);

		btnAlterar.relocate(180, 250);
		btnAlterar.setMinWidth(120);

		btnPesquisar.relocate(290, 10);
		btnPesquisar.setMinSize(30, 5);

		tableView.relocate(340, 10);

		pane.getChildren().addAll(tableView, lblProduto, txtNomeProduto, lblNomeFornecedor, txtNomeFornecedor, lblEmail,
				txtLote, lblValorTotal, txtValorTotal, lblQuantidade, txtQuantidade, lblArea, txtEntrada, lblFuncao,
				txtValidade, btnAdicionar, btnPesquisar, btnAlterar);

		btnAdicionar.setMinWidth(120);

		// Evento botão Pesquisar
		btnPesquisar.setOnAction(this);
		// Evento botão adicionar
		btnAdicionar.setOnAction(this);
		// Evento botão alterar
		btnAlterar.setOnAction(this);

		stage.setScene(scn);
		stage.setTitle("Gerenciar Entrada de Produtos");
		stage.setX(50);
		stage.setY(50);
		stage.setResizable(false);
		stage.show();

		control.pesquisarPorNome("");
	}

	public void handle(ActionEvent e) {
		// Evento botao Adicionar
		if (e.getTarget() == btnAdicionar) {

			EntityEntrada entrada = boundaryToEntity();
			control.adicionar(entrada);

			entityToBoundary(new EntityEntrada());

			// Evento boao 'P' Pesquisar
		} else if (e.getTarget() == btnPesquisar) {

			String nomeProd = txtNomeProduto.getText();
			EntityEntrada entrada = control.pesquisarPorNome(nomeProd);
			entityToBoundary(entrada);

		} else if (e.getTarget() == btnAlterar) {

			EntityEntrada entrada = boundaryToEntity();

			control.alterar(entrada);
			entityToBoundary(new EntityEntrada());

			// Evento boao 'P' Pesquisar
		}
	}

	public EntityEntrada boundaryToEntity() {
		EntityEntrada entrada = new EntityEntrada();

		try {

			entrada.setIdEntrada(Integer.parseInt(txtIdEntrada.getText()));
			entrada.setNomeProduto(txtNomeProduto.getText());
			entrada.setNomeFornecedor(txtNomeFornecedor.getText());
			entrada.setLote(txtLote.getText());
			entrada.setValorTotal(Double.parseDouble(txtValorTotal.getText()));
			entrada.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
			LocalDate dt = LocalDate.parse(txtEntrada.getText(), dtf);
			LocalDate dt2 = LocalDate.parse(txtValidade.getText(), dtf);
			entrada.setDataEntrada(dt);
			entrada.setDataValidade(dt2);

		} catch (Exception ex) {
			System.out.println("Erro ao computar os dados");
		}
		return entrada;
	}

	public void entityToBoundary(EntityEntrada entrada) {

		if (entrada != null) {

			txtIdEntrada.setText(String.valueOf(entrada.getIdEntrada()));
			txtNomeProduto.setText(entrada.getNomeProduto());
			txtNomeFornecedor.setText(entrada.getNomeFornecedor());
			txtLote.setText(entrada.getLote());
			txtValorTotal.setText(String.valueOf(entrada.getValorTotal()));
			txtQuantidade.setText(String.valueOf(entrada.getQuantidade()));
			txtEntrada.setText(dtf.format(entrada.getDataEntrada()));
			txtValidade.setText(dtf.format(entrada.getDataValidade()));
		}
	}

	public static void main(String[] args) {

		Application.launch(EntradaBoundary.class, args);
	}
}