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
public class Item {
    private String name;
    private String description;
    private float modifier;

    public Item(String name, String description, float modifier) {
        this.name = name;
        this.description = description;
        this.modifier = modifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getModifier() {
        return modifier;
    }

    public void setModifier(float modifier) {
        this.modifier = modifier;
    }
    
    
    
}
