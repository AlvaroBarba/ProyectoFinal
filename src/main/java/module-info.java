module com.alvaro.proyectofinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.xml.bind;
    requires java.base;

    opens com.alvaro.proyectofinal.model to java.xml.bind;
    opens com.alvaro.proyectofinal.controller to javafx.fxml;
    exports com.alvaro.proyectofinal;
}