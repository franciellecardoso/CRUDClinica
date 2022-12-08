package view;

import controller.MedicamentoControl;
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
import model.Medicamento;

public class MedicamentoBoundary extends Application {

	private TextField txtId = new TextField("");
	private TextField txtNome = new TextField("");

	private MedicamentoControl control = new MedicamentoControl();

	private Button btnAdicionar = new Button("Adicionar");
	private Button btnPesquisar = new Button("Pesquisar");
	private Button btnAtualizar = new Button("Atualizar");
	private Button btnApagar = new Button("Apagar");

	private TableView<Medicamento> table = new TableView<>();

	@Override
	public void start(Stage stage) throws Exception {
		BorderPane bp = new BorderPane();
		GridPane gp = new GridPane();

		Scene scn = new Scene(bp, 400, 300);
		bp.setTop(gp);
		bp.setCenter(table);

		prepararTable();

		gp.add(new Label("Id Medicamento"), 0, 0);
		gp.add(txtId, 1, 0);
		gp.add(new Label("Nome"), 0, 1);
		gp.add(txtNome, 1, 1);

		gp.add(btnAdicionar, 0, 2);
		gp.add(btnPesquisar, 1, 2);
		gp.add(btnAtualizar, 2, 2);
		gp.add(btnApagar, 3, 2);

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
		stage.setTitle("Gestão de Medicamentos");
		stage.show();
	}

	private void prepararTable() {
		TableColumn<Medicamento, Integer> col1 = new TableColumn<>("ID");
		col1.setCellValueFactory(new PropertyValueFactory<Medicamento, Integer>("id"));

		TableColumn<Medicamento, String> col2 = new TableColumn<>("NOME");
		col2.setCellValueFactory(new PropertyValueFactory<Medicamento, String>("nome"));

		table.getColumns().clear();
		table.getColumns().addAll(col1, col2);
		table.setItems(control.getLista());

		table.getSelectionModel().selectedItemProperty().addListener((prop, antiga, novo) -> {
			control.setMedicamento(novo);
		});
	}

	private void vincular() {
		StringConverter<? extends Number> converter = new NumberStringConverter();
		Bindings.bindBidirectional(txtId.textProperty(), control.idProperty(), (StringConverter<Number>) converter);
		Bindings.bindBidirectional(control.nomeProperty(), txtNome.textProperty());
	}

	public static void main(String[] args) {
		Application.launch(MedicamentoBoundary.class, args);
	}
}
