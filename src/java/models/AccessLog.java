/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class AccessLog {
    public static void addAccessLogEntry(String email, String fname, String action) throws SQLException{
        try{
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/IoTDatabase", "iotadmin", "password");
            //statement
            Statement statement = conn.createStatement();
            String command = "INSERT INTO ACCESSLOGS(ACCESSLOGID,USERID,USERNAME,ACCESSTIME,ACTION) VALUES(?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(command);

            //calculate the ACCESSLOGID
            String rows = "select count(*) from ACCESSLOGS";
            ResultSet retrieveResult = statement.executeQuery(rows);
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
            conn.close();
        }
        catch(Error e){
        }
           
    }
}
