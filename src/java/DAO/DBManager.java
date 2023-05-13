package DAO;
import DAO.DBConnector;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.User;

public class DBManager {
    private Statement st;
    private Connection conn;

    public DBManager(Connection conn) throws SQLException{
        st = conn.createStatement();
        this.conn = conn;
    }
    
    public User authenticateUser(String email, String password) {
    try {
        System.out.println("here1");
        ResultSet resultSet = st.executeQuery("SELECT * FROM CUSTOMER WHERE CUSTOMEREMAIL = '" + email + "'");
        //Check if a email has not been found
        if(!resultSet.next()){
            return null;
            
        }
        //Copy items from DB into a user object
        User user = new User(
            resultSet.getString("CUSTOMERFIRSTNAME"), 
            resultSet.getString("CUSTOMERLASTNAME"), 
            email, 
            resultSet.getString("CUSTOMERPASSWORD"), 
            resultSet.getString("CUSTOMERSTREET"),  
            resultSet.getString("CUSTOMERPOSTCODE"),
            resultSet.getString("CUSTOMERCITY"),
            resultSet.getString("CUSTOMERSTATE"), 
            resultSet.getString("CUSTOMERCOUNTRY"), 
            resultSet.getString("USERTYPE")
        );
        //Check if the password matches the user's input
        if(password.equals(user.getUserPassword())){
            return user;
        }
        else{
            return null;
        }
    } catch (SQLException ex) {
        System.out.println("Error Establishing Connection!");
        ex.printStackTrace();
    }
    return null;
}

    
    public String userType(String email) {
        try {
            ResultSet resultSet = st.executeQuery("SELECT * FROM CUSTOMER WHERE CUSTOMEREMAIL = '" + email + "'");
            if (resultSet.next()) {
                String userType = resultSet.getString("USERTYPE");
                return userType;
            }
            
        } catch (SQLException ex) {
            System.out.println("Error Establishing Connection!");
        }

        return null;
    }
    
    //Add user function
    public void addUser(String fname, String lname, String email, String password, String street, String postcode, String city, String state, String country, String userType) throws SQLException, ClassNotFoundException{
        try{
            String command = "INSERT INTO CUSTOMER(CUSTOMERID,CUSTOMERFIRSTNAME,CUSTOMERLASTNAME,CUSTOMEREMAIL,CUSTOMERPASSWORD,CUSTOMERSTREET,CUSTOMERPOSTCODE,CUSTOMERCITY,CUSTOMERSTATE,CUSTOMERCOUNTRY,USERTYPE) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(command);
            
            //calculate the new ID
            String rows = "select count(*) from CUSTOMER";
            ResultSet retrieveResult = st.executeQuery(rows);
            retrieveResult.next();
            int ID = retrieveResult.getInt(1);
            
            pst.setObject(1, ID);
            pst.setObject(2, fname);
            pst.setObject(3, lname);
            pst.setObject(4, email);
            pst.setObject(5, password);
            pst.setObject(6, street);
            pst.setObject(7, Integer.parseInt(postcode));
            pst.setObject(8, city);
            pst.setObject(9, state);
            pst.setObject(10, country);
            pst.setObject(11, userType);
            pst.executeUpdate();
        }
        catch(Error e){
        }
            
    }
    
    //update
    public void updateUser(String originalEmail, String fname, String lname, String email, String password, String street, String postcode, String city, String state, String country) throws SQLException, ClassNotFoundException{
        try {
            //update is used to seelct
            //Statement to update the correct columns
            PreparedStatement update = conn.prepareStatement("UPDATE CUSTOMER SET CUSTOMERFIRSTNAME=?, CUSTOMERLASTNAME=?, CUSTOMEREMAIL=?, CUSTOMERPASSWORD=?, CUSTOMERSTREET=?, CUSTOMERPOSTCODE=?, CUSTOMERCITY=?, CUSTOMERSTATE=?, CUSTOMERCOUNTRY=? WHERE CUSTOMEREMAIL=?");
            //Set the variables for the "update" statement
            update.setString(1, fname);
            update.setString(2, lname);
            update.setString(3, email);
            update.setString(4, password);
            update.setString(5, street);
            update.setInt(6, Integer.parseInt(postcode));
            update.setString(7, city);
            update.setString(8, state);
            update.setString(9, country);
            update.setString(10, originalEmail);

            update.executeUpdate();
        }
        catch(Error e){
        }
    }
    
    public void addAccessLogEntry(String email, String fname, String action) throws SQLException, ClassNotFoundException{
        try{
            String command = "INSERT INTO ACCESSLOGS(ACCESSLOGID,USERID,USERNAME,ACCESSTIME,ACTION) VALUES(?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(command);

            //calculate the ACCESSLOGID
            String rows = "select count(*) from ACCESSLOGS";
            ResultSet retrieveResult = st.executeQuery(rows);
            retrieveResult.next();
            int ACCESSLOGID = retrieveResult.getInt(1);

            //retrieve userID from DB
            String getUserID = "SELECT CUSTOMERID FROM CUSTOMER WHERE CUSTOMEREMAIL = ?";
            PreparedStatement userIDStatement = conn.prepareStatement(getUserID);
            userIDStatement.setObject(1, email);
            ResultSet retrieveID = userIDStatement.executeQuery();
            retrieveID.next();
            int userID = retrieveID.getInt(1);

            //get timestamp
            long timestamp = System.currentTimeMillis();
            java.sql.Timestamp accessTime = new java.sql.Timestamp(timestamp);

            pst.setObject(1, ACCESSLOGID);
            pst.setObject(2, userID);
            pst.setObject(3, fname);
            pst.setObject(4, accessTime);
            pst.setObject(5, action);

            pst.executeUpdate();
        }
        catch(Error e){
        }
           
    }
    
    
}
