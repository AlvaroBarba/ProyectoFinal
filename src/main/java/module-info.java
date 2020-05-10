module com.alvaro.proyectofinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.alvaro.proyectofinal.controller to javafx.fxml;
    exports com.alvaro.proyectofinal;
}