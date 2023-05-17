/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author mjra9
 */
public class OrderLine implements Serializable{
    private Product product;
    private int orderID;
    private int quantity;
    private double price;
    
    public OrderLine(){
        this.product = new Product();
        this.orderID = 0;
        this.quantity = 0;
        this.price = 0.0;
    }

    public OrderLine(Product product, int orderID, int quantity) {
        this.product = product;
        this.orderID = orderID;
        this.quantity = quantity;
        this.price = quantity * product.getProductPrice();
    }

    

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.product);
        hash = 17 * hash + this.orderID;
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
        final OrderLine other = (OrderLine) obj;
        if (this.orderID != other.orderID) {
            return false;
        }
        return Objects.equals(this.product, other.product);
    }

    
   
}
