package database;
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
    public static User authenticateUser(String email, String password) {
    try {
        //Get the connection from the CustomerDBConnector class
        System.out.println("Connecting to database...");
        Connection conn = new DBConnector().openConnection();
        System.out.println("Connected to database successfully.");
        
        PreparedStatement command = conn.prepareStatement("SELECT * FROM CUSTOMERS WHERE CUSTOMEREMAIL = ?");
        //Find the user in the DB which has the same email
        command.setString(1, email);
        ResultSet resultSet = command.executeQuery();
        
        //Check if a email has not been found
        if(!resultSet.next()){
            conn.close();
            return null;
        }
        
        //Copy items from DB into a user object
        User user = new User(
            resultSet.getString("CUSTOMERFIRSTNAME"), 
            resultSet.getString("CUSTOMERLASTNAME"), 
            email, 
            resultSet.getString("CUSTOMERPASSWORD"), 
            resultSet.getString("CUSTOMERSTREET"), 
            resultSet.getString("CUSTOMERCITY"), 
            resultSet.getString("CUSTOMERPOSTCODE"), 
            resultSet.getString("CUSTOMERSTATE"), 
            resultSet.getString("CUSTOMERCOUNTRY"), 
            resultSet.getString("USERTYPE")
        );
        
        conn.close();
        
        //Check if the password matches the user's input
        if(password.equals(user.getUserPassword())){
            return user;
        }
        else{
            return null;
        }
    } catch (SQLException | ClassNotFoundException ex) {
        System.out.println("Error Establishing Connection!");
        ex.printStackTrace();
    }
    return null;
}

    
    public static String userType(String email) {
        try {
            //Get the connection from the CustomerDBConnector class
            Connection conn = new DBConnector().openConnection();
            
            PreparedStatement command = conn.prepareStatement("SELECT * FROM CUSTOMERS WHERE CUSTOMEREMAIL = ?");
            //Find the user in the DB which has the same email
            command.setString(1, email);
            ResultSet resultSet = command.executeQuery();
            
            if (resultSet.next()) {
                String userType = resultSet.getString("USERTYPE");
                conn.close();
                return userType;
            }
            
            conn.close();
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Error Establishing Connection!");
        }

        return null;
    }
    
    //Add user function
    public static void addUser(String fname, String lname, String email, String password, String street, String postcode, String city, String state, String country, String userType) throws SQLException, ClassNotFoundException{
        try{
            Connection conn = new DBConnector().openConnection();
            //statement
            Statement statement = conn.createStatement();
            String command = "INSERT INTO CUSTOMERS(CUSTOMERID,CUSTOMERFIRSTNAME,CUSTOMERLASTNAME,CUSTOMEREMAIL,CUSTOMERPASSWORD,CUSTOMERSTREET,CUSTOMERPOSTCODE,CUSTOMERCITY,CUSTOMERSTATE,CUSTOMERCOUNTRY,USERTYPE) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
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
            pst.setObject(11, userType);
            pst.executeUpdate();
               
            conn.close();
        }
        catch(Error e){
        }
            
    }
    
    //update
    public static void updateUser(String originalEmail, String fname, String lname, String email, String password, String street, String postcode, String city, String state, String country) throws SQLException, ClassNotFoundException{
        //establish connection:
        Connection conn;
        try {
            conn = new DBConnector().openConnection();
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
        catch(Error e){
        }
    }
    
    public static void addAccessLogEntry(String email, String fname, String action) throws SQLException, ClassNotFoundException{
        Connection conn;
        try{
            conn = new DBConnector().openConnection();
            Statement statement = conn.createStatement();
            String command = "INSERT INTO ACCESSLOGS(ACCESSLOGID,USERID,USERNAME,ACCESSTIME,ACTION) VALUES(?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(command);

            //calculate the ACCESSLOGID
            String rows = "select count(*) from ACCESSLOGS";
            ResultSet retrieveResult = statement.executeQuery(rows);
            retrieveResult.next();
            int ACCESSLOGID = retrieveResult.getInt(1);

            //retrieve userID from DB
            String getUserID = "SELECT CUSTOMERID FROM CUSTOMERS WHERE CUSTOMEREMAIL = ?";
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
            conn.close();
        }
        catch(Error e){
        }
           
    }
    
    
}
