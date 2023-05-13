/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import models.dao.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import models.*;


/**
 *
 * @author mjra9
 */
public class Test {
    public static void main(String[] args){
        ArrayList<Product> products = new ArrayList<Product>();
        try{
            DBConnector connector = new DBConnector();
            Connection conn = connector.openConnection();
            DBManager manager = new DBManager(conn);
            products = manager.fetchProducts();
            for(Product product : products){
                System.out.println(product.getProductName() + " " + product.getProductID() + " " + product.getProductPrice());
            }
            int orderCount = manager.countOrderRows();
            System.out.println("Order Count is = " + orderCount);
            int userID = manager.getUserID("a@1.com");
            System.out.println("User ID = " + userID);
        } catch(ClassNotFoundException | SQLException ex){
            System.out.println("no connection");
        }
        Order order = new Order(1, 0);
        HashMap<Product, Integer> productsOrdered = order.getProducts();
        int i = 1;
        for(Product product: products){
            productsOrdered.put(product, i);
            i++;
        }
        Product product = new Product("Hummus", 4, 20.75);
        for(Map.Entry<Product, Integer> pair : productsOrdered.entrySet()){
            if(pair.getKey().equals(product)){
                System.out.println(pair.getKey().getProductName() + " is in the HashMap");
            }
            else{
                System.out.println(pair.getKey().getProductName() + " " + pair.getValue());
            }
        }
    }   
        
       
        
}
