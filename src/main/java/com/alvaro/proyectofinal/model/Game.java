/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.proyectofinal.model;

/**
 *
 * @author Alvaro
 */
public class Game {
    private Player player;
    private Character character;
    private int game_score;

    public Game(Player player, Character character, int game_score) {
        this.player = player;
        this.character = character;
        this.game_score = game_score;
    }
    
    

    public Game() {
        this.player = PlayerDAO.selected;
        this.character = CharacterDAO.choose;
        this.game_score = 0;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public int getGame_score() {
        return game_score;
    }

    public void setGame_score(int game_score) {
        this.game_score = game_score;
    }
    
       
    
}
