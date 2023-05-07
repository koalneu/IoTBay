/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.User;


public class userController {
    public static User authenticateUser(String email, String password) {
    //Set variables for the connection
    String dbuser = "test";
    String dbpass = "test";
    try {
        //Establish connection:
        Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/testing", dbuser, dbpass);
        PreparedStatement command = conn.prepareStatement("SELECT * FROM CUSTOMERS WHERE CUSTOMEREMAIL = ?");
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
}
