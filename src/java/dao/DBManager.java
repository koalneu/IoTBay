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
           String name = rs.getString("PRODUCTNAME");
           int ID = rs.getInt("PRODUCTID");
           double price = rs.getDouble("PRODUCTPRICE");
           products.add(new Product(name, ID, price)); 
        }
        //System.out.println("products acquired");
        return products;    
    }
    
    public int getUserID (String email) throws SQLException{
        String fetch = "SELECT * FROM CUSTOMER WHERE CUSTOMEREMAIL = " + "\'" + email + "\'";
        ResultSet rs = st.executeQuery(fetch);
        rs.next();
        int ID = rs.getInt("CUSTOMERID");
        return ID;
    }
    
    public int countOrderRows() throws SQLException{
        String fetch = "SELECT COUNT(*) FROM ORDERS"; 
        ResultSet rs = st.executeQuery(fetch);
        rs.next();
        int ID = rs.getInt(1);
        return ID;
    }
}
