package Fornecedor;

import javafx.application.Application;
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

public class FornecedorBoundary extends Application implements EventHandler<ActionEvent> {
	private FornecedorControl control = new FornecedorControl();

	private TextField txtIdFornecedor = new TextField();
	private TextField txtNomeFornecedor = new TextField();
	private TextField txtCNPJFornecedor = new TextField();
	private TextField txtemailFornecedor = new TextField();
	private TextField txtTelefoneFornecedor = new TextField();
	private TextField txtCEPFornecedor = new TextField();
	private TextField txtEnderecoFornecedor = new TextField();
	private TextField txtNumeroEndereco = new TextField();
	private TextField txtComplementoEndereco = new TextField();
	private Button btnAdicionar = new Button("Adicionar");
	private Button btnPesquisar = new Button("P");
	private Button btnAlterar = new Button("Alterar");

	TableView<EntityFornecedor> tableView = new TableView<EntityFornecedor>(control.getLista());

	@SuppressWarnings("unchecked")
	public void generateTable() {
		TableColumn<EntityFornecedor, String> colNomeFornecedor = new TableColumn<>("Fornecedor");
		colNomeFornecedor.setCellValueFactory(new PropertyValueFactory<EntityFornecedor, String>("nomeFornecedor"));

		TableColumn<EntityFornecedor, String> colCNPJFornecedor = new TableColumn<>("CNPJ");
		colCNPJFornecedor.setCellValueFactory(new PropertyValueFactory<EntityFornecedor, String>("CNPJFornecedor"));

		TableColumn<EntityFornecedor, String> colEmailFornecedor = new TableColumn<>("Email");
		colEmailFornecedor.setCellValueFactory(new PropertyValueFactory<EntityFornecedor, String>("emailFornecedor"));

		TableColumn<EntityFornecedor, Integer> coltelefoneFornecedor = new TableColumn<>("Telefone");
		coltelefoneFornecedor
				.setCellValueFactory(new PropertyValueFactory<EntityFornecedor, Integer>("telefoneFornecedor"));

		TableColumn<EntityFornecedor, Integer> colCEPFornecedor = new TableColumn<>("CEP");
		colCEPFornecedor.setCellValueFactory(new PropertyValueFactory<EntityFornecedor, Integer>("CEPFornecedor"));

		TableColumn<EntityFornecedor, String> colenderecoFornecedor = new TableColumn<>("Endereço");
		colenderecoFornecedor
				.setCellValueFactory(new PropertyValueFactory<EntityFornecedor, String>("enderecoFornecedor"));

		TableColumn<EntityFornecedor, Integer> colnumeroEndereco = new TableColumn<>("Número");
		colnumeroEndereco.setCellValueFactory(new PropertyValueFactory<EntityFornecedor, Integer>("numeroEndereco"));

		TableColumn<EntityFornecedor, String> colcomplementoEndereco = new TableColumn<>("Complemento");
		colcomplementoEndereco
				.setCellValueFactory(new PropertyValueFactory<EntityFornecedor, String>("complementoEndereco"));
		colcomplementoEndereco.setMinWidth(100);

		tableView.getColumns().addAll(colNomeFornecedor, colCNPJFornecedor, colEmailFornecedor, coltelefoneFornecedor,
				colCEPFornecedor, colenderecoFornecedor, colnumeroEndereco, colcomplementoEndereco);
		tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<EntityFornecedor>() {

			@Override
			public void changed(ObservableValue<? extends EntityFornecedor> Fornecedor, EntityFornecedor antigo,
					EntityFornecedor novo) {
				entityToBoundary(novo);
			}
		});
	}

	@Override
	public void start(Stage stage) throws Exception {
		Pane pane = new Pane();
		Scene scn = new Scene(pane, 1000, 410);
		generateTable();
		Label lblFornecedor = new Label("Fornecedor: ");
		lblFornecedor.relocate(10, 10);

		txtNomeFornecedor.setMinWidth(190);
		txtNomeFornecedor.relocate(90, 10);

		Label lblCNPJFornecedor = new Label("CNPJ: ");
		lblCNPJFornecedor.relocate(10, 40);

		txtCNPJFornecedor.setMinWidth(230);
		txtCNPJFornecedor.relocate(90, 40);

		Label lblEmail = new Label("Email: ");
		lblEmail.relocate(10, 70);

		txtemailFornecedor.setMinWidth(230);
		txtemailFornecedor.relocate(90, 70);

		Label lblTelefoneFornecedor = new Label("Telefone: ");
		lblTelefoneFornecedor.relocate(10, 100);

		txtTelefoneFornecedor.setMinWidth(230);
		txtTelefoneFornecedor.relocate(90, 100);

		Label lblCEPFornecedor = new Label("CEP: ");
		lblCEPFornecedor.relocate(10, 130);

		txtCEPFornecedor.setMinWidth(230);
		txtCEPFornecedor.relocate(90, 130);

		Label lblEnderecoFornecedor = new Label("Endereço: ");
		lblEnderecoFornecedor.relocate(10, 160);

		txtEnderecoFornecedor.setMinWidth(230);
		txtEnderecoFornecedor.relocate(90, 160);

		Label lblNumeroEndereco = new Label("Numero: ");
		lblNumeroEndereco.relocate(10, 190);

		txtNumeroEndereco.setMinWidth(230);
		txtNumeroEndereco.relocate(90, 190);

		Label lblComplementoEndereco = new Label("Complemento: ");
		lblComplementoEndereco.relocate(10, 220);

		txtComplementoEndereco.setMinWidth(230);
		txtComplementoEndereco.relocate(90, 220);

		btnAdicionar.relocate(40, 250);
		btnAdicionar.setMinWidth(150);

		btnAlterar.relocate(180, 250);
		btnAlterar.setMinWidth(120);

		btnPesquisar.relocate(290, 10);
		btnPesquisar.setMinSize(30, 5);

		tableView.relocate(340, 10);

		pane.getChildren().addAll(tableView, lblFornecedor, txtNomeFornecedor, lblCNPJFornecedor, txtCNPJFornecedor,
				lblEmail, txtemailFornecedor, lblTelefoneFornecedor, txtTelefoneFornecedor, lblCEPFornecedor,
				txtCEPFornecedor, lblEnderecoFornecedor, txtEnderecoFornecedor, lblNumeroEndereco, txtNumeroEndereco,
				lblComplementoEndereco, txtComplementoEndereco, btnAdicionar, btnPesquisar, btnAlterar);

		btnAdicionar.setMinWidth(120);

		// Evento botão Pesquisar
		btnPesquisar.setOnAction(this);
		// Evento botão adicionar
		btnAdicionar.setOnAction(this);
		// Evento botão alterar
		btnAlterar.setOnAction(this);

		stage.setScene(scn);
		stage.setTitle("Gerenciar Fornecedores");
		stage.setX(100);
		stage.setY(50);
		stage.setResizable(false);
		stage.show();

		control.pesquisarPorNome("");
	}

	public void handle(ActionEvent e) {
		// Evento botao Adicionar
		if (e.getTarget() == btnAdicionar) {

			EntityFornecedor fornecedor = boundaryToEntity();
			control.adicionar(fornecedor);
			entityToBoundary(new EntityFornecedor());

			// Evento boao 'P' Pesquisar
		} else if (e.getTarget() == btnPesquisar) {

			String nomeFornecedor = txtNomeFornecedor.getText();
			EntityFornecedor fornecedor = control.pesquisarPorNome(nomeFornecedor);
			entityToBoundary(fornecedor);

		} else if (e.getTarget() == btnAlterar) {

			EntityFornecedor fornecedor = boundaryToEntity();
			control.alterar(fornecedor);
			entityToBoundary(new EntityFornecedor());
			// Evento boao 'P' Pesquisar
		}
	}

	public EntityFornecedor boundaryToEntity() {
		EntityFornecedor fornecedor = new EntityFornecedor();
		try {

			fornecedor.setIdFornecedor(Integer.parseInt(txtIdFornecedor.getText()));
			fornecedor.setNomeFornecedor(txtNomeFornecedor.getText());
			fornecedor.setCNPJFornecedor(txtCNPJFornecedor.getText());
			fornecedor.setEmailFornecedor(txtemailFornecedor.getText());
			fornecedor.setTelefoneFornecedor(Integer.parseInt(txtTelefoneFornecedor.getText()));
			fornecedor.setCEPFornecedor(Integer.parseInt(txtCEPFornecedor.getText()));
			fornecedor.setEnderecoFornecedor(txtEnderecoFornecedor.getText());
			fornecedor.setNumeroEndereco(Integer.parseInt(txtNumeroEndereco.getText()));
			fornecedor.setComplementoEndereco(txtComplementoEndereco.getText());

		} catch (Exception ex) {
			System.out.println("Erro ao computar os dados");
		}
		return fornecedor;
	}

	public void entityToBoundary(EntityFornecedor fornecedor) {
		if (fornecedor != null) {

			txtIdFornecedor.setText(String.valueOf(fornecedor.getIdFornecedor()));
			txtNomeFornecedor.setText(fornecedor.getNomeFornecedor());
			txtCNPJFornecedor.setText(fornecedor.getCNPJFornecedor());
			txtemailFornecedor.setText(fornecedor.getEmailFornecedor());
			txtTelefoneFornecedor.setText(String.valueOf(fornecedor.getTelefoneFornecedor()));
			txtCEPFornecedor.setText(String.valueOf(fornecedor.getCEPFornecedor()));
			txtEnderecoFornecedor.setText(fornecedor.getEnderecoFornecedor());
			txtNumeroEndereco.setText(String.valueOf(fornecedor.getNumeroEndereco()));
			txtComplementoEndereco.setText(fornecedor.getComplementoEndereco());
		}
	}

	public static void main(String[] args) {
		Application.launch(FornecedorBoundary.class, args);
	}
}