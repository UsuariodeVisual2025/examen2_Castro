package co.edu.poli.examen2_Castro.servicios;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import co.edu.poli.examen2_Castro.modelo.Apartamento;
import co.edu.poli.examen2_Castro.modelo.Casa;
import co.edu.poli.examen2_Castro.modelo.Inmueble;
import co.edu.poli.examen2_Castro.modelo.Propietario;

public class DAOInmueble implements CRUD<Inmueble> {

	@Override
	public String create(Inmueble t) throws Exception {
		Connection con = ConexionBD.getInstancia().getConexion();
		con.setAutoCommit(false);

		String SQL_INSERT_INMUEBLE = "INSERT INTO Inmueble (numero, fecha_compra, estado, propietario_id) VALUES (?, ?, ?, ?)";

		PreparedStatement ps = con.prepareStatement(SQL_INSERT_INMUEBLE);
		ps.setInt(1, t.getNumero());
		ps.setDate(2, Date.valueOf(t.getFechaCompra())); 
		ps.setString(3, t.getEstado());
		ps.setString(4, t.getPropietario().getId());
		ps.executeUpdate();

		String SQL_INSERT_APARTAMENTO = "INSERT INTO Apartamento (numero, num_piso) VALUES (?, ?)";
		String SQL_INSERT_CASA = "INSERT INTO Casa (numero, cant_pisos) VALUES (?, ?)";

		String sql = (t instanceof Apartamento) ? SQL_INSERT_APARTAMENTO : SQL_INSERT_CASA;
		ps = con.prepareStatement(sql);
		
		// ERROR 1: Tenías ps.setFloat. Debe ser setInt.
		ps.setInt(1, t.getNumero()); // <--- CORREGIDO
		
		if (t instanceof Apartamento)
			// ERROR 2: Verifica que en Apartamento.java el método sea getNumeroPiso()
			ps.setInt(2, ((Apartamento) t).getNumeroPiso()); 
		else
			// ERROR 3: Verifica que en Casa.java el método sea getCantidadPisos()
			ps.setInt(2, ((Casa) t).getCantidadPisos());
		
		try {
			ps.executeUpdate();
			con.commit();
			return "✔ " + t.getClass().getSimpleName() + " [" + t.getNumero() + "] guardado correctamente.";
		} catch (Exception e) {
			con.rollback();
			return "Error al guardar: " + e.getMessage();
		} finally {
			con.setAutoCommit(true);
		}
	}

	@Override
	public <K> Inmueble readone(K num) throws Exception {
		Connection con = ConexionBD.getInstancia().getConexion();
		
		// ERROR 4: El casting (Integer) num falla si pasas un String. Mejor usar parseInt.
		int idBusqueda = Integer.parseInt(num.toString()); // <--- CORREGIDO

		String SQL_SELECT_APTO = "SELECT i.*, p.nombre AS prop_nombre, a.num_piso FROM Apartamento a "
				+ "INNER JOIN Inmueble i ON a.numero = i.numero "
				+ "INNER JOIN Propietario p ON i.propietario_id = p.id WHERE a.numero = ?";

		PreparedStatement ps = con.prepareStatement(SQL_SELECT_APTO);
		ps.setInt(1, idBusqueda);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return new Apartamento(rs.getInt("numero"), rs.getDate("fecha_compra").toLocalDate(), 
					rs.getString("estado"), new Propietario(rs.getString("propietario_id"), rs.getString("prop_nombre")), 
					rs.getInt("num_piso"));
		}

		String SQL_SELECT_CASA = "SELECT i.*, p.nombre AS prop_nombre, c.cant_pisos FROM Casa c "
				+ "INNER JOIN Inmueble i ON c.numero = i.numero "
				+ "INNER JOIN Propietario p ON i.propietario_id = p.id WHERE c.numero = ?";

		ps = con.prepareStatement(SQL_SELECT_CASA);
		ps.setInt(1, idBusqueda);
		rs = ps.executeQuery();
		if (rs.next()) {
			return new Casa(rs.getInt("numero"), rs.getDate("fecha_compra").toLocalDate(), 
					rs.getString("estado"), new Propietario(rs.getString("propietario_id"), rs.getString("prop_nombre")), 
					rs.getInt("cant_pisos"));
		}
		return null;
	}

	@Override
	public List<Inmueble> readall() throws Exception { // <--- Agregado throws Exception para cumplir con CRUD
		return null;
	}
}