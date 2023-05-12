/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.LinkedList;

/**
 *
 * @author mjra9
 */
public class Order {
    private int orderID;
    private int userID;
    private String status;
    private String date;
    private LinkedList<Product> products;
    
    
    public Order(int ID, int userID, String date){
        this.orderID = orderID;
        this.products = new LinkedList<Product>();
        this.userID = userID;
        this.date = date;
        this.status = "In Cart";
    }

    public int getOrderID() {
        return orderID;
    }

    public int getUserID() {
        return userID;
    }

    public String getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }

    public LinkedList<Product> getProducts() {
        return products;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
