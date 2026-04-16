module co.edu.poli.examen2_Castro {
    // Agregamos 'static' o 'transitive' para resolver el error de accesibilidad
    requires transitive javafx.graphics; // 
    requires transitive javafx.controls; // 
    
    requires javafx.fxml;
    requires java.sql;
    requires javafx.base;

    // Abrimos los paquetes para que JavaFX pueda entrar a ellos
    opens co.edu.poli.examen2_Castro.vista to javafx.fxml;
    opens co.edu.poli.examen2_Castro.controlador to javafx.fxml;
    opens co.edu.poli.examen2_Castro.modelo to javafx.base;

    // Exportamos el paquete de la vista donde está App.java
    exports co.edu.poli.examen2_Castro.vista;
}