/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.proyectofinal.controller;

import com.alvaro.proyectofinal.App;
import com.alvaro.proyectofinal.model.Item;
import com.alvaro.proyectofinal.model.ItemDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Alvaro
 */
public class ItemInfoController implements Initializable {

    @FXML
    private TableView<Item> table;
    @FXML
    private TableColumn<Item, String> nameColumn;
    @FXML
    private TableColumn<Item, String> descColumn;

    private ObservableList<Item> data;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connection con = Utils.ConnectionUtil.getConnection();
        this.data = FXCollections.observableArrayList();
        data.addAll(ItemDAO.getItems(con));

        this.nameColumn.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getName());
        });
        this.descColumn.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getDescription());
        });

        
        table.setEditable(true);
        //Indico que info tiene que renderizar la tabla
        table.setItems(data);

    }

    @FXML
    private void switchToMainMenu() throws IOException {
        App.setRoot("mainMenu");
    }

}
