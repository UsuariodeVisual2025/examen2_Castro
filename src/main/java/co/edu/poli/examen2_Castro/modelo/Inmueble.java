package co.edu.poli.examen2_Castro.modelo;

import java.time.LocalDate;

public abstract class Inmueble {

    // 1. Asegúrate de que estas variables existan arriba
    private int numero;
    private LocalDate fechaCompra;
    private String estado;
    private Propietario propietario;

    // 2. El constructor DEBE asignar los valores (this.x = x)
    public Inmueble(int numero, LocalDate fechaCompra, String estado, Propietario propietario) {
        this.numero = numero;           // <--- ESTO ES LO QUE TE FALTA
        this.fechaCompra = fechaCompra; // <--- SIN ESTO, LOS DATOS SE PIERDEN
        this.estado = estado;
        this.propietario = propietario;
    }

    // 3. Los Getters deben retornar la variable de la clase
    public int getNumero() { 
        return numero; 
    }
    
    public void setNumero(int numero) { 
        this.numero = numero; 
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }
}