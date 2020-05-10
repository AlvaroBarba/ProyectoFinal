package com.alvaro.proyectofinal.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import com.alvaro.proyectofinal.App;

public class LoginController {

    @FXML
    private void switchToRegister() throws IOException {
        App.setRoot("register");
    }
    
    @FXML
    private void switchToMainMenu() throws IOException {
        App.setRoot("mainMenu");
    }
    
}
