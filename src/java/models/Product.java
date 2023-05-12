/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;



/**
 *
 * @author mjra9
 */
public class Product{
    private String name;
    private int productID;
    private double price;
    
    public Product(String name, int ID, double price){
        this.name = name;
        this.productID = ID;
        this.price = price;
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
    
    
}
