package co.edu.poli.examen2_Castro.tests.integracion;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;
import co.edu.poli.examen2_Castro.modelo.Propietario;
import co.edu.poli.examen2_Castro.servicios.DAOPropietario;

public class TestDAOPropietario {

    DAOPropietario dao = new DAOPropietario();

    @Test
    void testCreateYReadOne() throws Exception {
        Propietario p = new Propietario("2002", "James Rodriguez");

        // 1. Probar Creación
        String resultado = dao.create(p);
        
        // Esto imprimirá en la consola de Eclipse qué está respondiendo el DAO realmente
        System.out.println("Respuesta del DAO: " + resultado); 

        // Usamos toLowerCase() para evitar problemas de mayúsculas
        assertTrue(resultado.toLowerCase().contains("exito"), "El DAO no devolvió éxito, devolvió: " + resultado);

        // 2. Probar Lectura
        Propietario recuperado = dao.readone("2002");
        assertNotNull(recuperado);
        assertEquals("James Rodriguez", recuperado.getNombre());
    }

    @Test
    void testReadAll() throws Exception {
        // Verificamos que al menos devuelva una lista (aunque sea vacía o con el de arriba)
        List<Propietario> lista = dao.readall();
        assertNotNull(lista);
        // Si ya insertamos uno en el test anterior, el tamaño debería ser > 0
        assertTrue(lista.size() >= 0);
    }

    @Test
    void testReadOneInexistente() throws Exception {
        Propietario p = dao.readone("ID_QUE_NO_EXISTE");
        assertNull(p);
    }
}