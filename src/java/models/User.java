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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author (Tyler) Shi En Lim 13675919
 * @author Wilsan
 */
public class User implements Serializable {
    public String userFirstName;
    public String userLastName;
    private String userEmail;
    private String userPassword;
    public String userStreet;
    public String userPostCode;
    public String userState;
    public String userCountry;
    
    /*
        to store list of users to access after session ends
    */
    public static List<User> users = new ArrayList<User>();
    
    //method to add users to the array
    public static void addUser(User user){
        //if user is already in list, do not add
            if (user != null ){
                String emailAdd = user.getUserEmail();

                System.out.println("users before:");

                for (User u : users){
                    System.out.println(u.getUserFirstName());
                }

                System.out.println("users after:");

                //if (!users.contains(user)){
                    users.add(user);
                //}

                for (User u : users){
                    System.out.println(u.getUserFirstName());
                }
                
            }
    }
    
    /*
        method to authenticate user for logging in
        
        check if list is empty..?
        if not empty, loop through the list
            check input email == email in list
            if yes, check input password == password in list
                return user or set session to found user
    */
    public static User authenticateUser(String email, String password) {
        if (users.isEmpty()){
            //if no registered users currently
            System.out.println("empty list");
            return null;
        }  else {
            for (User u : users){
                String systemUserEmail = u.getUserEmail();
                if (systemUserEmail.equals(email)){
                    String systemUserPassword = u.getUserPassword();
                    if (systemUserPassword.equals(password)){
                        //take you to the welcome page
                        //returns user
                        System.out.println("user found");
                        return u;
                    }
                }
            }
        }
        return null;
    }
    
    /*
    initialize constructors
    */
    public User(String userFirstName, String userLastName, String userEmail, String userPassword, String userStreet, String userPostCode, String userState, String userCountry) {
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userStreet = userStreet;
        this.userPostCode = userPostCode;
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

    public void setUserPostCode(String userPostCode) {
        this.userPostCode = userPostCode;
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
