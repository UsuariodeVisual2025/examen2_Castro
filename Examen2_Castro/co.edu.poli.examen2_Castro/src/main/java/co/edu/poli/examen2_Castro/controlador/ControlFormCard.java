package co.edu.poli.examen2_Castro.controlador;

import java.time.LocalDate;
import java.util.List;

import co.edu.poli.examen2_Castro.modelo.Apartamento;
import co.edu.poli.examen2_Castro.modelo.Casa;
import co.edu.poli.examen2_Castro.modelo.Inmueble;
import co.edu.poli.examen2_Castro.modelo.Propietario;
import co.edu.poli.examen2_Castro.servicios.DAOInmueble;
import co.edu.poli.examen2_Castro.servicios.DAOPropietario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

public class ControlFormCard {

    // Nombres ajustados para coincidir exactamente con el FXML
    @FXML private Button bttConsulta, bttCreacion;
    @FXML private TextField txtNumero1, txtNumero2; // Antes eran txtTarjeta1/2
    @FXML private TextArea txtAreaResultado;
    @FXML private DatePicker datepk1;
    @FXML private ComboBox<Propietario> cmbPropietario; // Antes era cmbTitular
    @FXML private RadioButton radio1, radio2;
    @FXML private ToggleGroup tipo;

    private DAOInmueble daoInmueble;
    private DAOPropietario daoPropietario;

    @FXML
    private void initialize() {
        daoInmueble = new DAOInmueble();
        daoPropietario = new DAOPropietario();

        datepk1.setValue(LocalDate.now());

        try {
            // Verificación de seguridad para evitar el error de "null"
            if (cmbPropietario != null) {
                List<Propietario> lista = daoPropietario.readall();
                cmbPropietario.getItems().setAll(lista);
            }
        } catch (Exception e) {
            System.out.println("Error al cargar propietarios: " + e.getMessage());
        }
    }

    @FXML
    private void pressConsulta(ActionEvent event) {
        txtAreaResultado.setText("");
        String input = txtNumero1.getText(); // Usando el nuevo nombre
        
        if (input != null && !input.isBlank()) {
            try {
                int numBusqueda = Integer.parseInt(input.trim());
                Inmueble t = daoInmueble.readone(numBusqueda);

                if (t != null)
                    txtAreaResultado.setText(t.toString());
                else
                    mostrarAlerta("No existe el inmueble número: " + numBusqueda);
            } catch (NumberFormatException e) {
                mostrarAlerta("Ingrese un número válido.");
            } catch (Exception e) {
                mostrarAlerta("Error: " + e.getMessage());
            }
        } else {
            mostrarAlerta("Ingrese número de inmueble para consultar.");
        }
    }

    @FXML
    private void pressCreacion(ActionEvent event) {
        try {
            // 1. Validar el número
            String inputNum = txtNumero2.getText();
            if (inputNum == null || inputNum.isBlank()) {
                mostrarAlerta("Ingrese un número de inmueble.");
                return;
            }
            int numero = Integer.parseInt(inputNum.trim());
            
            // 2. Obtener la fecha
            LocalDate fechaCompra = datepk1.getValue();
            
            // 3. Obtener el propietario
            Propietario propietario = cmbPropietario.getValue();
            
            if (propietario == null) {
                mostrarAlerta("⚠ Seleccione un propietario.");
                return;
            }

            Inmueble nuevo;

            // 4. Lógica según el tipo
            if (radio1.isSelected()) {
                nuevo = new Apartamento(numero, fechaCompra, "Disponible", propietario, 1);
            } else {
                nuevo = new Casa(numero, fechaCompra, "Disponible", propietario, 2);
            }

            String resultado = daoInmueble.create(nuevo);
            mostrarAlerta(resultado);
            
            // Si el DAO devuelve algo que indique éxito, limpiamos
            if (resultado.toLowerCase().contains("exito") || resultado.toLowerCase().contains("guardado")) {
                txtAreaResultado.setText("Guardado exitosamente: \n" + nuevo.toString());
            }
            
        } catch (NumberFormatException e) {
            mostrarAlerta("El número de inmueble debe ser numérico.");
        } catch (Exception e) {
            mostrarAlerta("Error: " + e.getMessage());
        }
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}