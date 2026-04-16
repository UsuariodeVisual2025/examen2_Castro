package co.edu.poli.examen2_Castro.vista;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        try {
            System.out.println("Iniciando carga de recursos...");

            /* * EXPLICACIÓN DE LA RUTA:
             * Si formCard.fxml está directamente en src/main/resources, usa "/formCard.fxml"
             * Si está dentro de una carpeta llamada 'vista', usa "/vista/formCard.fxml"
             */
            String rutaFxml = "/formCard.fxml"; 
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/co/edu/poli/examen2_Castro/formCard.fxml"));            
            if (fxmlLoader.getLocation() == null) {
                throw new Exception("No se encontró el archivo FXML en la ruta: " + rutaFxml);
            }

            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            
            System.out.println("Configurando escena y mostrar ventana...");
            stage.setTitle("Examen Gestión de Inmuebles - Castro");
            stage.setScene(scene);
            stage.setResizable(false); // Opcional: evita que deformen tu diseño
            stage.show();
            
            System.out.println("¡Aplicación cargada con éxito!");

        } catch (Exception e) {
            System.err.println("--- ERROR CRÍTICO AL INICIAR LA APLICACIÓN ---");
            System.err.println("Mensaje: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}