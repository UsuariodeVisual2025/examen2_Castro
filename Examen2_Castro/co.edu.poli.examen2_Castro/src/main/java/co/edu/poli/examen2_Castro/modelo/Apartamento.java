package co.edu.poli.examen2_Castro.modelo;

import java.time.LocalDate;

public class Apartamento extends Inmueble {

	private int numeroPiso; // Cambiado de 'saldo' a 'numeroPiso'

	public Apartamento(int numero, LocalDate fechaCompra, String estado, Propietario propietario, int numeroPiso) {
		super(numero, fechaCompra, estado, propietario);
		this.numeroPiso = numeroPiso;
	}

	public int getNumeroPiso() { // Este es el método que el DAO estaba buscando
		return numeroPiso;
	}

	public void setNumeroPiso(int numeroPiso) {
		this.numeroPiso = numeroPiso;
	}

	@Override
	public String toString() {
		return "Apartamento [" + super.toString() + ", Piso=" + numeroPiso + "]";
	}
}