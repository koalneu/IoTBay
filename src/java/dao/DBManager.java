/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.*;
import java.util.LinkedList;
import models.*;

/**
 *
 * @author mjra9
 */
public class DBManager {
    private Statement st;
    
    public DBManager(Connection conn) throws SQLException{
        st = conn.createStatement(); 
    }
    
    public LinkedList<Product> getProducts() throws SQLException {
        String fetch = "SELECT * FROM PRODUCT";
        ResultSet rs = st.executeQuery(fetch);
        LinkedList<Product> products = new LinkedList<Product>();
        while(rs.next()){
           String name = rs.getString(2);
           int ID = Integer.parseInt(rs.getString(1));
           double price = Double.parseDouble(rs.getString(4));
           products.add(new Product(name, ID, price)); 
        }
        //System.out.println("products acquired");
        return products;    
    }
}
