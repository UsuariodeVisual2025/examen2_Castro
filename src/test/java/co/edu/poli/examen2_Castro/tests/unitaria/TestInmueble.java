package co.edu.poli.examen2_Castro.tests.unitaria;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;


import co.edu.poli.examen2_Castro.modelo.Apartamento;
import co.edu.poli.examen2_Castro.modelo.Casa;
import co.edu.poli.examen2_Castro.modelo.Inmueble;
import co.edu.poli.examen2_Castro.modelo.Propietario;

public class TestInmueble {

    // --- PRUEBAS DE ATRIBUTOS Y GETTERS ---

    @Test
    void testAtributosInmueble() {
        Propietario prop = new Propietario("123", "Camilo Castro");
        LocalDate fecha = LocalDate.now();
        Inmueble i = new Apartamento(555, fecha, "Disponible", prop, 3);

        assertEquals(555, i.getNumero());
        assertEquals(fecha, i.getFechaCompra());
        assertEquals("Disponible", i.getEstado());
        assertEquals(prop, i.getPropietario());
    }

    // --- PRUEBAS DE SETTERS (Modificación) ---

    @Test
    void testModificarEstado() {
        Propietario prop = new Propietario("123", "Camilo Castro");
        Inmueble i = new Apartamento(555, LocalDate.now(), "Disponible", prop, 3);

        i.setEstado("Vendido");
        assertEquals("Vendido", i.getEstado());
    }

    @Test
    void testCambioDePropietario() {
        Propietario prop1 = new Propietario("1", "Original");
        Propietario prop2 = new Propietario("2", "Nuevo");
        Inmueble i = new Casa(100, LocalDate.now(), "Nuevo", prop1, 2);

        i.setPropietario(prop2);
        assertEquals("2", i.getPropietario().getId());
        assertEquals("Nuevo", i.getPropietario().getNombre());
    }

    // --- PRUEBAS DE HERENCIA (Apartamento vs Casa) ---

    @Test
    void testDiferenciaHerencia() {
        Propietario prop = new Propietario("123", "Camilo");
        
        // Probamos atributo específico de Apartamento
        Apartamento apto = new Apartamento(101, LocalDate.now(), "Disponible", prop, 8);
        assertEquals(8, apto.getNumeroPiso());

        // Probamos atributo específico de Casa
        Casa casa = new Casa(202, LocalDate.now(), "Vendido", prop, 3);
        assertEquals(3, casa.getCantidadPisos());
    }

    // --- PRUEBAS DE COMPORTAMIENTO (ToString) ---

    @Test
    void testToStringContieneDatosClave() {
        Propietario prop = new Propietario("999", "Tester");
        Inmueble i = new Apartamento(101, LocalDate.of(2024, 10, 20), "Nuevo", prop, 1);
        
        String info = i.toString().toLowerCase();
        
        assertTrue(info.contains("101"), "Debe contener el número");
        assertTrue(info.contains("tester"), "Debe contener el nombre del propietario");
        assertTrue(info.contains("nuevo"), "Debe contener el estado");
    }

    // --- PRUEBAS DE PROPIETARIO ---

  
    
    @Test
    void testSettersPropietario() {
        Propietario p = new Propietario("old", "old");
        p.setId("newID");
        p.setNombre("newName");
        assertEquals("newID", p.getId());
        assertEquals("newName", p.getNombre());
    }
}