package com.alvaro.proyectofinal.controller;

import Utils.ConnectionUtil;
import java.io.IOException;
import javafx.fxml.FXML;
import com.alvaro.proyectofinal.App;
import com.alvaro.proyectofinal.model.Player;
import com.alvaro.proyectofinal.model.PlayerDAO;
import java.sql.Connection;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterController {

    @FXML
    public TextField name;
    public TextField email;
    public TextField nick;
    public PasswordField pass;
    public PasswordField rpass;

    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("login");
    }

    @FXML
    private void register() throws IOException {
        boolean result = false;
        Connection con = null;
        try{
            con = ConnectionUtil.getConnection();
        }catch(Exception e){
            showWarning("Error", "No se ha podido conectar a la Base de datos",
                    "Revise su conexión a internet");
        }
        String n = nick.getText();
        String e = email.getText();
        String na = name.getText();
        String p = pass.getText();
        String rp = rpass.getText();
        if (!p.equals(rp)) {
            showWarning("Error", "Contraseñas no coinciden",
                    "Vuelva a escribir las contraseñas, gracias");
        }else {
            Player a = new Player(n, na, e, p);
            result = (PlayerDAO.insertPlayer(a, con));
        }
        if(result){
            App.setRoot("mainMenu");
        }else{
            showWarning("Error", "No se ha podido crear el usuario", "Intentelo más tarde");
        }
    }
    
    public void showWarning(String title, String header, String description) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(description);
        alert.showAndWait();
    }

}
