package co.edu.poli.examen2_Castro.modelo;

import java.time.LocalDate;

public class Casa extends Inmueble {

	private int cantidadPisos; // Antes era 'limite'

	public Casa(int numero, LocalDate fechaCompra, String estado, Propietario propietario, int cantidadPisos) {
		super(numero, fechaCompra, estado, propietario);
		this.cantidadPisos = cantidadPisos;
	}

	public int getCantidadPisos() { // Este es el método que el DAO busca
		return cantidadPisos;
	}

	public void setCantidadPisos(int cantidadPisos) {
		this.cantidadPisos = cantidadPisos;
	}

	@Override
	public String toString() {
		return "Casa [" + super.toString() + ", Pisos=" + cantidadPisos + "]";
	}
}