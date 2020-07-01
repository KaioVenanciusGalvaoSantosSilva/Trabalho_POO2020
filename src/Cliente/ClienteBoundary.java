package Cliente;

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

public class ClienteBoundary extends Application implements EventHandler<ActionEvent> {
	private ClienteControl control = new ClienteControl();

	private TextField txtIdCliente = new TextField();
	private TextField txtNomeCliente = new TextField();
	private TextField txtCPFCliente = new TextField();
	private TextField txtemailCliente = new TextField();
	private TextField txtTelefoneCliente = new TextField();
	private TextField txtCEPCliente = new TextField();
	private TextField txtEnderecoCliente = new TextField();
	private TextField txtNumeroEndereco = new TextField();
	private TextField txtComplementoEndereco = new TextField();
	private Button btnAdicionar = new Button("Adicionar");
	private Button btnPesquisar = new Button("P");
	private Button btnAlterar = new Button("Alterar");

	TableView<EntityCliente> tableView = new TableView<EntityCliente>(control.getLista());

	@SuppressWarnings("unchecked")
	public void generateTable() {
		TableColumn<EntityCliente, String> colNomeCliente = new TableColumn<>("Cliente");
		colNomeCliente.setCellValueFactory(new PropertyValueFactory<EntityCliente, String>("nomeCliente"));

		TableColumn<EntityCliente, String> colCPFCliente = new TableColumn<>("CPF");
		colCPFCliente.setCellValueFactory(new PropertyValueFactory<EntityCliente, String>("CPFCliente"));

		TableColumn<EntityCliente, String> colEmailCliente = new TableColumn<>("Email");
		colEmailCliente.setCellValueFactory(new PropertyValueFactory<EntityCliente, String>("emailCliente"));

		TableColumn<EntityCliente, Integer> coltelefoneCliente = new TableColumn<>("Telefone");
		coltelefoneCliente.setCellValueFactory(new PropertyValueFactory<EntityCliente, Integer>("telefoneCliente"));

		TableColumn<EntityCliente, Integer> colCEPCliente = new TableColumn<>("CEP");
		colCEPCliente.setCellValueFactory(new PropertyValueFactory<EntityCliente, Integer>("CEPCliente"));

		TableColumn<EntityCliente, String> colenderecoCliente = new TableColumn<>("Endereço");
		colenderecoCliente.setCellValueFactory(new PropertyValueFactory<EntityCliente, String>("enderecoCliente"));

		TableColumn<EntityCliente, Integer> colnumeroEndereco = new TableColumn<>("Número");
		colnumeroEndereco.setCellValueFactory(new PropertyValueFactory<EntityCliente, Integer>("numeroEndereco"));

		TableColumn<EntityCliente, String> colcomplementoEndereco = new TableColumn<>("Complemento");
		colcomplementoEndereco
				.setCellValueFactory(new PropertyValueFactory<EntityCliente, String>("complementoEndereco"));
		colcomplementoEndereco.setMinWidth(100);

		tableView.getColumns().addAll(colNomeCliente, colCPFCliente, colEmailCliente, coltelefoneCliente, colCEPCliente,
				colenderecoCliente, colnumeroEndereco, colcomplementoEndereco);
		tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<EntityCliente>() {

			@Override
			public void changed(ObservableValue<? extends EntityCliente> Cliente, EntityCliente antigo,
					EntityCliente novo) {
				entityToBoundary(novo);
			}
		});
	}

	@Override
	public void start(Stage stage) throws Exception {
		Pane pane = new Pane();
		Scene scn = new Scene(pane, 1000, 410);
		// 350,280
		generateTable();
		Label lblCliente = new Label("Cliente: ");
		lblCliente.relocate(10, 10);

		txtNomeCliente.setMinWidth(190);
		txtNomeCliente.relocate(90, 10);

		Label lblCPFCliente = new Label("CPF: ");
		lblCPFCliente.relocate(10, 40);

		txtCPFCliente.setMinWidth(230);
		txtCPFCliente.relocate(90, 40);

		Label lblEmail = new Label("Email: ");
		lblEmail.relocate(10, 70);

		txtemailCliente.setMinWidth(230);
		txtemailCliente.relocate(90, 70);

		Label lblTelefoneCliente = new Label("Telefone: ");
		lblTelefoneCliente.relocate(10, 100);

		txtTelefoneCliente.setMinWidth(230);
		txtTelefoneCliente.relocate(90, 100);

		Label lblCEPCliente = new Label("CEP: ");
		lblCEPCliente.relocate(10, 130);

		txtCEPCliente.setMinWidth(230);
		txtCEPCliente.relocate(90, 130);

		Label lblEnderecoCliente = new Label("Endereço: ");
		lblEnderecoCliente.relocate(10, 160);

		txtEnderecoCliente.setMinWidth(230);
		txtEnderecoCliente.relocate(90, 160);

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

		pane.getChildren().addAll(tableView, lblCliente, txtNomeCliente, lblCPFCliente, txtCPFCliente, lblEmail,
				txtemailCliente, lblTelefoneCliente, txtTelefoneCliente, lblCEPCliente, txtCEPCliente,
				lblEnderecoCliente, txtEnderecoCliente, lblNumeroEndereco, txtNumeroEndereco, lblComplementoEndereco,
				txtComplementoEndereco, btnAdicionar, btnPesquisar, btnAlterar);

		btnAdicionar.setMinWidth(120);

		// Evento botão Pesquisar
		btnPesquisar.setOnAction(this);
		// Evento botão adicionar
		btnAdicionar.setOnAction(this);
		// Evento botão alterar
		btnAlterar.setOnAction(this);

		stage.setScene(scn);
		stage.setTitle("Gerenciar Clientes");
		stage.setX(100);
		stage.setY(50);
		stage.setResizable(false);
		stage.show();

		control.pesquisarPorNome("");
	}

	public void handle(ActionEvent e) {
		// Evento botao Adicionar
		if (e.getTarget() == btnAdicionar) {

			EntityCliente cliente = boundaryToEntity();
			control.adicionar(cliente);
			entityToBoundary(new EntityCliente());

			// Evento boao 'P' Pesquisar
		} else if (e.getTarget() == btnPesquisar) {

			String nomeCliente = txtNomeCliente.getText();
			EntityCliente cliente = control.pesquisarPorNome(nomeCliente);
			entityToBoundary(cliente);

		} else if (e.getTarget() == btnAlterar) {

			EntityCliente cliente = boundaryToEntity();
			control.alterar(cliente);
			entityToBoundary(new EntityCliente());

			// Evento boao 'P' Pesquisar
		}
	}

	public EntityCliente boundaryToEntity() {
		EntityCliente cliente = new EntityCliente();
		try {

			cliente.setIdCliente(Integer.parseInt(txtIdCliente.getText()));
			cliente.setNomeCliente(txtNomeCliente.getText());
			cliente.setCPFCliente(txtCPFCliente.getText());
			cliente.setEmailCliente(txtemailCliente.getText());
			cliente.setTelefoneCliente(Integer.parseInt(txtTelefoneCliente.getText()));
			cliente.setCEPCliente(Integer.parseInt(txtCEPCliente.getText()));
			cliente.setEnderecoCliente(txtEnderecoCliente.getText());
			cliente.setNumeroEndereco(Integer.parseInt(txtNumeroEndereco.getText()));
			cliente.setComplementoEndereco(txtComplementoEndereco.getText());

		} catch (Exception ex) {
			System.out.println("Erro ao computar os dados");
		}
		return cliente;
	}

	public void entityToBoundary(EntityCliente cliente) {
		if (cliente != null) {

			txtIdCliente.setText(String.valueOf(cliente.getIdCliente()));
			txtNomeCliente.setText(cliente.getNomeCliente());
			txtCPFCliente.setText(cliente.getCPFCliente());
			txtemailCliente.setText(cliente.getEmailCliente());
			txtTelefoneCliente.setText(String.valueOf(cliente.getTelefoneCliente()));
			txtCEPCliente.setText(String.valueOf(cliente.getCEPCliente()));
			txtEnderecoCliente.setText(cliente.getEnderecoCliente());
			txtNumeroEndereco.setText(String.valueOf(cliente.getNumeroEndereco()));
			txtComplementoEndereco.setText(cliente.getComplementoEndereco());
		}
	}

	public static void main(String[] args) {
		System.out.println("imprimi");
		Application.launch(ClienteBoundary.class, args);
	}
}