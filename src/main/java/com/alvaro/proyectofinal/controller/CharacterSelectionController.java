/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.proyectofinal.controller;

import com.alvaro.proyectofinal.App;
import com.alvaro.proyectofinal.model.CharacterDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author Alvaro
 */
public class CharacterSelectionController implements Initializable {

    @FXML
    private TableView<com.alvaro.proyectofinal.model.Character> table;
    @FXML
    private TableColumn<com.alvaro.proyectofinal.model.Character, String> nameColumn;
    @FXML
    private TableColumn<com.alvaro.proyectofinal.model.Character, Integer> healthColumn;
    @FXML
    private TableColumn<com.alvaro.proyectofinal.model.Character, Integer> damageColumn;
    @FXML
    private TableColumn<com.alvaro.proyectofinal.model.Character, String> itemColumn;
    @FXML
    private TableColumn<com.alvaro.proyectofinal.model.Character, String> descColumn;

    private ObservableList<com.alvaro.proyectofinal.model.Character> data;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connection con = Utils.ConnectionUtil.getConnection();
        this.data = FXCollections.observableArrayList();
        data.addAll(CharacterDAO.getCharacters(con));

        this.nameColumn.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getName());
        });
        this.healthColumn.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getHealth());
        });
        this.damageColumn.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getDamage());
        });
        this.itemColumn.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getItem().getName());
        });
        this.descColumn.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getItem().getDescription());
        });
        table.setItems(data);
    }
    
    @FXML
    private void selectCharacter() throws IOException{
        CharacterDAO.choose = table.getSelectionModel().getSelectedItem();
        if(CharacterDAO.choose!=null){
            switchToGame();
        }else{
            showWarning("Personaje no seleccionado", "Olvidaste seleccionar"
                    + " personaje", "Seleccione un personaje antes de pulsar"
                            + " continuar, gracias");
        }
    }

    @FXML
    private void switchToMainMenu() throws IOException {
        App.setRoot("mainMenu");
    }
    @FXML
    private void switchToGame() throws IOException {
        App.setRoot("game");
    }
    
    public void showWarning(String title, String header, String description) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(description);
        alert.showAndWait();
    }

}
