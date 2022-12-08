package persistence;

import java.util.List;

import model.Medicamento;

public interface MedicamentoDAO {

	void criar(Medicamento me);

	void atualizar(Medicamento me);

	void apagar(Medicamento me);

	List<Medicamento> pesquisarPorNome(String nome);
}
