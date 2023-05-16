/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author mjra9
 */
public class Order implements Serializable{
    private int orderID;
    private int userID;
    private String status;
    private Date date;
    private boolean isPayed;
    private HashMap<Product, Integer> products;
    
    
    public Order(int ID, int userID){
        this.orderID = orderID;
        this.products = new HashMap<Product, Integer>();
        this.userID = userID;
        this.date = new Date();
        //processing or shipped
        this.status = "Processing";
        this.isPayed = false;
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

    public Date getDate() {
        return date;
    }

    public boolean isIsPayed() {
        return isPayed;
    }

    public HashMap<Product, Integer> getProducts() {
        return products;
    }

    public void setIsPayed(boolean isPayed) {
        this.isPayed = isPayed;
    }
 
    public void setStatus(String status) {
        this.status = status;
    }

}
