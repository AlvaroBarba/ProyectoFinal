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
public class GameDAO extends Game{
    
    public GameDAO(Player player, Character character, int game_score) {
        super();
    }
    
     private static final String insert = "INSERT INTO game (nick,"
            + " character_name, points, character_item"
             + ") VALUES (?, ?, ?, ?)";
    private static final String update = "update game set nick=?, character_name=?,"
            + " points=? where nick=?";
    private static final String obtain = "Select * from game";
    
    public static ArrayList<Game> getGame(java.sql.Connection con){
        PreparedStatement st = null;
        ResultSet rs = null;
        ArrayList<Game> games = new ArrayList<>();
        ArrayList<Character> characters = new ArrayList<>();
        ArrayList<Player> players = new ArrayList<>();
        if (con != null){
            try{
                st = con.prepareStatement(obtain);
                rs = st.executeQuery();
                
                while(rs != null && rs.next()){
                    Game a = null;
                    a = new Game();
                    String character_name = rs.getString("character_name");
                    String nick = rs.getString("nick");
                    int points = rs.getInt("points");
                    
                    characters = CharacterDAO.getCharacters(con);
                    for(Character c : characters){
                        if(c.getName().equals(character_name)){
                            a.setCharacter(c);
                            break;
                        }
                    }
                    players = PlayerDAO.getPlayers(con);
                    for(Player p : players){
                        if(p.getNick().equals(nick)){
                            a.setPlayer(p);
                            break;
                        }
                    }
                    a.setGame_score(points);
                    
                    games.add(a);
                    
                }
            }catch(SQLException e){
                Logger.getLogger(GameDAO.class.getName()).log(Level.SEVERE, null, e);
            }finally{
                if(rs != null){
                    try{
                        rs.close();
                    }catch(SQLException e){
                        Logger.getLogger(GameDAO.class.getName()).log(Level.SEVERE, null, e);
                    }
                }
            }
        }
        return games;
    }
    
    public static boolean updateGame(Game a, java.sql.Connection con) {
        boolean updating = false;
        PreparedStatement st = null;
        if (con != null) {
            try {
                st = con.prepareStatement(update);
                st.setString(1, a.getPlayer().getNick());
                st.setString(2, a.getCharacter().getName());
                st.setInt(3, a.getGame_score());
                st.setString(4, a.getPlayer().getNick());
                
                st.executeUpdate();
                updating = true;
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
        return updating;
    }
    
     public static boolean insertGame(Player a, Character b,int points, java.sql.Connection con) {
        boolean insercion = false;
        PreparedStatement st = null;
        if (con != null) {
            try {
                st = con.prepareStatement(insert);
                st.setString(1, a.getNick());
                st.setString(2, b.getName());
                st.setInt(3, points);
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
