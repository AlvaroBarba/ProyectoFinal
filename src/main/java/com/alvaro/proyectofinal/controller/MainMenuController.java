package com.alvaro.proyectofinal.controller;

import com.alvaro.proyectofinal.App;
import java.io.IOException;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class MainMenuController {
    
    @FXML
    private void exit(){
        boolean result = showConfirmation("Salir", "¿Seguro que quiere salir?");
        if(result){
            System.exit(0);
        }
    }
    
   @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("login");
    }
    
    @FXML
    private void switchToObjInfo() throws IOException{
        App.setRoot("itemInfo");
    }
    
    @FXML
    private void switchToLeaderboard() throws IOException{
        App.setRoot("pointsTable");
    }
    
    @FXML
    private void switchToCharacterSelect() throws IOException {
        App.setRoot("characterSelection");
    }
    
    @FXML
    private void switchToProfile() throws IOException {
        App.setRoot("profile");
    }
    
    public boolean showConfirmation(String title, String header) {
        boolean result = false;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        Optional<ButtonType> option = alert.showAndWait();
        if(option.get() == ButtonType.OK){
            result = true;
        }
        return result;
    }
    
}
