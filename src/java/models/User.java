package models;

import java.io.Serializable;

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
    public String userType;
    
    /*
    initialize constructors
    */
    public User(String userFirstName, String userLastName, String userEmail, String userPassword, String userStreet, String userPostCode,String userCity,String userState, String userCountry, String userType) {
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userStreet = userStreet;
        this.userPostCode = userPostCode;
        this.userCity = userCity;
        this.userState = userState;
        this.userCountry = userCountry;
        this.userType = userType;
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
    
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
    
}
