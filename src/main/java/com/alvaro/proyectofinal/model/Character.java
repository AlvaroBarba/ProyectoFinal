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
public class Character {
    private String name;
    private int damage;
    private Item item;
    private int health;

    public Character(String name, int damage, Item item, int health) {
        this.name = name;
        this.damage = damage;
        this.item = item;
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
    
    
    
}
