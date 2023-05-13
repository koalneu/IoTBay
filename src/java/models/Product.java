/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;

/**
 *
 * @author mjra9
 */
public class Product implements Serializable{
    public int productID;
    public String productName;
    public String productDesc;
    public double productPrice;
    public String productImage;
    public int productStock;
    
    public Product(int id, String name, String desc, double price, String image, int stock) {
        this.productID = id;
        this.productName = name;
        this.productDesc = desc;
        this.productPrice = price;
        this.productImage = image;
        this.productStock = stock;
    }
    
    public int getProductID() {
        return productID;
    }
    
    public void setProductID(int id) {
        this.productID = id;
    }
    
    public String getProductName() {
        return productName;
    }
    
    public void setProductName(String name) {
        this.productName = name;
    }
    
    public String getProductDesc() {
        return productDesc;
    }
    
    public void setProductDesc(String desc) {
        this.productDesc = desc;
    }
    
    public double getProductPrice() {
        return productPrice;
    }
    
    public void setProductPrice(double price) {
        this.productPrice = price;
    }
    
    public String getProductImage() {
        return productImage;
    }
    
    public void setProductImage(String image) {
        this.productImage = image;
    }
    
    public int getProductStock() {
        return productStock;
    }
    
    public void setProductStock(int stock) {
        this.productStock = stock;
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
