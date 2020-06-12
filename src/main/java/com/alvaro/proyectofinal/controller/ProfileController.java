/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.proyectofinal.controller;

import com.alvaro.proyectofinal.App;
import com.alvaro.proyectofinal.model.Player;
import com.alvaro.proyectofinal.model.PlayerDAO;
import com.alvaro.proyectofinal.model.ScoreDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;

/**
 * FXML Controller class
 *
 * @author Alvaro
 */
public class ProfileController implements Initializable {

    @FXML
    private TableView<Player> table;
    @FXML
    private TableColumn<Player, String> nameColumn;
    @FXML
    private TableColumn<Player, String> nickColumn;
    @FXML
    private TableColumn<Player, String> emailColumn;
    public PasswordField actualpass;
    public PasswordField newpass;
    public PasswordField rnewpass;

    private ObservableList<Player> data;
    private Player select = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connection con = Utils.ConnectionUtil.getConnection();
        this.data = FXCollections.observableArrayList();
        data.add(PlayerDAO.selected);

        this.nameColumn.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getName());
        });
        this.nickColumn.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getNick());
        });
        this.emailColumn.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getEmail());
        });

        //Editables
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Player, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Player, String> t) {

                select = (Player) t.getTableView().getItems().get(
                        t.getTablePosition().getRow());

                select.setName(t.getNewValue());  //<<- update lista en vista

                PlayerDAO.updatePlayer(select, con);
            }
        }
        );
        emailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        emailColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Player, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Player, String> t) {

                select = (Player) t.getTableView().getItems().get(
                        t.getTablePosition().getRow());

                select.setEmail(t.getNewValue());  //<<- update lista en vista

                PlayerDAO.updatePlayer(select, con);
            }
        }
        );
        table.setEditable(true);
        table.setItems(data);

    }

    @FXML
    public void cambiarPass() {
        Connection con = Utils.ConnectionUtil.getConnection();
        if (actualpass.getText().equals(PlayerDAO.selected.getPassword())) {
            if (newpass.getText().equals(rnewpass.getText())) {
                select = PlayerDAO.selected;
                select.setPassword(newpass.getText());
                PlayerDAO.updatePlayer(select, con);
                showInformation("Contraseña cambiada", "Su contraseña se cambió satisfactoriamente");
            } else {
                showWarning("Error al cambiar contraseña", "Las contraseñas nuevas"
                        + " no coinciden", "Pruebe de nuevo, gracias");
            }
        } else {
            showWarning("Error al cambiar contraseña", "Las contraseña actual"
                    + " no es correcta", "Pruebe de nuevo, gracias");
        }
    }

    @FXML
    public void deleteAccount() throws IOException {
        Connection con = Utils.ConnectionUtil.getConnection();
        if (ScoreDAO.getScore(con) != null) {
            if (ScoreDAO.deleteScore(PlayerDAO.selected.getNick(), con)) {
                if (PlayerDAO.deletePlayer(PlayerDAO.selected.getNick(), con)) {
                    switchToLogin();
                } else {
                    showWarning("Error al borrar", "No se puede borrar los datos", "Ha ocurrido"
                            + " un error al borrar el usuario, intentelo más tarde");
                }
            } else {
                showWarning("Error al borrar", "No se puede borrar los datos", "Ha ocurrido"
                        + " un error al borrar el usuario, intentelo más tarde");
            }

        } else if (PlayerDAO.deletePlayer(PlayerDAO.selected.getNick(), con)) {
            switchToLogin();
        } else {
            showWarning("Error al borrar", "No se puede borrar los datos", "Ha ocurrido"
                    + " un error al borrar el usuario, intentelo más tarde");
        }
    }

    @FXML
    private void switchToMainMenu() throws IOException {
        App.setRoot("mainMenu");
    }

    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("login");
    }

    public void showWarning(String title, String header, String description) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(description);
        alert.showAndWait();
    }
    
    public void showInformation(String title, String header) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.showAndWait();
    }

}
