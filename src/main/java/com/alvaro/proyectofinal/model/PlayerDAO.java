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
public class PlayerDAO extends Player {
    
    public static Player selected;
    
    //Queris
    private static final String insert = "INSERT INTO players (name, nick,"
            + " email, password) VALUES (?, ?, ?, ?)";
    private static final String update = "update players set name=?, nick=?, email=?, password=? where nick=?";
    private static final String obtain = "Select * from players";
    private static final String delete = "delete from players where nick=?";

    public static boolean insertPlayer(Player a, java.sql.Connection con) {
        boolean insercion = false;
        PreparedStatement st = null;
        if (con != null) {
            try {
                st = con.prepareStatement(insert);
                st.setString(1, a.getName());
                st.setString(2, a.getNick());
                st.setString(3, a.getEmail());
                st.setString(4, a.getNick());
                st.executeUpdate();
                insercion = true;
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
        return insercion;
    }

    public static boolean updatePlayer(Player a, java.sql.Connection con) {
        boolean updating = false;
        PreparedStatement st = null;
        if (con != null) {
            try {
                st = con.prepareStatement(update);
                st.setString(1, a.getName());
                st.setString(2, a.getNick());
                st.setString(3, a.getEmail());
                st.setString(4, a.getPassword());
                st.setString(5, a.getNick());
                
                st.executeUpdate();
                updating = true;
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
        return updating;
    }
    
     public static ArrayList<Player> getPlayers(java.sql.Connection con){
        PreparedStatement st = null;
        ResultSet rs = null;
        ArrayList<Player> players = new ArrayList<>();
        if (con != null){
            try{
                st = con.prepareStatement(obtain);
                rs = st.executeQuery();
                
                while(rs != null && rs.next()){
                    Player a = null;
                    a = new Player();
                    String nick = rs.getString("nick");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    
                    a.setNick(nick);
                    a.setName(name);
                    a.setEmail(email);
                    a.setPassword(password);
                    
                    players.add(a);
                    
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
        return players;
    }
     
     public static boolean deletePlayer(String nick, java.sql.Connection con) {
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

}
