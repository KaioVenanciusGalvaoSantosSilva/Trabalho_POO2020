package Redefinir;

import Login.EntityLogin;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class RedefinirBoundary extends Application implements EventHandler<ActionEvent> {

	private Label lblWelcome = new Label("Redefinir Senha");
	private Label lblLogin = new Label("Email");
	private TextField txtUsuario = new TextField();
	private Button btnRedefinir2 = new Button("Redefinir");

	public void start(Stage stage) throws Exception {
		Pane pan = new Pane();
		Scene scn = new Scene(pan, 600, 300);

		lblWelcome.relocate((600 - 150) / 2 - 10, 50);
		lblWelcome.setStyle("-fx-font-size: 25");

		lblLogin.relocate((600 - 154) / 2, 100);
		txtUsuario.relocate((600 - 148) / 2, 120);

		double x = ((600 - 148) / 2 + 15);
		double y = (160);
		btnRedefinir2.relocate(x, y);
		btnRedefinir2.setMinWidth(120);

		pan.getChildren().add(lblWelcome);
		pan.getChildren().add(lblLogin);

		pan.getChildren().add(btnRedefinir2);
		pan.getChildren().add(txtUsuario);

		btnRedefinir2.setOnAction(this);

		stage.setScene(scn);
		stage.setTitle("Redefinir Senha");
		stage.setX(300);
		stage.setY(200);
		stage.setResizable(false);
		stage.show();
	}

	public EntityLogin boundaryToEntity() {
		EntityLogin login = new EntityLogin();
		try {

			login.setUsuario(txtUsuario.getText());

		} catch (Exception ex) {
			System.out.println("Erro ao computar os dados");
		}
		return login;
	}

	public void entityToBoundary(EntityLogin login) {
		if (login != null) {

			txtUsuario.setText(login.getUsuario());
		}
	}

	public void handle(ActionEvent e) {
		EntityLogin login = new EntityLogin();
		login.setUsuario(txtUsuario.getText());

		if (e.getTarget() == btnRedefinir2) {
			if (login.getUsuario().equals("")) {

				Alert preenchaAlert = new Alert(AlertType.WARNING);
				preenchaAlert.setTitle("Erro");
				preenchaAlert.setContentText("Preencha o  campo com seu email");
				preenchaAlert.setHeaderText("Falha ao enviar o email");
				preenchaAlert.show();

			} else {

				Alert preenchaAlert = new Alert(AlertType.CONFIRMATION);
				preenchaAlert.setTitle("Enviado");
				preenchaAlert.setContentText("Deseja enviar uma nova senha para seu email?");
				preenchaAlert.setHeaderText("Redefinir senha");
				preenchaAlert.show();
			}
		}
	}

	public static void main(String[] args) {
		Application.launch(RedefinirBoundary.class, args);
	}
}