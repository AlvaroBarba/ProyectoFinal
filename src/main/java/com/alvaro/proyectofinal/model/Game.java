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
    private Item item[];
    private int game_score;

    public Game(Player player, Character character, int game_score) {
        this.player = player;
        this.character = character;
        this.item = null;
        this.game_score = 0;
    }
    
    
    
}
