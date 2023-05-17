/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;
import java.util.ArrayList;
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
    private String date;
    private boolean isPayed;
    private ArrayList<OrderLine> orderLine;
    
    
    public Order(int orderID, int userID){
        this.orderID = orderID;
        this.orderLine = new ArrayList<OrderLine>();
        this.userID = userID;
        this.date = "";
        //processing or shipped
        this.status = "Processing";
        this.isPayed = false;
    }
    

    public Order(int orderID, int userID, String status, String date, boolean isPayed, ArrayList<OrderLine> orderLine) {
        this.orderID = orderID;
        this.userID = userID;
        this.status = status;
        this.date = date;
        this.isPayed = isPayed;
        this.orderLine = orderLine;
    }
   
    public Order(){
        this.orderID = 0;
        this.orderLine = new ArrayList<OrderLine>();
        this.userID = 0;
        this.date = "";
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

    public String getDate() {
        return date;
    }

    public boolean isIsPayed() {
        return isPayed;
    }

    public ArrayList<OrderLine> getOrderLine() {
        return orderLine;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setOrderLine(ArrayList<OrderLine> orderLine) {
        this.orderLine = orderLine;
    }

    public void setIsPayed(boolean isPayed) {
        this.isPayed = isPayed;
    }
 
    public void setStatus(String status) {
        this.status = status;
    }
    
    public void addQuantity(OrderLine product){
        int index = orderLine.indexOf(product);
        OrderLine productToUpdate = orderLine.get(index);
        productToUpdate.setQuantity(productToUpdate.getQuantity() + 1);
        productToUpdate.setPrice(product.getPrice() + product.getProduct().getProductPrice());
    }
    
    public void subtractQuantity(OrderLine product){
        int index = orderLine.indexOf(product);
        OrderLine productToUpdate = orderLine.get(index);
        productToUpdate.setQuantity(productToUpdate.getQuantity() - 1);
        if(productToUpdate.getQuantity() == 0){
            orderLine.remove(product);
        }else{
            productToUpdate.setPrice(product.getPrice() - product.getProduct().getProductPrice());
        }  
    }
    
    
    public void addToOrderLine(OrderLine product){
       orderLine.add(product);
    }
    
    public OrderLine checkProduct(Product product){
        for(OrderLine p : orderLine){
            if(p.getProduct().equals(product)){
                return p;
            }
        }
        return null;
    }
    public boolean isValid(){
        for(OrderLine p : orderLine){
            if(!p.isInStock()){
                return false;
            }
        }
        return true;
    }  
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.orderID;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        return this.orderID == other.orderID;
    }

}
