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
public class Product implements Serializable{
    private String name;
    private int productID;
    private double price;
    
    public Product(String name, int ID, double price){
        this.name = name;
        this.productID = ID;
        this.price = price;
    }
    
    public Product(){
        this.name = "";
        this.productID = 0;
        this.price = 0.0;
    }
    
    public String getName() {
        return name;
    }

    public int getProductID() {
        return productID;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.name);
        hash = 79 * hash + this.productID;
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
        final Product other = (Product) obj;
        if (this.productID != other.productID) {
            return false;
        }
        return Objects.equals(this.name, other.name);
    }


    
    
    
}
