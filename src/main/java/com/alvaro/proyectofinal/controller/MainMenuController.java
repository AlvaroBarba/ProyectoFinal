package com.alvaro.proyectofinal.controller;

import com.alvaro.proyectofinal.App;
import java.io.IOException;
import javafx.fxml.FXML;

public class MainMenuController {
    
   @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("login");
    }
    
    @FXML
    private void switchToCharacterSelect() throws IOException {
        App.setRoot("characterSelection");
    }
    
    @FXML
    private void switchToProfile() throws IOException {
        App.setRoot("profile");
    }
    
}
