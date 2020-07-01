

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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PetBoundary extends Application implements EventHandler<ActionEvent>{
	private PetControl control = new PetControl();
	private TextField txtId = new TextField();
	private TextField txtNome = new TextField();
	private ComboBox<String> txtRaca = new ComboBox<>(control.getRacas());
	private TextField txtPeso = new TextField();
	private TextField txtNascimento = new TextField();
	
	private Button btnAdicionar = new Button("Adicionar");
	private Button btnPesquisar = new Button("Pesquisar");
	
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private TableView<Entity> tableView = new TableView<>(control.getLista());
	
	@SuppressWarnings("unchecked")
	public void generateTable() { 
		TableColumn<Entity, String> colNome = new TableColumn<>("Nome");
		colNome.setCellValueFactory(new PropertyValueFactory<Entity, String>("nome"));
		
		TableColumn<Entity, String> colRaca = new TableColumn<>("Raça");
		colRaca.setCellValueFactory(new PropertyValueFactory<Entity, String>("raca"));
		
		TableColumn<Entity, Double> colPeso = new TableColumn<>("Peso");
		colPeso.setCellValueFactory(new PropertyValueFactory<Entity, Double>("peso"));
		
		TableColumn<Entity, String> colNascimento = new TableColumn<>("Nascimento");
		colNascimento.setCellValueFactory(item -> 
			new ReadOnlyStringWrapper(dtf.format(item.getValue().getNascimento()))
		);			
		tableView.getColumns().addAll(colNome, colRaca, colPeso, colNascimento);
		tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Entity>() {
			@Override
			public void changed(ObservableValue<? extends Entity> p, Entity antigo, Entity novo) {
				entityToBoundary(novo);
			}
		});
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		BorderPane panPrincipal = new BorderPane();
		Scene scn = new Scene(panPrincipal, 400, 300);
		
		GridPane panCampos = new GridPane();
		generateTable();
		
		panCampos.add(new Label("Id: "), 0, 0);
		panCampos.add(txtId, 1, 0);
		panCampos.add(new Label("Nome: "), 0, 1);
		panCampos.add(txtNome, 1, 1);
		panCampos.add(new Label("Raça: "), 0, 2);
		panCampos.add(txtRaca, 1, 2);
		panCampos.add(new Label("Peso: "), 0, 3);
		panCampos.add(txtPeso, 1, 3);
		panCampos.add(new Label("Nascimento: "), 0, 4);
		panCampos.add(txtNascimento, 1, 4);
		panCampos.add(btnAdicionar, 0, 5);
		panCampos.add(btnPesquisar,  1,  5);
		
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
			Entity p = boundaryToEntity();
			control.adicionar(p);
			entityToBoundary(new Entity());
		} else if (e.getTarget() == btnPesquisar){ 
			String nome = txtNome.getText();
			Entity p = control.pesquisarPorNome(nome);
			entityToBoundary(p);
		}
	}
	
	public Entity boundaryToEntity() { 
		Entity p = new Entity();
		try { 
			p.setId( Long.parseLong(txtId.getText()) );
			p.setNome( txtNome.getText() );
			p.setRaca( txtRaca.getSelectionModel().getSelectedItem() );
			p.setPeso( Double.parseDouble(txtPeso.getText()) );
			LocalDate dt = LocalDate.parse(txtNascimento.getText(), dtf);
			p.setNascimento( dt );
		} catch (Exception ex) { 
			System.out.println("Erro ao computar os dados");
		}
		return p;
	}
	
	public void entityToBoundary(Entity p) { 
		if (p != null) {
			txtId.setText( String.valueOf(p.getId()) );
			txtNome.setText( p.getNome() );
			txtRaca.getSelectionModel().select( p.getRaca() );
			txtPeso.setText( String.valueOf(p.getPeso()) );
			txtNascimento.setText( dtf.format(p.getNascimento()) );
		}
	}
	
	public static void main(String[] args) {
		Application.launch(PetBoundary.class, args);
	}

}
