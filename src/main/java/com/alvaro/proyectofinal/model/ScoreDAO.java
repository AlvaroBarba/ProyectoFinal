/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.proyectofinal.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alvaro
 */
public class ScoreDAO extends Score{
     
    
    private static final String insert = "INSERT INTO scores (nick,"
            + " points) VALUES (?, 0)";
    private static final String delete = "delete from scores where nick=?";
    private static final String obtain = "Select * from scores";
    
    public static ArrayList<Score> getScore(java.sql.Connection con){
        PreparedStatement st = null;
        ResultSet rs = null;
        ArrayList<Score> scores = new ArrayList<>();
        if (con != null){
            try{
                st = con.prepareStatement(obtain);
                rs = st.executeQuery();
                
                while(rs != null && rs.next()){
                    Score a = null;
                    String nick = rs.getString("nick");
                    int points = rs.getInt("points");
                    
                    a.setNick(nick);
                    a.setScore(points);
                    
                    scores.add(a);
                    
                }
            }catch(SQLException e){
                Logger.getLogger(PlayerDAO.class.getName()).log(Level.SEVERE, null, e);
            }finally{
                if(rs != null){
                    try{
                        rs.close();
                    }catch(SQLException e){
                        Logger.getLogger(PlayerDAO.class.getName()).log(Level.SEVERE, null, e);
                    }
                }
            }
        }
        return scores;
    }
    
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
     
     public static boolean deleteScore(String nick, java.sql.Connection con) {
        boolean del = false;
        PreparedStatement st = null;
        if (con != null) {
            try {
                st = con.prepareStatement(delete);
                st.setString(1, nick);
                st.executeUpdate();
                del = true;
            } catch (SQLException e) {
                Logger.getLogger(PlayerDAO.class.getName()).log(Level.SEVERE, null, e);
            } finally {
                if (st != null) {
                    try {
                        st.close();
                    } catch (SQLException e) {
                        Logger.getLogger(PlayerDAO.class.getName()).log(Level.SEVERE, null, e);
                    }
                }
            }
        }
        return del;
    }

    public ScoreDAO(Player a, int score) {
        super(a, score);
    }
}
