package co.edu.poli.examen2_Castro.servicios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import co.edu.poli.examen2_Castro.modelo.Propietario;

public class DAOPropietario implements CRUD<Propietario> {

	@Override
	public String create(Propietario t) throws Exception {
		Connection con = ConexionBD.getInstancia().getConexion();
		
		String SQL_INSERT_PROPIETARIO = "INSERT INTO Propietario (id, nombre) VALUES (?, ?)";
		
		try (PreparedStatement ps = con.prepareStatement(SQL_INSERT_PROPIETARIO)) {
			ps.setString(1, t.getId());
			ps.setString(2, t.getNombre());
			ps.executeUpdate();
			return "Propietario " + t.getNombre() + " registrado con éxito.";
		} catch (Exception e) {
			return "Error al registrar propietario: " + e.getMessage();
		}
	}
	
	@Override
	public <K> Propietario readone(K id) throws Exception {
		Connection con = ConexionBD.getInstancia().getConexion();
		
		String SQL_SELECT_ONE = "SELECT id, nombre FROM Propietario WHERE id = ?";
		
		PreparedStatement ps = con.prepareStatement(SQL_SELECT_ONE);
		ps.setString(1, (String) id);
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			return new Propietario(rs.getString("id"), rs.getString("nombre"));
		}
		return null;
	}

	@Override
	public List<Propietario> readall() throws Exception {
		Connection con = ConexionBD.getInstancia().getConexion();
		List<Propietario> lista = new ArrayList<>();

		// Ajustado a tu tabla 'Propietario'
		String SQL_SELECT_PROPIETARIO = "SELECT id, nombre FROM Propietario";

		PreparedStatement ps = con.prepareStatement(SQL_SELECT_PROPIETARIO);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			// Usamos los nombres de columna de tu tabla
			Propietario p = new Propietario(
				rs.getString("id"), 
				rs.getString("nombre")
			);
			lista.add(p);
		}
		return lista;
	}
}
