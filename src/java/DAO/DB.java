/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package DAO;

import java.sql.Connection;


public class DB {
    protected String URL = "jdbc:derby://localhost:1527/IoTDatabase";  
    protected String db = "IoTDatabase";//name of the database   
    protected String dbuser = "iotadmin";//db root user   
    protected String dbpass = "password"; //db root password     
    protected Connection conn; //connection null-instance to be initialized in sub-classes
    
}
