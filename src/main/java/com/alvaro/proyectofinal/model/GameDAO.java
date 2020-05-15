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
public class GameDAO extends Game{
    
    public GameDAO(Player player, Character character, int game_score) {
        super(player, character, game_score);
    }
    
     private static final String insert = "INSERT INTO game (nick,"
            + " character_name, item_name, points, character_item"
             + ") VALUES (?, ?, ?, 0, ?)";
    
     public static boolean insertGame(Player a, Character b, Item c, java.sql.Connection con) {
        boolean insercion = false;
        PreparedStatement st = null;
        if (con != null) {
            try {
                st = con.prepareStatement(insert);
                st.setString(1, a.getNick());
                st.setString(2, b.getName());
                st.setString(3, c.getName());
                st.setString(4, b.getItem().getName());
                st.executeUpdate();
                insercion = true;
            } catch (SQLException e) {
                Logger.getLogger(GameDAO.class.getName()).log(Level.SEVERE, null, e);
            } finally {
                if (st != null) {
                    try {
                        st.close();
                    } catch (SQLException e) {
                        Logger.getLogger(GameDAO.class.getName()).log(Level.SEVERE, null, e);
                    }
                }
            }
        }
        return insercion;
    }
    
}
