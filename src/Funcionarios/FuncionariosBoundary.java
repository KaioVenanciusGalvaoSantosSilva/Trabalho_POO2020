package Funcionarios;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FuncionariosBoundary extends Application implements EventHandler<ActionEvent> {

	private Label lblWelcome = new Label("Área de Login");
	private Label lblLogin = new Label("Email");
	private Label lblSenha = new Label("Senha");
	private Button btnRedefinir = new Button("Esqueceu a Senha?");
	private Button btnEntrar = new Button("Entrar");
	public String mudar = "";

	public void start(Stage stage) throws Exception {
		Pane pan = new Pane();
		Scene scn = new Scene(pan, 600, 300);

		lblWelcome.relocate((600 - 150) / 2, 50);
		lblWelcome.setStyle("-fx-font-size: 25");

		lblLogin.relocate((600 - 154) / 2, 100);

		TextField txt = new TextField();
		txt.relocate((600 - 148) / 2, 120);

		lblSenha.relocate((600 - 154) / 2, 150);

		TextField txtSenha = new TextField();
		txtSenha.relocate((600 - 148) / 2, 170);

		btnRedefinir.relocate((600 - 148) / 2 - 45, 210);
		btnRedefinir.setMinWidth(120);

		double x = ((600 - 148) / 2 + 80);
		double y = (210);
		btnEntrar.relocate(x, y);
		btnEntrar.setMinWidth(120);

		pan.getChildren().add(lblWelcome);
		pan.getChildren().add(lblLogin);
		pan.getChildren().add(lblSenha);
		pan.getChildren().add(txtSenha);
		pan.getChildren().add(btnRedefinir);
		pan.getChildren().add(btnEntrar);
		pan.getChildren().add(txt);

		btnEntrar.setOnAction(this);
		btnRedefinir.setOnAction(this);

		stage.setScene(scn);
		stage.show();

	}

	public void handle(ActionEvent e) {
		if (e.getTarget() == btnEntrar) {
			System.out.println("Entrou");
			// AQUI DEVE Acessar o "BondaryToEntity" pegar as informações de email e senha e
			// comparar com o banco de dados
			// Se for o mesmo que no banco, deve pegar o cargo no banco de dados (para
			// retornar a tela certa) retornar logado e acessar a janela do usuario
			// Caso não seja os dados certos retorne usuário incorreto, tente novamente

		} else if (e.getTarget() == btnRedefinir) {
			System.out.println("Redefinir senha");
			// AQUI DEVE ABRIR A JANELA "RedefinirBoundary"

		}
	}

	public static void main(String[] args) {
		Application.launch(FuncionariosBoundary.class, args);

	}
}
