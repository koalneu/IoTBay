/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author tylershienlim
 */
public class Payment implements Serializable {
    private int paymentID;
    private int payMethodID;
    private int paymentAmount;
    
    private PaymentMethod paymethod;
    
    public Payment (){
        this.paymentID = paymentID;
        this.payMethodID = payMethodID;
        this.paymentAmount = paymentAmount;
        PaymentMethod paymethod = new PaymentMethod();
    }

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public int getPayMethodID() {
        return paymethod.getPayMethodID();
    }

    public void setPayMethodID(int payMethodID) {
        this.payMethodID = payMethodID;
        paymethod.setPayMethodID(payMethodID);
    }

    public int getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(int paymentAmount) {
        this.paymentAmount = paymentAmount;
    }   
    
    public void addPayment(int amount ) throws SQLException{
        //Set variables for the connection to DB
        String dbuser = "iotadmin";
        String dbpass = "password";
        
        
        try {
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/IoTDatabase", dbuser, dbpass);
            //statement
            Statement statement = conn.createStatement();
            String command = "INSERT INTO PAYMENT(PAYMENTID, PAYMETHODID, PAYMENTAMOUNT) VALUES(?,?,?)";
            PreparedStatement pst = conn.prepareStatement(command);
            //calculate the new ID
            String rows = "select count(*) from CUSTOMER";
            ResultSet retrieveResult = statement.executeQuery(rows);
            retrieveResult.next();
            int ID = retrieveResult.getInt(1);
            pst.setObject(1, ID);
            pst.setObject(2, ID);
            pst.setObject(3, amount);
            
            pst.executeUpdate();
               
            conn.close();
        } catch (Error e) {
            
        }
    }
}
