package Funcionario;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FuncionarioBoundary extends Application implements EventHandler<ActionEvent> {
	private FuncionarioControl control = new FuncionarioControl();

	private TextField txtIdFuncionario = new TextField();
	private TextField txtNomeFuncionario = new TextField();
	private TextField txtCPFFuncionario = new TextField();
	private TextField txtemailFuncionario = new TextField();
	private TextField txtTelefoneFuncionario = new TextField();
	private TextField txtCEPFuncionario = new TextField();
	private TextField txtEnderecoFuncionario = new TextField();
	private TextField txtNumeroEndereco = new TextField();
	private TextField txtComplementoEndereco = new TextField();
	private ComboBox<String> txtArea = new ComboBox<>(control.getAreas());
	private ComboBox<String> txtFuncao = new ComboBox<>(control.getFuncoes());

	private TextField txtDataAdmissao = new TextField();

	private TextField txtDataDesligamento = new TextField();

	private Button btnAdicionar = new Button("Adicionar");
	private Button btnPesquisar = new Button("P");
	private Button btnAlterar = new Button("Alterar");

	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	TableView<EntityFuncionario> tableView = new TableView<EntityFuncionario>(control.getLista());

	@SuppressWarnings("unchecked")
	public void generateTable() {
		TableColumn<EntityFuncionario, String> colNomeFuncionario = new TableColumn<>("Funcionario");
		colNomeFuncionario.setCellValueFactory(new PropertyValueFactory<EntityFuncionario, String>("nomeFuncionario"));

		TableColumn<EntityFuncionario, String> colCPFFuncionario = new TableColumn<>("CPF");
		colCPFFuncionario.setCellValueFactory(new PropertyValueFactory<EntityFuncionario, String>("CPFFuncionario"));

		TableColumn<EntityFuncionario, String> colEmailFuncionario = new TableColumn<>("Email");
		colEmailFuncionario
				.setCellValueFactory(new PropertyValueFactory<EntityFuncionario, String>("emailFuncionario"));

		TableColumn<EntityFuncionario, Integer> coltelefoneFuncionario = new TableColumn<>("Telefone");
		coltelefoneFuncionario
				.setCellValueFactory(new PropertyValueFactory<EntityFuncionario, Integer>("telefoneFuncionario"));

		TableColumn<EntityFuncionario, Integer> colCEPFuncionario = new TableColumn<>("CEP");
		colCEPFuncionario.setCellValueFactory(new PropertyValueFactory<EntityFuncionario, Integer>("CEPFuncionario"));

		TableColumn<EntityFuncionario, String> colenderecoFuncionario = new TableColumn<>("Endereço");
		colenderecoFuncionario
				.setCellValueFactory(new PropertyValueFactory<EntityFuncionario, String>("enderecoFuncionario"));

		TableColumn<EntityFuncionario, Integer> colnumeroEndereco = new TableColumn<>("Número");
		colnumeroEndereco.setCellValueFactory(new PropertyValueFactory<EntityFuncionario, Integer>("numeroEndereco"));

		TableColumn<EntityFuncionario, String> colcomplementoEndereco = new TableColumn<>("Complemento");
		colcomplementoEndereco
				.setCellValueFactory(new PropertyValueFactory<EntityFuncionario, String>("complementoEndereco"));
		colcomplementoEndereco.setMinWidth(100);

		TableColumn<EntityFuncionario, String> colAdmissão = new TableColumn<>("Admissão");
		colAdmissão
				.setCellValueFactory(item -> new ReadOnlyStringWrapper(dtf.format(item.getValue().getDataAdmissao())));

		TableColumn<EntityFuncionario, String> colDesligamento = new TableColumn<>("Desligamento");
		// colDesligamento.setCellValueFactory(
		// item -> new
		// ReadOnlyStringWrapper(dtf.format(item.getValue().getDataDesligamento())));
		colDesligamento.setMinWidth(90);

		tableView.getColumns().addAll(colNomeFuncionario, colCPFFuncionario, colEmailFuncionario,
				coltelefoneFuncionario, colCEPFuncionario, colenderecoFuncionario, colnumeroEndereco,
				colcomplementoEndereco, colAdmissão, colDesligamento);
		tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<EntityFuncionario>() {

			@Override
			public void changed(ObservableValue<? extends EntityFuncionario> Funcionario, EntityFuncionario antigo,
					EntityFuncionario novo) {
				entityToBoundary(novo);
			}
		});
	}

	@Override
	public void start(Stage stage) throws Exception {
		Pane pane = new Pane();
		Scene scn = new Scene(pane, 1180, 410);
		// 350,280
		generateTable();
		Label lblFuncionario = new Label("Funcionario: ");
		lblFuncionario.relocate(10, 10);

		txtNomeFuncionario.setMinWidth(190);
		txtNomeFuncionario.relocate(90, 10);

		Label lblCPFFuncionario = new Label("CPF: ");
		lblCPFFuncionario.relocate(10, 40);

		txtCPFFuncionario.setMinWidth(230);
		txtCPFFuncionario.relocate(90, 40);

		Label lblEmail = new Label("Email: ");
		lblEmail.relocate(10, 70);

		txtemailFuncionario.setMinWidth(230);
		txtemailFuncionario.relocate(90, 70);

		Label lblTelefoneFuncionario = new Label("Telefone: ");
		lblTelefoneFuncionario.relocate(10, 100);

		txtTelefoneFuncionario.setMinWidth(230);
		txtTelefoneFuncionario.relocate(90, 100);

		Label lblCEPFuncionario = new Label("CEP: ");
		lblCEPFuncionario.relocate(10, 130);

		txtCEPFuncionario.setMinWidth(230);
		txtCEPFuncionario.relocate(90, 130);

		Label lblEnderecoFuncionario = new Label("Endereço: ");
		lblEnderecoFuncionario.relocate(10, 160);

		txtEnderecoFuncionario.setMinWidth(230);
		txtEnderecoFuncionario.relocate(90, 160);

		Label lblNumeroEndereco = new Label("Numero: ");
		lblNumeroEndereco.relocate(10, 190);

		txtNumeroEndereco.setMinWidth(230);
		txtNumeroEndereco.relocate(90, 190);

		Label lblComplementoEndereco = new Label("Complemento: ");
		lblComplementoEndereco.relocate(10, 220);

		txtComplementoEndereco.setMinWidth(230);
		txtComplementoEndereco.relocate(90, 220);

		Label lblArea = new Label("Area: ");
		lblArea.relocate(10, 250);

		txtArea.setMinWidth(230);
		txtArea.relocate(90, 250);

		Label lblFuncao = new Label("Função: ");
		lblFuncao.relocate(10, 280);

		txtFuncao.setMinWidth(230);
		txtFuncao.relocate(90, 280);

		Label lblDataAdmissao = new Label("Adimissão: ");
		lblDataAdmissao.relocate(10, 310);

		txtDataAdmissao.setMinWidth(230);
		txtDataAdmissao.relocate(90, 310);

		Label lblDataDesligamento = new Label("Desligamento: ");
		lblDataDesligamento.relocate(10, 340);

		txtDataDesligamento.setMinWidth(230);
		txtDataDesligamento.relocate(90, 340);

		btnAdicionar.relocate(40, 380);
		btnAdicionar.setMinWidth(150);

		btnAlterar.relocate(180, 380);
		btnAlterar.setMinWidth(120);

		btnPesquisar.relocate(290, 10);
		btnPesquisar.setMinSize(30, 5);

		tableView.relocate(340, 10);

		pane.getChildren().addAll(tableView, lblFuncionario, txtNomeFuncionario, lblCPFFuncionario, txtCPFFuncionario,
				lblEmail, txtemailFuncionario, lblTelefoneFuncionario, txtTelefoneFuncionario, lblCEPFuncionario,
				txtCEPFuncionario, lblEnderecoFuncionario, txtEnderecoFuncionario, lblNumeroEndereco, txtNumeroEndereco,
				lblComplementoEndereco, txtComplementoEndereco, lblArea, txtArea, lblFuncao, txtFuncao, lblDataAdmissao,
				txtDataAdmissao, lblDataDesligamento, txtDataDesligamento, btnAdicionar, btnPesquisar, btnAlterar);

		btnAdicionar.setMinWidth(120);

		// Evento botão Pesquisar
		btnPesquisar.setOnAction(this);
		// Evento botão adicionar
		btnAdicionar.setOnAction(this);
		// Evento botão alterar
		btnAlterar.setOnAction(this);

		stage.setScene(scn);
		stage.setTitle("Gerenciar Funcionarios");
		stage.setX(50);
		stage.setY(50);
		stage.setResizable(false);
		stage.show();

		control.pesquisarPorNome("");

	}

	public void handle(ActionEvent e) {
		// Evento botao Adicionar
		if (e.getTarget() == btnAdicionar) {

			EntityFuncionario funcionario = boundaryToEntity();
			control.adicionar(funcionario);

			entityToBoundary(new EntityFuncionario());

			// Evento boao 'P' Pesquisar
		} else if (e.getTarget() == btnPesquisar) {

			String nomeFuncionario = txtNomeFuncionario.getText();
			EntityFuncionario funcionario = control.pesquisarPorNome(nomeFuncionario);
			entityToBoundary(funcionario);

		} else if (e.getTarget() == btnAlterar) {

			EntityFuncionario funcionario = boundaryToEntity();

			control.alterar(funcionario);
			entityToBoundary(new EntityFuncionario());
		}
	}

	public EntityFuncionario boundaryToEntity() {
		EntityFuncionario funcionario = new EntityFuncionario();

		try {

			funcionario.setIdFuncionario(Integer.parseInt(txtIdFuncionario.getText()));
			funcionario.setNomeFuncionario(txtNomeFuncionario.getText());
			funcionario.setCPFFuncionario(txtCPFFuncionario.getText());
			funcionario.setEmailFuncionario(txtemailFuncionario.getText());
			funcionario.setTelefoneFuncionario(Integer.parseInt(txtTelefoneFuncionario.getText()));
			funcionario.setCEPFuncionario(Integer.parseInt(txtCEPFuncionario.getText()));
			funcionario.setEnderecoFuncionario(txtEnderecoFuncionario.getText());
			funcionario.setNumeroEndereco(Integer.parseInt(txtNumeroEndereco.getText()));
			funcionario.setComplementoEndereco(txtComplementoEndereco.getText());
			funcionario.setArea(txtArea.getSelectionModel().getSelectedItem());
			funcionario.setFuncao(txtFuncao.getSelectionModel().getSelectedItem());
			LocalDate dt = LocalDate.parse(txtDataAdmissao.getText(), dtf);
			LocalDate dt2 = LocalDate.parse(txtDataDesligamento.getText(), dtf);
			funcionario.setDataAdmissao(dt);
			funcionario.setDataDesligamento(dt2);

		} catch (Exception ex) {
			System.out.println("Erro ao computar os dados");
		}
		return funcionario;
	}

	public void entityToBoundary(EntityFuncionario funcionario) {

		if (funcionario != null) {

			txtIdFuncionario.setText(String.valueOf(funcionario.getIdFuncionario()));
			txtNomeFuncionario.setText(funcionario.getNomeFuncionario());
			txtCPFFuncionario.setText(funcionario.getCPFFuncionario());
			txtemailFuncionario.setText(funcionario.getEmailFuncionario());
			txtTelefoneFuncionario.setText(String.valueOf(funcionario.getTelefoneFuncionario()));
			txtCEPFuncionario.setText(String.valueOf(funcionario.getCEPFuncionario()));
			txtEnderecoFuncionario.setText(funcionario.getEnderecoFuncionario());
			txtNumeroEndereco.setText(String.valueOf(funcionario.getNumeroEndereco()));
			txtComplementoEndereco.setText(funcionario.getComplementoEndereco());
			txtArea.getSelectionModel().select(funcionario.getArea());
			txtFuncao.getSelectionModel().select(funcionario.getFuncao());
			txtDataAdmissao.setText(dtf.format(funcionario.getDataAdmissao()));
			txtDataDesligamento.setText(dtf.format(funcionario.getDataDesligamento()));

		}
	}

	public static void main(String[] args) {

		Application.launch(FuncionarioBoundary.class, args);
	}
}