package Login;

import Cliente.ClienteBoundary;
import EntradaProdutos.EntradaBoundary;
import Estoque.EstoqueBoundary;
import Fornecedor.FornecedorBoundary;
import Funcionario.FuncionarioBoundary;
import Produto.ProdutoBoundary;
import Redefinir.RedefinirBoundary;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginBoundary extends Application implements EventHandler<ActionEvent> {
	private LoginControl control = new LoginControl();

	// Para utilizar o sistema é necessário ter o banco de dados

	private TextField txtIdUsuario = new TextField();
	private TextField txtUsuario = new TextField();
	private PasswordField txtSenha = new PasswordField();
	private TextField txtCargo = new TextField();

	private Label lblWelcome = new Label("Área de Login");
	private Label lblUsuario = new Label("Login: ");
	private Label lblSenha = new Label("Senha");

	private Button btnEntrar = new Button("Entrar");
	private Button btnRedefinir = new Button("Esqueceu a senha?");

	@Override
	public void start(Stage stage) throws Exception {
		Pane pane = new Pane();
		Scene scn = new Scene(pane, 600, 300);

		lblWelcome.relocate((600 - 150) / 2, 50);
		lblWelcome.setStyle("-fx-font-size: 25");

		lblUsuario.relocate(10, 10);

		lblUsuario.relocate((600 - 154) / 2, 100);
		txtUsuario.relocate((600 - 148) / 2, 120);

		lblSenha.relocate((600 - 154) / 2, 150);

		txtSenha.relocate((600 - 148) / 2, 170);

		btnRedefinir.relocate((600 - 148) / 2 - 45, 210);
		btnRedefinir.setMinWidth(120);

		double x = ((600 - 148) / 2 + 80);
		double y = (210);
		btnEntrar.relocate(x, y);
		btnEntrar.setMinWidth(120);

		pane.getChildren().addAll(lblWelcome, lblUsuario, txtUsuario, lblSenha, txtSenha, btnEntrar, btnRedefinir);

		// Evento botão Pesquisar
		btnEntrar.setOnAction(this);
		// Evento botão alterar
		btnRedefinir.setOnAction(this);

		stage.setScene(scn);
		stage.setTitle("Área De Login");
		stage.setX(300);
		stage.setY(200);
		stage.setResizable(false);
		stage.show();

	}

	public void handle(ActionEvent e) {
		// Evento botao Entrar
		if (e.getTarget() == btnEntrar) {

			String vetor[] = new String[2];
			vetor[0] = txtUsuario.getText();
			vetor[1] = txtSenha.getText();

			EntityLogin login = control.pesquisarPorNome(vetor);
			entityToBoundary(login);

			if (login.getCargo().equals("estoquista")) {
				EntradaBoundary entradaBondary = new EntradaBoundary();
				try {
					entradaBondary.start(new Stage());

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			} else if (login.getCargo().equals("farmaceutico")) {
				EstoqueBoundary estoqueBondary = new EstoqueBoundary();
				ProdutoBoundary produtoBondary = new ProdutoBoundary();
				
				try {
					estoqueBondary.start(new Stage());
					produtoBondary.start(new Stage());
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (login.getCargo().equals("gerente")) {
				FuncionarioBoundary funcionarioBondary = new FuncionarioBoundary();
				FornecedorBoundary fornecedorBondary = new FornecedorBoundary();
				try {
					funcionarioBondary.start(new Stage());
					fornecedorBondary.start(new Stage());

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (login.getCargo().equals("atendente")) {
				ClienteBoundary clienteBoundary = new ClienteBoundary();
				ProdutoBoundary produtoBondary = new ProdutoBoundary();
				
				try {
					clienteBoundary.start(new Stage());
					produtoBondary.start(new Stage());

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (login.getCargo().equals("admin")) {
				//todas as telas
				EstoqueBoundary estoqueBondary = new EstoqueBoundary();
				ClienteBoundary clienteBoundary = new ClienteBoundary();
				EntradaBoundary entradaBondary = new EntradaBoundary();
				FornecedorBoundary fornecedorBondary = new FornecedorBoundary();
				FuncionarioBoundary funcionarioBondary = new FuncionarioBoundary();
				ProdutoBoundary produtoBondary = new ProdutoBoundary();
				
				try {
					estoqueBondary.start(new Stage());
					clienteBoundary.start(new Stage());
					entradaBondary.start(new Stage());
					fornecedorBondary.start(new Stage());
					funcionarioBondary.start(new Stage());
					produtoBondary.start(new Stage());					
					

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (login.getCargo().equals("")) {

				// Alerta para campos vazios
				Alert esqueceuSenha = new Alert(AlertType.ERROR);

				esqueceuSenha.setTitle("Erro ao logar");
				esqueceuSenha.setHeaderText("Não foi possível iniciar sessão");

				esqueceuSenha.setContentText("Usuário ou Senha incorreta");
				esqueceuSenha.show();

			}
		} else if (e.getTarget() == btnRedefinir) {
			RedefinirBoundary frmRedefinir = new RedefinirBoundary();
			try {
				frmRedefinir.start(new Stage());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public EntityLogin boundaryToEntity() {
		EntityLogin login = new EntityLogin();
		try {

			login.setIdUsuario(Integer.parseInt(txtIdUsuario.getText()));
			login.setUsuario(txtUsuario.getText());
			login.setSenha(txtSenha.getText());
			login.setCargo(txtCargo.getText());

		} catch (Exception ex) {
			System.out.println("Erro ao computar os dados");
		}
		return login;
	}

	public void entityToBoundary(EntityLogin login) {
		if (login != null) {

			txtIdUsuario.setText(String.valueOf(login.getIdUsuario()));
			txtUsuario.setText(login.getUsuario());
			txtSenha.setText(login.getSenha());
			txtCargo.setText(login.getCargo());
		}
	}

	public static void main(String[] args) {
		Application.launch(LoginBoundary.class, args);

	}
}