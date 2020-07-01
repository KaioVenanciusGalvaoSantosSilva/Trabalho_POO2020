package Estoquista;

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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class EFornecedorBoundary extends Application implements EventHandler<ActionEvent> {
	private EFornecedorControl control = new EFornecedorControl();
	
	private TextField txtNomeFornecedor = new TextField();
	private TextField txtCNPJFornecedor = new TextField();
	private TextField txtemailFornecedor = new TextField();
	private TextField txtTelefoneFornecedor = new TextField();
	private TextField txtCEPFornecedor = new TextField();
	private TextField txtEnderecoFornecedor = new TextField();
	private TextField txtNumeroEndereco = new TextField();
	private TextField txtComplementoEndereco = new TextField();

	private Button btnAdicionar = new Button("Adicionar");
	private Button btnPesquisar = new Button("Pesquisar");

	private TableView<EEntityFornecedor> tableView = new TableView<>(control.getLista());

	@SuppressWarnings("unchecked")
	public void generateTable() {
		TableColumn<EEntityFornecedor, String> colNomeFornecedor = new TableColumn<>("Fornecedor");
		colNomeFornecedor.setCellValueFactory(new PropertyValueFactory<EEntityFornecedor, String>("nomeFornecedor"));

		TableColumn<EEntityFornecedor, String> colCNPJFornecedor = new TableColumn<>("CNPJ");
		colCNPJFornecedor.setCellValueFactory(new PropertyValueFactory<EEntityFornecedor, String>("CNPJFornecedor"));

		TableColumn<EEntityFornecedor, String> colEmailFornecedor= new TableColumn<>("Email");
		colEmailFornecedor.setCellValueFactory(new PropertyValueFactory<EEntityFornecedor, String>("emailFornecedor"));

		TableColumn<EEntityFornecedor, Integer> coltelefoneFornecedor = new TableColumn<>("Telefone");
		coltelefoneFornecedor.setCellValueFactory(new PropertyValueFactory<EEntityFornecedor, Integer>("telefoneFornecedor"));

		TableColumn<EEntityFornecedor, Integer> colCEPFornecedor = new TableColumn<>("CEP");
		colCEPFornecedor.setCellValueFactory(new PropertyValueFactory<EEntityFornecedor, Integer>("CEPFornecedor"));

		TableColumn<EEntityFornecedor, String> colenderecoFornecedor = new TableColumn<>("Endereço");
		colenderecoFornecedor.setCellValueFactory(new PropertyValueFactory<EEntityFornecedor, String>("enderecoFornecedor"));

		TableColumn<EEntityFornecedor, Integer> colnumeroEndereco = new TableColumn<>("Número");
		colnumeroEndereco.setCellValueFactory(new PropertyValueFactory<EEntityFornecedor, Integer>("numeroEndereco"));

		TableColumn<EEntityFornecedor, String> colcomplementoEndereco = new TableColumn<>("Complemento");
		colcomplementoEndereco.setCellValueFactory(new PropertyValueFactory<EEntityFornecedor, String>("complementoEndereco"));
		colcomplementoEndereco.setMinWidth(100);

		tableView.getColumns().addAll(colNomeFornecedor, colCNPJFornecedor, colEmailFornecedor, coltelefoneFornecedor,
				colCEPFornecedor, colenderecoFornecedor, colnumeroEndereco, colcomplementoEndereco);
		tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<EEntityFornecedor>() {

			@Override
			public void changed(ObservableValue<? extends EEntityFornecedor> Fornecedor, EEntityFornecedor antigo,
					EEntityFornecedor novo) {
				entityToBoundary(novo);
			}
		});
	}

	@Override
	public void start(Stage stage) throws Exception {
		BorderPane panPrincipal = new BorderPane();
		Scene scn = new Scene(panPrincipal, 662, 600);

		GridPane panCampos = new GridPane();
		generateTable();

		panCampos.add(new Label("Fornecedor: "), 0, 1);
		panCampos.add(txtNomeFornecedor, 1, 1);
		panCampos.add(new Label("CNPJ: "), 0, 2);
		panCampos.add(txtCNPJFornecedor, 1, 2);
		panCampos.add(new Label("Email: "), 2, 0);
		panCampos.add(txtemailFornecedor, 3, 0);
		panCampos.add(new Label("Telefone: "), 2, 1);
		panCampos.add(txtTelefoneFornecedor, 3, 1);
		panCampos.add(new Label("CEP: "), 2, 2);
		panCampos.add(txtCEPFornecedor, 3, 2);
		panCampos.add(new Label("Endereço: "), 4, 0);
		panCampos.add(txtEnderecoFornecedor, 5, 0);
		panCampos.add(new Label("Número: "), 4, 1);
		panCampos.add(txtNumeroEndereco, 5, 1);
		panCampos.add(new Label("Complemento: "), 4, 2);
		panCampos.add(txtComplementoEndereco, 5, 2);

		panCampos.add(btnAdicionar, 4, 3);
		panCampos.add(btnPesquisar, 5, 3);
		btnAdicionar.setMinWidth(120);
		btnPesquisar.setMinWidth(120);

		btnAdicionar.setOnAction(this);
		btnPesquisar.setOnAction(this);

		panPrincipal.setTop(panCampos);
		panPrincipal.setCenter(tableView);

		stage.setScene(scn);
		stage.setTitle("Estoque");
		stage.show();
	}

	public void handle(ActionEvent e) {
		if (e.getTarget() == btnAdicionar) {
			EEntityFornecedor fornecedor = boundaryToEntity();
			control.adicionar(fornecedor);
			entityToBoundary(new EEntityFornecedor());
		} else if (e.getTarget() == btnPesquisar) {
			
			String nomeFornecedor = txtNomeFornecedor.getText();
			EEntityFornecedor fornecedor = control.pesquisarPorNome(nomeFornecedor);
			entityToBoundary(fornecedor);
		}
	}

	public EEntityFornecedor boundaryToEntity() {
		EEntityFornecedor fornecedor = new EEntityFornecedor();
		try {
			
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

	public void entityToBoundary(EEntityFornecedor fornecedor) {
		if (fornecedor != null) {
			
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
		Application.launch(EFornecedorBoundary.class, args);
	}
}