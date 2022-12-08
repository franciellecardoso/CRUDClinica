package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Medicamento;

public class MedicamentoDAOImpl implements MedicamentoDAO {

	public final static String URL = "jdbc:mariadb://localhost:3306/clinica";
	public final static String USER = "root";
	public final static String PASSWORD = "123456";
	private Connection con;

	public MedicamentoDAOImpl() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void criar(Medicamento me) {
		String sql = "INSERT INTO medicamento (id, nome) VALUES (?, ?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, me.getId());
			ps.setString(2, me.getNome());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Medicamento> pesquisarPorNome(String nome) {
		List<Medicamento> lista = new ArrayList<>();
		String sql = "SELECT * FROM medicamento WHERE nome LIKE '%" + nome + "%'";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Medicamento me = new Medicamento();
				me.setId(rs.getInt("id"));
				me.setNome(rs.getString("nome"));
				lista.add(me);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public void apagar(Medicamento me) {
		String sql = "DELETE FROM medicamento WHERE id= ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, me.getId());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void atualizar(Medicamento me) {
		String sql = "UPDATE medicamento SET nome= ? WHERE id= ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, me.getNome());
			ps.setInt(2, me.getId());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
