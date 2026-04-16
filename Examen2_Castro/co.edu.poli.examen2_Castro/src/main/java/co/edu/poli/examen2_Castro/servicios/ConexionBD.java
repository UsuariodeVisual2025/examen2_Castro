package co.edu.poli.examen2_Castro.servicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

	private static ConexionBD instancia;
	private Connection conexion;

	private ConexionBD() throws Exception {
		// Datos de tu conexión local
		String url = "jdbc:mysql://127.0.0.1:3306/Examen2_Castro";
		String user = "camilo";
		String pass = "Admin123*";

		try {
			// Carga del Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Creación de la conexión
			conexion = DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException e) {
			throw new Exception("Error: No se encontró el Driver de MySQL. Verifica el JAR.");
		} catch (SQLException e) {
			throw new Exception("Error de SQL: " + e.getMessage());
		}
	}

	public static ConexionBD getInstancia() throws Exception {
		if (instancia == null) {
			instancia = new ConexionBD();
		}
		return instancia;
	}

	public Connection getConexion() throws Exception {
		// Si la conexión se cerró por tiempo de inactividad, la reabre
		if (conexion == null || conexion.isClosed()) {
			instancia = new ConexionBD();
			return instancia.conexion;
		}
		return conexion;
	}
}
