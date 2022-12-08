package persistence;

import java.util.List;

import model.Consulta;

public interface ConsultaDAO {

	void criar(Consulta co);

	void atualizar(Consulta co);

	void apagar(Consulta co);

	List<Consulta> pesquisarPorPaciente(String paciente);
}
