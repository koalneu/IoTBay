/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author milad
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.sql.*;

public class Shipment {
    private int shipmentID;
    private String shipmentStreet;
    private String shipmentCountry;
    private int shipmentPostcode;
    private String shipmentCity;
    private String shipmentMethod;
    private String shipmentState;
    private int orderID;
    // new date attribute
    
    // constructor, getters, and setters omitted for brevity
        


    public Shipment(int shipmentID, int orderID){
    this.shipmentID = shipmentID;
    this.orderID = orderID;
   
    }
    public Shipment(int ID, String street, int postCode, String country, String city, String method, int orderId, String state) {
    this.shipmentID = ID;
     this.shipmentStreet = street;
    this.shipmentCountry = country;
    this.shipmentPostcode = postCode;
    this.shipmentCity = city;
    this.shipmentMethod = method;
    this.shipmentState = state;
    this.orderID = orderID;
    }
    public Shipment(){
    this.shipmentID = 0;
    this.shipmentStreet = "";
    this.shipmentCountry = "";
    this.shipmentPostcode = 0;
    this.shipmentCity = "";
    this.shipmentMethod = "";
    this.shipmentState = "";
    
    
    }
    public int getShipmentID() {
        return shipmentID;
    }

    public void setShipmentId(int ID) {
        this.shipmentID = ID;
    }

    public String getShipmentStreet() {
        return shipmentStreet;
    }

    public void setShipmentStreet(String street) {
        this.shipmentStreet = street;
    }

    public String getShipmentCountry() {
        return shipmentCountry;
    }

    public void setShipmentCountry(String country) {
        this.shipmentCountry = country;
    }

    public int getShipmentPostCode() {
        return shipmentPostcode;
    }

    public void setShipmentPostCode(int postCode) {
        this.shipmentPostcode = postCode;
    }

    public String getShipmentCity() {
        return shipmentCity;
    }

    public void setShipmentCity(String city) {
        this.shipmentCity = city;
    }

    public String getShipmentMethod() {
        return shipmentMethod;
    }

    public void setShipmentMethod(String method) {
        this.shipmentMethod = method;
    }

    public String getShipmentState() {
        return shipmentState;
    }

    public void setShipmentState(String state) {
        this.shipmentState = state;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }


    
}