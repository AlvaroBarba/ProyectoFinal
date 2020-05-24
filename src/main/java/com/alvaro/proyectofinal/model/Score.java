/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.proyectofinal.model;

import java.util.Objects;

/**
 *
 * @author Alvaro
 */
public class Score {

    private String nick;
    private int score;

    public Score(Player a, int score) {
        this.nick = a.getNick();
        this.score = score;
    }

    public Score() {
        this.nick = "Default";
        this.score = 0;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Score other = (Score) obj;
        if (this.score != other.score) {
            return false;
        }
        if (!Objects.equals(this.nick, other.nick)) {
            return false;
        }
        return true;
    }

}
