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
import java.util.Date;

/**
 *
 * @author tylershienlim
 */
public class PaymentMethod implements Serializable{
    private int payMethodID;
    private int payMethodCardNo;
    private String payMethodCardHolder;
    private int payMethodCardSecurity;
    private Date payMethodCardExpiry;

    public int getPayMethodID() {
        return payMethodID;
    }

    public void setPayMethodID(int payMethodID) {
        this.payMethodID = payMethodID;
    }

    public int getPayMethodCardNo() {
        return payMethodCardNo;
    }

    public void setPayMethodCardNo(int payMethodCardNo) {
        this.payMethodCardNo = payMethodCardNo;
    }

    public String getPayMethodCardHolder() {
        return payMethodCardHolder;
    }

    public void setPayMethodCardHolder(String payMethodCardHolder) {
        this.payMethodCardHolder = payMethodCardHolder;
    }

    public int getPayMethodCardSecurity() {
        return payMethodCardSecurity;
    }

    public void setPayMethodCardSecurity(int payMethodCardSecurity) {
        this.payMethodCardSecurity = payMethodCardSecurity;
    }

    public Date getPayMethodCardExpiry() {
        return payMethodCardExpiry;
    }

    public void setPayMethodCardExpiry(Date payMethodCardExpiry) {
        this.payMethodCardExpiry = payMethodCardExpiry;
    }
    
    
    public void addPayMethod(int cardno, String cardname, String cardDate, int cardcvv) throws SQLException {
        //Set variables for the connection to DB
        String dbuser = "iotadmin";
        String dbpass = "password";
        
        try {
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/IoTDatabase", dbuser, dbpass);
            //statement
            Statement statement = conn.createStatement();
            String command = "INSERT INTO PAYMENTMETHOD(PAYMETHODID, PAYMETHODCARDNO,"
                    + "PAYMETHODCARDHOLDER, PAYMETHODCARDSECURITY,PAYMETHODCARDEXPIRY VALUES(?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(command);
            //calculate the new ID
            String rows = "select count(*) from CUSTOMER";
            ResultSet retrieveResult = statement.executeQuery(rows);
            retrieveResult.next();
            int ID = retrieveResult.getInt(1);
            pst.setObject(1, ID);
            pst.setObject(2, cardno);
            pst.setObject(3, cardname);
            pst.setDate(4, java.sql.Date.valueOf(cardDate));
            pst.setObject(5, cardcvv);
            
            pst.executeUpdate();   
            conn.close();
        } catch (Error e){
            
        }
    }
}
