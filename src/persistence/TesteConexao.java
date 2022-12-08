package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TesteConexao {

	public final static String URL = "jdbc:mariadb://localhost:3306/clinica";
	public final static String USER = "root";
	public final static String PASSWORD = "123456";

	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName("org.mariadb.jdbc.Driver");
		System.out.println("Classe carregada");

		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
			System.out.println("Conectado no banco de dados");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
