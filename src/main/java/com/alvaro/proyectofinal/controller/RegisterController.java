package com.alvaro.proyectofinal.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import com.alvaro.proyectofinal.App;

public class RegisterController {

    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("login");
    }
    
}