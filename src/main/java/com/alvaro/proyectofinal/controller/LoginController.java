package com.alvaro.proyectofinal.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import com.alvaro.proyectofinal.App;
import com.alvaro.proyectofinal.model.Player;
import com.alvaro.proyectofinal.model.PlayerDAO;
import java.sql.Connection;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    
    @FXML
    public TextField nick;
    public PasswordField password;
    
    @FXML
    private void login() throws IOException{
        boolean err = true;
        Connection con = Utils.ConnectionUtil.getConnection();
        ArrayList<Player> list = PlayerDAO.getPlayers(con);
        for(Player a : list){
            if(a.getNick().equals(nick.getText()) && a.getPassword().equals(password.getText())){
                switchToMainMenu();
                err = false;
                PlayerDAO.selected = a;
                break;
            }
        }
        if(err){
             showWarning("Error en login", "Los datos son incorrectos",
                        "Intentelo de nuevo o registrese para poder usar la aplicacion");
        }
    }

    @FXML
    private void switchToRegister() throws IOException {
        App.setRoot("register");
    }
    
    @FXML
    private void switchToMainMenu() throws IOException {
        App.setRoot("mainMenu");
    }
    
    public void showWarning(String title, String header, String description) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(description);
        alert.showAndWait();
    }
    
}
