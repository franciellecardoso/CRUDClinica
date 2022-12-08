package controller;

import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Consulta;
import persistence.ConsultaDAO;
import persistence.ConsultaDAOImpl;

public class ConsultaControl {

	private IntegerProperty id = new SimpleIntegerProperty();
	private StringProperty paciente = new SimpleStringProperty("");
	private StringProperty medico = new SimpleStringProperty("");
	private IntegerProperty clinica = new SimpleIntegerProperty();
	private StringProperty data = new SimpleStringProperty("");
	private StringProperty hora = new SimpleStringProperty("");
	private StringProperty observacao = new SimpleStringProperty("");

	private ConsultaDAO coDAO = new ConsultaDAOImpl();

	private ObservableList<Consulta> lista = FXCollections.observableArrayList();

	public Consulta getConsulta() {
		Consulta co = new Consulta();
		co.setId(id.get());
		co.setPaciente(paciente.get());
		co.setMedico(medico.get());
		co.setClinica(clinica.get());
		co.setData(data.get());
		co.setHora(hora.get());
		co.setObservacao(observacao.get());
		return co;
	}

	public void setConsulta(Consulta co) {
		id.set(co.getId());
		paciente.set(co.getPaciente());
		medico.set(co.getMedico());
		clinica.set(co.getClinica());
		data.set(co.getData());
		hora.set(co.getHora());
		observacao.set(co.getObservacao());
	}

	public void adicionar() {
		coDAO.criar(getConsulta());
	}

	public void pesquisar() {
		List<Consulta> tempLista = coDAO.pesquisarPorPaciente(paciente.get());
		lista.clear();
		lista.addAll(tempLista);
	}

	public void atualizar() {
		coDAO.atualizar(getConsulta());
	}
	
	public void apagar() {
		coDAO.apagar(getConsulta());
	}
	
	public void limpa() {
		id.set(0);
		paciente.set("");
		medico.set("");
		clinica.set(0);
		data.set("");
		hora.set("");
		observacao.set("");
	}
	
	public IntegerProperty idProperty() {
		return id;
	}
	
	public StringProperty pacienteProperty() {
		return paciente;
	}
	
	public StringProperty medicoProperty() {
		return medico;
	}
	
	public IntegerProperty clinicaProperty() {
		return clinica;
	}
	
	public StringProperty dataProperty() {
		return data;
	}
	
	public StringProperty horaProperty() {
		return hora;
	}
	
	public StringProperty observacaoProperty() {
		return observacao;
	}
	
	public ObservableList<Consulta> getLista() {
        return lista;
    }
}
