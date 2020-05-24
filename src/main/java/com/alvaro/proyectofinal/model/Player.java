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
public class Player extends Person {

    private String nick;
    private String email;
    private String password;

    public Player(String nick, String name, String email, String password) {
        super(name);
        this.nick = nick;
        this.email = email;
        this.password = password;
    }

    public Player() {
        super("Default");
        this.nick = "Default";
        this.email = "Default";
        this.password = "";
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
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
        final Player other = (Player) obj;
        if (!Objects.equals(this.nick, other.nick) && !Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Player{" + "nick=" + nick + ", email=" + email + ", password=" + password + '}';
    }

}
