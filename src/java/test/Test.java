/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import models.dao.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
            Product p = manager.searchProduct(4);
            System.out.println(p.getProductID() + " " + p.getProductName());
//            ArrayList<OrderLine> orderLine = manager.getOrderLine(3);
//            for(OrderLine order : orderLine){
//                System.out.println(order.getOrderID() + " " + order.getProduct().getProductName() + 
//                        " " +order.getProduct().getProductID() + " " + order.getQuantity());
//            }
            ArrayList<Order> orders = manager.getOrderHistory(1, true);
            for(Order order: orders){
                //order.setOrderLine(manager.getOrderLine(order.getUserID()));
                for(OrderLine o : order.getOrderLine()){
                System.out.println(order.getOrderID() + " " + o.getProduct().getProductName() + 
                        " " +o.getProduct().getProductID() + " " + o.getQuantity());
                }
            }
            Order order = new Order(3, 1, "Processing", "03/16/2023", false, manager.getOrderLine(3));
            order.getOrderLine().add(new OrderLine(manager.searchProduct(6), order.getOrderID(), 4));
            manager.updateOrder(order);
            
        } catch(ClassNotFoundException | SQLException ex){
            System.out.println("no connection" + ex.getMessage());
        }
        Order order = new Order(1, 0);
        if(!order.getOrderLine().isEmpty()){
            System.out.println("orderLine is empty");
        }
        
//        HashMap<Product, Integer> productsOrdered = order.getProducts();
//        int i = 1;
//        for(Product product: products){
//            productsOrdered.put(product, i);
//            i++;
//        }
//        Product product = new Product(4,"Hummus",null, 20.75,null, 0);
//        for(Map.Entry<Product, Integer> pair : productsOrdered.entrySet()){
//            if(pair.getKey().equals(product)){
//                System.out.println(pair.getKey().getProductName() + " is in the HashMap");
//            }
//            else{
//                System.out.println(pair.getKey().getProductName() + " " + pair.getValue());
//            }
//        }
        int orderID = 0;
        int userID = 0;
        Date date = new Date();
        String status = "processing";
        boolean isPayed = false;
        
        System.out.println("INSERT INTO IOTADMIN.ORDERS" + "(ORDERID, CUSTOMERID, ORDERDATE, ORDERSTATUS, ISPAYED)" + 
                "VALUES(" + orderID + ", " + userID + ", \'" + date + "\' ," + "\'" + status + "\', " + isPayed + ")");
        
        
    }
    
    
        
       
        
}
