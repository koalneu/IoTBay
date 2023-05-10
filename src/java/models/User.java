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
        Modified authenticateUser() to function with the database
        NOTE: dbuser and dbpass have to be edited to the credentials for the database
              conn must be changed from "testing" to the actual database name
              
    */
    public static User authenticateUser(String email, String password) {
    //Set variables for the connection
    String dbuser = "iotadmin";
    String dbpass = "password";
    try {
        //Establish connection:
        Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/IoTDatabase", dbuser, dbpass);
        PreparedStatement command = conn.prepareStatement("SELECT * FROM CUSTOMER WHERE CUSTOMEREMAIL = ?");
        //Find the user in the DB which has the same email
        command.setString(1, email);
        ResultSet resultSet = command.executeQuery();
        //Check if a email has not been found
        if(!resultSet.next()){
            return null;
        }
        //Copy items from DB into a user object
        User user = new User(resultSet.getString("CUSTOMERFIRSTNAME"), resultSet.getString("CUSTOMERLASTNAME"), email, resultSet.getString("CUSTOMERPASSWORD"), resultSet.getString("CUSTOMERSTREET"), resultSet.getString("CUSTOMERCITY"), resultSet.getString("CUSTOMERPOSTCODE"), resultSet.getString("CUSTOMERSTATE"), resultSet.getString("CUSTOMERCOUNTRY"));
        
        conn.close();
        
        //Check if the password matches the user's input
        if(password.equals(user.getUserPassword())){
            return user;
        }
        else{
            return null;
        }
        
    } catch (SQLException ex) {
        System.out.println("Error Establishing Connection!");
    }

    return null;
    }

    
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
}
