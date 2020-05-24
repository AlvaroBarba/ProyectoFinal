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
public class ItemDAO extends Item{
    
    public static final Item surrender = new Item("La rendición", "Se otorga cuando un jugador se rinde"
            + "ante el abrumador poder del enemigo", 0);
    
    public ItemDAO(String name, String description, float modifier) {
        super(name, description, modifier);
    }
    
    private static final String insert = "INSERT INTO items (name,"
            + " description, modifier) VALUES (?, ?, ?)";
    private static final String obtain = "select * from items";
    private static final String update = "update items set name=?, description=?, modifier=? where name=?";
    
    public static boolean updateItem(Item a, java.sql.Connection con) {
        boolean updating = false;
        PreparedStatement st = null;
        if (con != null) {
            try {
                st = con.prepareStatement(update);
                st.setString(1, a.getName());
                st.setString(2, a.getDescription());
                st.setFloat(3, a.getModifier());
                st.setString(4, a.getName());
                
                st.executeUpdate();
                updating = true;
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
        return updating;
    }
    
    public static ArrayList<Item> getItems(java.sql.Connection con){
        PreparedStatement st = null;
        ResultSet rs = null;
        ArrayList<Item> items = new ArrayList<>();
        if (con != null){
            try{
                st = con.prepareStatement(obtain);
                rs = st.executeQuery();
                
                while(rs != null && rs.next()){
                    Item a = null;
                    a = new Item();
                    String name = rs.getString("name");
                    String desc = rs.getString("description");
                    
                    a.setName(name);
                    a.setDescription(desc);
                    
                    items.add(a);
                    
                }
            }catch(SQLException e){
                Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, e);
            }finally{
                if(rs != null){
                    try{
                        rs.close();
                    }catch(SQLException e){
                        Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, e);
                    }
                }
            }
        }
        return items;
    }
    
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
