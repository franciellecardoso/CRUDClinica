package view;

import controller.ConsultaControl;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
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
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;
import model.Consulta;

public class ConsultaBoundary extends Application {

	private TextField txtId = new TextField("");
	private TextField txtPaciente = new TextField("");
	private TextField txtMedico = new TextField("");
	private TextField txtClinica = new TextField("");
	private TextField txtData = new TextField("");
	private TextField txtHora = new TextField("");
	private TextField txtObservacao = new TextField("");

	private ConsultaControl control = new ConsultaControl();

	private Button btnAdicionar = new Button("Adicionar");
	private Button btnPesquisar = new Button("Pesquisar");
	private Button btnAtualizar = new Button("Atualizar");
	private Button btnApagar = new Button("Apagar");

	private TableView<Consulta> table = new TableView<>();

	@Override
	public void start(Stage stage) throws Exception {
		BorderPane bp = new BorderPane();
		GridPane gp = new GridPane();

		Scene scn = new Scene(bp, 400, 300);
		bp.setTop(gp);
		bp.setCenter(table);

		prepararTable();

		gp.add(new Label("Id Consulta"), 0, 0);
		gp.add(txtId, 1, 0);
		gp.add(new Label("CPF Paciente"), 0, 1);
		gp.add(txtPaciente, 1, 1);
		gp.add(new Label("CRM Médico"), 0, 2);
		gp.add(txtMedico, 1, 2);
		gp.add(new Label("Id Clínica"), 0, 3);
		gp.add(txtClinica, 1, 3);
		gp.add(new Label("Data"), 0, 4);
		gp.add(txtData, 1, 4);
		gp.add(new Label("Hora"), 0, 5);
		gp.add(txtHora, 1, 5);
		gp.add(new Label("Observação"), 0, 6);
		gp.add(txtObservacao, 1, 6);

		gp.add(btnAdicionar, 0, 7);
		gp.add(btnPesquisar, 1, 7);
		gp.add(btnAtualizar, 2, 7);
		gp.add(btnApagar, 3, 7);

		btnAdicionar.setOnAction(e -> {
			control.adicionar();
			control.limpa();
			control.pesquisar();
		});

		btnPesquisar.setOnAction(e -> {
			control.pesquisar();
			control.limpa();
		});

		btnAtualizar.setOnAction(e -> {
			control.atualizar();
			control.limpa();
			control.pesquisar();
		});

		btnApagar.setOnAction(e -> {
			control.apagar();
			control.limpa();
			control.pesquisar();
		});

		vincular();

		stage.setScene(scn);
		stage.setTitle("Gestão de Consultas");
		stage.show();
	}

	private void prepararTable() {
		TableColumn<Consulta, Integer> col1 = new TableColumn<>("ID");
		col1.setCellValueFactory(new PropertyValueFactory<Consulta, Integer>("id"));

		TableColumn<Consulta, String> col2 = new TableColumn<>("PACIENTE");
		col2.setCellValueFactory(new PropertyValueFactory<Consulta, String>("paciente"));

		TableColumn<Consulta, String> col3 = new TableColumn<>("MEDICO");
		col3.setCellValueFactory(new PropertyValueFactory<Consulta, String>("medico"));

		TableColumn<Consulta, Integer> col4 = new TableColumn<>("CLINICA");
		col4.setCellValueFactory(new PropertyValueFactory<Consulta, Integer>("clinica"));

		TableColumn<Consulta, String> col5 = new TableColumn<>("DATA");
		col5.setCellValueFactory(new PropertyValueFactory<Consulta, String>("data"));

		TableColumn<Consulta, String> col6 = new TableColumn<>("HORA");
		col6.setCellValueFactory(new PropertyValueFactory<Consulta, String>("hora"));

		TableColumn<Consulta, String> col7 = new TableColumn<>("OBSERVACAO");
		col7.setCellValueFactory(new PropertyValueFactory<Consulta, String>("observacao"));

		table.getColumns().clear();
		table.getColumns().addAll(col1, col2, col3, col4, col5, col6, col7);
		table.setItems(control.getLista());

		table.getSelectionModel().selectedItemProperty().addListener((prop, antiga, novo) -> {
			control.setConsulta(novo);
		});
	}

	private void vincular() {
		StringConverter<? extends Number> converter = new NumberStringConverter();
		Bindings.bindBidirectional(txtId.textProperty(), control.idProperty(), (StringConverter<Number>) converter);
		Bindings.bindBidirectional(control.pacienteProperty(), txtPaciente.textProperty());
		Bindings.bindBidirectional(control.medicoProperty(), txtMedico.textProperty());
		Bindings.bindBidirectional(txtClinica.textProperty(), control.clinicaProperty(),
				(StringConverter<Number>) converter);
		Bindings.bindBidirectional(control.dataProperty(), txtData.textProperty());
		Bindings.bindBidirectional(control.horaProperty(), txtHora.textProperty());
		Bindings.bindBidirectional(control.observacaoProperty(), txtObservacao.textProperty());
	}

	public static void main(String[] args) {
		Application.launch(ConsultaBoundary.class, args);
	}
}
