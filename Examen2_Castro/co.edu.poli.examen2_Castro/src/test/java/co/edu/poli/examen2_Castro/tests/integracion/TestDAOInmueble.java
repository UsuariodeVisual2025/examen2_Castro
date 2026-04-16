package co.edu.poli.examen2_Castro.tests.integracion;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import co.edu.poli.examen2_Castro.modelo.Apartamento;
import co.edu.poli.examen2_Castro.modelo.Casa;
import co.edu.poli.examen2_Castro.modelo.Inmueble;
import co.edu.poli.examen2_Castro.modelo.Propietario;
import co.edu.poli.examen2_Castro.servicios.DAOInmueble;
import co.edu.poli.examen2_Castro.servicios.DAOPropietario;

public class TestDAOInmueble {

    DAOInmueble dao = new DAOInmueble();
    DAOPropietario daoProp = new DAOPropietario();

    @Test
    void create_apartamento_y_readone() throws Exception {
        // 1. Aseguramos el dueño (ID 2044)
        Propietario propietario = new Propietario("2044", "John Cardoso");
        try { daoProp.create(propietario); } catch (Exception e) {}

        // 2. Usamos un número que sepamos que no va a chocar (puedes cambiarlo si ya existe)
        int numApto = 888001; 

        Apartamento apartamento = new Apartamento(
                numApto, 
                LocalDate.of(2026, 04, 15), 
                "Disponible", 
                propietario, 
                6 
        );

        // Limpiamos antes de insertar por si acaso quedó de un test anterior
        // Esto depende de si tienes un método delete, si no, bórralo manual en MySQL
        String result = dao.create(apartamento);
        
        // Cambié assertTrue por una validación que ignore mayúsculas/minúsculas
        assertTrue(result.toLowerCase().contains("guardado") || result.toLowerCase().contains("exito"));

        // 3. Verificación de lectura
        Inmueble t = dao.readone(numApto);

        assertNotNull(t, "El inmueble no debería ser nulo");
        assertTrue(t instanceof Apartamento, "Debería ser una instancia de Apartamento");

        Apartamento d = (Apartamento) t;
        assertEquals(6, d.getNumeroPiso());
    }

    @Test
    void create_casa_y_readone() throws Exception {
        // Aseguramos el dueño (ID 2002)
        Propietario propietario = new Propietario("2002", "James Rodriguez");
        try { daoProp.create(propietario); } catch (Exception e) {}

        int numCasa = 888002; 

        Casa casa = new Casa(
                numCasa, 
                LocalDate.of(2026, 04, 15), 
                "Vendido", 
                propietario, 
                6 
        );

        String result = dao.create(casa);
        assertTrue(result.toLowerCase().contains("guardado") || result.toLowerCase().contains("exito"));

        Inmueble t = dao.readone(numCasa);

        assertNotNull(t);
        assertTrue(t instanceof Casa);

        Casa c = (Casa) t;
        assertEquals(6, c.getCantidadPisos());
    }

    @Test
    void readone_noExiste() throws Exception {
        // Buscamos un ID que sea imposible que exista
        Inmueble t = dao.readone(-1); 
        assertNull(t);
    }
}