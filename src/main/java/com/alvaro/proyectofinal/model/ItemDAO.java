/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.proyectofinal.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alvaro
 */
public class ItemDAO extends Item{
    
    public ItemDAO(String name, String description, float modifier) {
        super(name, description, modifier);
    }
    
    private static final String insert = "INSERT INTO items (name,"
            + " description, modifier) VALUES (?, ?, ?)";
    
     public static boolean insertItem(Item c, java.sql.Connection con) {
        boolean insercion = false;
        PreparedStatement st = null;
        if (con != null) {
            try {
                st = con.prepareStatement(insert);
                st.setString(1, c.getName());
                st.setString(2, c.getDescription());
                st.setFloat(3, c.getModifier());
                st.executeUpdate();
                insercion = true;
            } catch (SQLException e) {
                Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, e);
            } finally {
                if (st != null) {
                    try {
                        st.close();
                    } catch (SQLException e) {
                        Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, e);
                    }
                }
            }
        }
        return insercion;
    }
    
}
