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
public class CharacterDAO extends Character{
    
    public static Character choose;
    
    private static final String insert = "INSERT INTO characters (name, damage,"
            + " start_item, health) VALUES (?, ?, ?, ?)";
    private static final String update = "update characters set name=?, damage=?, start_item=?, health=? where name=?";
    private static final String obtain = "Select * from characters";
    
    public static boolean insertCharacters(Character a, java.sql.Connection con) {
        boolean insercion = false;
        PreparedStatement st = null;
        if (con != null) {
            try {
                st = con.prepareStatement(insert);
                st.setString(1, a.getName());
                st.setInt(2, a.getDamage());
                st.setString(3, a.getItem().getName());
                st.setInt(4, a.getHealth());
                st.executeUpdate();
                insercion = true;
            } catch (SQLException e) {
                Logger.getLogger(CharacterDAO.class.getName()).log(Level.SEVERE, null, e);
            } finally {
                if (st != null) {
                    try {
                        st.close();
                    } catch (SQLException e) {
                        Logger.getLogger(CharacterDAO.class.getName()).log(Level.SEVERE, null, e);
                    }
                }
            }
        }
        return insercion;
    }
    
    public static boolean updatePlayer(Character a, java.sql.Connection con) {
        boolean updating = false;
        PreparedStatement st = null;
        if (con != null) {
            try {
                st = con.prepareStatement(update);
                st.setString(1, a.getName());
                st.setInt(2, a.getDamage());
                st.setString(3, a.getItem().getName());
                st.setInt(4, a.getHealth());
                st.setString(5, a.getName());
                
                st.executeUpdate();
                updating = true;
            } catch (SQLException e) {
                Logger.getLogger(CharacterDAO.class.getName()).log(Level.SEVERE, null, e);
            } finally {
                if (st != null) {
                    try {
                        st.close();
                    } catch (SQLException e) {
                        Logger.getLogger(CharacterDAO.class.getName()).log(Level.SEVERE, null, e);
                    }
                }
            }
        }
        return updating;
    }
    
    public static ArrayList<Character> getCharacters(java.sql.Connection con){
        PreparedStatement st = null;
        ResultSet rs = null;
        ArrayList<Character> characters = new ArrayList<>();
        ArrayList<Item> listAux = new ArrayList<>();
        if (con != null){
            try{
                st = con.prepareStatement(obtain);
                rs = st.executeQuery();
                
                while(rs != null && rs.next()){
                    Character a = null;
                    a = new Character();
                    String name = rs.getString("name");
                    int damage = rs.getInt("damage");
                    String s_item = rs.getString("start_item");
                    int health = rs.getInt("health");
                    Item aux = null;
                    listAux = ItemDAO.getItems(con);
                    for(Item i : listAux){
                        if(i.getName().equals(s_item)){
                            aux = i;
                            break;
                        }
                    }
                    
                    a.setName(name);
                    a.setDamage(damage);
                    a.setItem(aux);
                    a.setHealth(health);
                    
                    characters.add(a);
                    
                }
            }catch(SQLException e){
                Logger.getLogger(CharacterDAO.class.getName()).log(Level.SEVERE, null, e);
            }finally{
                if(rs != null){
                    try{
                        rs.close();
                    }catch(SQLException e){
                        Logger.getLogger(CharacterDAO.class.getName()).log(Level.SEVERE, null, e);
                    }
                }
            }
        }
        return characters;
    }
    
}
