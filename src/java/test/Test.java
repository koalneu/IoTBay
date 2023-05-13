/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import dao.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import models.Product;

/**
 *
 * @author mjra9
 */
public class Test {
    public static void main(String[] args){
        try{
            DBConnector connector = new DBConnector();
            Connection conn = connector.openConnection();
            DBManager manager = new DBManager(conn);
            LinkedList<Product> products = manager.getProducts();
            for(Product product : products){
                System.out.println(product.getProductName() + " " + product.getProductID() + " " + product.getProductPrice());
            }
        } catch(ClassNotFoundException | SQLException ex){
            System.out.println("no connection");
        }
    }   
        
       
        
}
