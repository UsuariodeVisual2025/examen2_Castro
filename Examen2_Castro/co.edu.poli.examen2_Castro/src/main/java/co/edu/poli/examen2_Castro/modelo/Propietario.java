package co.edu.poli.examen2_Castro.modelo;

public class Propietario {

	private String id;
	private String nombre;

	public Propietario(String id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	// Esto es lo que aparecerá en el ComboBox de la interfaz gráfica
	@Override
	public String toString() {
		return nombre + " (" + id + ")";
	}
}