/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.proyectofinal.controller;

import com.alvaro.proyectofinal.App;
import com.alvaro.proyectofinal.model.Item;
import com.alvaro.proyectofinal.model.ItemDAO;
import com.alvaro.proyectofinal.model.Score;
import com.alvaro.proyectofinal.model.ScoreDAO;
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
 *
 * @author Alvaro
 */
public class PointsTableController implements Initializable{
    
     @FXML
    private TableView<Score> table;
    @FXML
    private TableColumn<Score, String> nickColumn;
    @FXML
    private TableColumn<Score, Integer> pointsColumn;

    private ObservableList<Score> data;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connection con = Utils.ConnectionUtil.getConnection();
        this.data = FXCollections.observableArrayList();
        data.addAll(ScoreDAO.getScoreOrder(con));

        this.nickColumn.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getNick());
        });
        this.pointsColumn.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getScore());
        });

        table.setItems(data);

    }
    
    @FXML
    private void switchToMainMenu() throws IOException {
        App.setRoot("mainMenu");
    }
    
}
