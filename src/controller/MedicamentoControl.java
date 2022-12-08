package controller;

import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Medicamento;
import persistence.MedicamentoDAO;
import persistence.MedicamentoDAOImpl;

public class MedicamentoControl {
	private IntegerProperty id = new SimpleIntegerProperty(0);
	private StringProperty nome = new SimpleStringProperty("");

	private MedicamentoDAO meDAO = new MedicamentoDAOImpl();

	private ObservableList<Medicamento> lista = FXCollections.observableArrayList();

	public Medicamento getMedicamento() {
		Medicamento me = new Medicamento();
		me.setId(id.get());
		me.setNome(nome.get());
		return me;
	}

	public void setMedicamento(Medicamento me) {
		id.set(me.getId());
		nome.set(me.getNome());
	}

	public void adicionar() {
		meDAO.criar(getMedicamento());
	}

	public void pesquisar() {
		List<Medicamento> tempLista = meDAO.pesquisarPorNome(nome.get());
		lista.clear();
		lista.addAll(tempLista);
	}

	public void atualizar() {
		meDAO.atualizar(getMedicamento());
	}

	public void apagar() {
		meDAO.apagar(getMedicamento());
		;
	}

	public void limpa() {
		id.set(0);
		nome.set("");
	}

	public IntegerProperty idProperty() {
		return id;
	}

	public StringProperty nomeProperty() {
		return nome;
	}

	public ObservableList<Medicamento> getLista() {
		return lista;
	}

}
