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

public class RedefinirBoundary extends Application implements EventHandler<ActionEvent> {

	private Label lblWelcome = new Label("Redefinir Senha");
	private Label lblLogin = new Label("Email");
	private Button btnRedefinir2 = new Button("Redefinir");

	public void start(Stage stage) throws Exception {
		Pane pan = new Pane();
		Scene scn = new Scene(pan, 600, 300);

		lblWelcome.relocate((600 - 150) / 2 - 10, 50);
		lblWelcome.setStyle("-fx-font-size: 25");

		lblLogin.relocate((600 - 154) / 2, 100);

		TextField txt = new TextField();
		txt.relocate((600 - 148) / 2, 120);

		TextField txtSenha = new TextField();
		txtSenha.relocate((600 - 148) / 2, 170);

		double x = ((600 - 148) / 2 + 15);
		double y = (160);
		btnRedefinir2.relocate(x, y);
		btnRedefinir2.setMinWidth(120);

		pan.getChildren().add(lblWelcome);
		pan.getChildren().add(lblLogin);

		pan.getChildren().add(btnRedefinir2);
		pan.getChildren().add(txt);

		btnRedefinir2.setOnAction(this);

		stage.setScene(scn);
		stage.show();
	}

	public void handle(ActionEvent e) {
		if (e.getTarget() == btnRedefinir2) {
			System.out.println("Sua nova senha foi enviada para seu email");
			// Pega o email que foi digitado e compara se tem algum email com essa
			// credencial e envia a nova senha para o mesmo (email deve ser unico)
			// Exibe a mensagem: "Sua nova senha foi enviada para seu email"

		} else {
			System.out.println("Esse email não existe");
			// Se o email for diferente exibe: "Esse email não existe".
		}

	}

	public static void main(String[] args) {
		Application.launch(RedefinirBoundary.class, args);
	}
}
