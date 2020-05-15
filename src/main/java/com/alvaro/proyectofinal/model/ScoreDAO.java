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
public class ScoreDAO extends Score{
     
    
    private static final String insert = "INSERT INTO scores (nick,"
            + " points) VALUES (?, 0)";
    
     public static boolean insertScore(Player a, java.sql.Connection con) {
        boolean insercion = false;
        PreparedStatement st = null;
        if (con != null) {
            try {
                st = con.prepareStatement(insert);
                st.setString(1, a.getNick());
                st.executeUpdate();
                insercion = true;
            } catch (SQLException e) {
                Logger.getLogger(ScoreDAO.class.getName()).log(Level.SEVERE, null, e);
            } finally {
                if (st != null) {
                    try {
                        st.close();
                    } catch (SQLException e) {
                        Logger.getLogger(ScoreDAO.class.getName()).log(Level.SEVERE, null, e);
                    }
                }
            }
        }
        return insercion;
    }

    public ScoreDAO(Player a, int score) {
        super(a, score);
    }
}
