/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.proyectofinal.controller;

import com.alvaro.proyectofinal.App;
import com.alvaro.proyectofinal.model.CharacterDAO;
import com.alvaro.proyectofinal.model.Game;
import com.alvaro.proyectofinal.model.GameDAO;
import com.alvaro.proyectofinal.model.PlayerDAO;
import com.alvaro.proyectofinal.model.Score;
import com.alvaro.proyectofinal.model.ScoreDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * FXML Controller class
 *
 * @author Alvaro
 */
public class GameController implements Initializable {

    @FXML
    private TableView<Game> table;
    @FXML
    private TableColumn<Game, String> characterColumn;
    @FXML
    private TableColumn<Game, String> itemColumn;
    @FXML
    private TableColumn<Game, Integer> pointsColumn;
    @FXML
    private TextFlow TFaux = new TextFlow();
    private ObservableList<Game> data;
    private ObservableList<Game> show;

    private Game partida = new Game(PlayerDAO.selected, CharacterDAO.choose, 0);
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connection con = Utils.ConnectionUtil.getConnection();
        GameDAO.insertGame(partida.getPlayer(), partida.getCharacter(), 0, con);
        this.data = FXCollections.observableArrayList();
        this.show = FXCollections.observableArrayList();
        data.addAll(GameDAO.getGame(con));
        for (Game a : data) {
            if (a.getPlayer().getNick().equals(PlayerDAO.selected.getNick())) {
                show.add(a);
            }
        }

        this.characterColumn.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getCharacter().getName());
        });
        this.itemColumn.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getCharacter().getItem().getName());
        });
        this.pointsColumn.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getGame_score());
        });
        table.setItems(show);
        try {
            iniMatch();
        } catch (InterruptedException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void iniMatch() throws InterruptedException, IOException {
        Connection con = Utils.ConnectionUtil.getConnection();
        String welcome = "Bienvenido a la mazmorra\n"
                + "¿Estás preparado para enfrentarte a tus peores pesadillas?";
        Text Twelcome = new Text(welcome);
        TFaux.getChildren().add(Twelcome);
        match();
    }

    @FXML
    private void match() throws InterruptedException, IOException {
        Connection con = Utils.ConnectionUtil.getConnection();
        int health = 0;
        int damage = 0;
        boolean win = false;
        Score score = hasScore(partida.getPlayer().getNick());
        String gameText = "";
        health = generateHealth();
        damage = generateDamage();
        gameText = "Te encuentras un enemigo\n"
                + "Salud = " + health + "\n"
                + "Daño = " + damage + "\n"
                + "Hora de LUCHAR!!\n";
        Text TgameText = new Text(gameText);
        TFaux.getChildren().add(TgameText);
        win = fight(partida.getCharacter(), health, damage);

        if (win) {
            score.setScore(score.getScore() + 10);
            ScoreDAO.updateScore(score, con);
            gameText = "Has Ganado!! Felicidades :)";
            TgameText = new Text(gameText);
            TFaux.getChildren().add(TgameText);
            partida.setGame_score(10);
            GameDAO.updateGame(partida, con);
        } else {
            gameText = "Has perdido, prueba otra vez cuando te atrevas";
            TgameText = new Text(gameText);
            TFaux.getChildren().add(TgameText);
            partida.setGame_score(0);
            GameDAO.updateGame(partida, con);
        }
    }
    
    private Score hasScore(String nick) {
        Connection con = Utils.ConnectionUtil.getConnection();
        Score result = null;
        ArrayList<Score> list = new ArrayList<>();
        list = ScoreDAO.getScore(con);
        for (Score a : list) {
            if (a.getNick().equals(nick)) {
                result = a;
                break;
            }
        }
        return result;
    }

    private boolean fight(com.alvaro.proyectofinal.model.Character inGame, int health, int damage) {
        boolean win = false;
        String gameText = "";
        Text TgameText = null;
        int playerAccuracy = 0;
        int iaAccuracy = 0;
        Random r = new Random();
        playerAccuracy = r.nextInt(101);
        if (playerAccuracy <= 75 && inGame.getHealth() > 0) {
            health -= inGame.getDamage();
            gameText = "Ataque con éxito\n"
                    + "Enemigo:\n"
                    + "Salud = " + health + "\n"
                    + "Daño= " + damage + "\n";
            TgameText = new Text(gameText);
            TFaux.getChildren().add(TgameText);
        } else if (playerAccuracy > 75 && inGame.getHealth() > 0) {
            gameText = "Fallo al realizar el ataque\n";
            TgameText = new Text(gameText);
            TFaux.getChildren().add(TgameText);
        }
        iaAccuracy = r.nextInt(101);
        if (iaAccuracy <= 65 && health > 0) {
            inGame.setHealth(inGame.getHealth() - damage);
            gameText = "Ataque de la ia con éxito\n"
                    + "Tus stats son:\n"
                    + "Salud = " + inGame.getHealth() + "\n";
            TgameText = new Text(gameText);
            TFaux.getChildren().add(TgameText);
        } else if (iaAccuracy > 65 && health > 0) {
            gameText = "La ia falló al realizar el ataque\n";
            TgameText = new Text(gameText);
            TFaux.getChildren().add(TgameText);
        }
        if (inGame.getHealth() > health) {
            win = true;
        }
        return win;
    }

    private int generateHealth() {
        Random r = new Random();
        int aux = CharacterDAO.choose.getHealth();
        int health = r.nextInt(aux + 50) + 1;
        return health;
    }

    private int generateDamage() {
        Random r = new Random();
        int aux = CharacterDAO.choose.getDamage();
        int damage = r.nextInt(aux + 50) + 1;
        return damage;
    }

    @FXML
    private void switchToMainMenu() throws IOException {
        App.setRoot("mainMenu");
    }

}
