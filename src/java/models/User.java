/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
user is a JavaBean that store customer details such as first and last name,
email, password, and their address
*/

package models;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author (Tyler) Shi En Lim 13675919
 * @author Wilson
 */
public class User implements Serializable {

    public String userFirstName;
    public String userLastName;
    private String userEmail;
    private String userPassword;
    public String userStreet;
    public String userCity;
    public String userPostCode;
    public String userState;
    public String userCountry;
    
    /*
    initialize constructors
    */
    public User(String userFirstName, String userLastName, String userEmail, String userPassword, String userStreet, String userPostCode,String userCity,String userState, String userCountry) {
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userStreet = userStreet;
        this.userPostCode = userPostCode;
        this.userCity = userCity;
        this.userState = userState;
        this.userCountry = userCountry;
    }
    
    /*
    initialize getters and setters
    */
    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserStreet() {
        return userStreet;
    }

    public void setUserStreet(String userStreet) {
        this.userStreet = userStreet;
    }

    public String getUserPostCode() {
        return userPostCode;
    }

    public void setUserPostCode(String userCity) {
        this.userPostCode = userPostCode;
    }
    
    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public String getUserCountry() {
        return userCountry;
    }

    public void setUserCountry(String userCountry) {
        this.userCountry = userCountry;
    }
    
    //Add user function
    public void addUser(String fname, String lname, String email, String password, String street, String postcode, String city, String state, String country) throws SQLException{
        //Set variables for the connection to DB
        String dbuser = "test";
        String dbpass = "test";
        //establish connection: 
        try{
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/testing", dbuser, dbpass);
            //statement
            Statement statement = conn.createStatement();
            String command = "INSERT INTO CUSTOMERS(CUSTOMERID,CUSTOMERFIRSTNAME,CUSTOMERLASTNAME,CUSTOMEREMAIL,CUSTOMERPASSWORD,CUSTOMERSTREET,CUSTOMERPOSTCODE,CUSTOMERCITY,CUSTOMERSTATE,CUSTOMERCOUNTRY) VALUES(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(command);
            //calculate the new ID
            String rows = "select count(*) from CUSTOMERS";
            ResultSet retrieveResult = statement.executeQuery(rows);
            retrieveResult.next();
            int ID = retrieveResult.getInt(1);
            pst.setObject(1, ID);
            pst.setObject(2, fname);
            pst.setObject(3, lname);
            pst.setObject(4, email);
            pst.setObject(5, password);
            pst.setObject(6, street);
            pst.setObject(7, postcode);
            pst.setObject(8, city);
            pst.setObject(9, state);
            pst.setObject(10, country);
                
            pst.executeUpdate();
               
            conn.close();
        }
        catch(Error e){
        }
            
    }
    
    //update
    public static void updateUser(String originalEmail, String fname, String lname, String email, String password, String street, String postcode, String city, String state, String country) throws SQLException{
        String dbuser = "test";
        String dbpass = "test";
        String driver = "org.apache.derby.jdbc.ClientDriver";

        //establish connection:
        Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/testing", dbuser, dbpass);
        //update is used to seelct
        //Statement to update the correct columns
        PreparedStatement update = conn.prepareStatement("UPDATE CUSTOMERS SET CUSTOMERFIRSTNAME=?, CUSTOMERLASTNAME=?, CUSTOMEREMAIL=?, CUSTOMERPASSWORD=?, CUSTOMERSTREET=?, CUSTOMERPOSTCODE=?, CUSTOMERCITY=?, CUSTOMERSTATE=?, CUSTOMERCOUNTRY=? WHERE CUSTOMEREMAIL=?");
        //Set the variables for the "update" statement
        update.setString(1, fname);
        update.setString(2, lname);
        update.setString(3, email);
        update.setString(4, password);
        update.setString(5, street);
        update.setString(7, city);
        update.setInt(6, Integer.parseInt(postcode));
        update.setString(8, state);
        update.setString(9, country);
        update.setString(10, originalEmail);

        update.executeUpdate();
        conn.close();
    }
}
