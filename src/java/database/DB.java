/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package database;

import java.sql.Connection;


public class DB {
    protected String URL = "jdbc:derby://localhost:1527/testing";  
    protected String db = "testing";//name of the database   
    protected String dbuser = "test";//db root user   
    protected String dbpass = "test"; //db root password     
    protected Connection conn; //connection null-instance to be initialized in sub-classes
    
}
