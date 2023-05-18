package models.dao;

import java.sql.Connection;

/**
 *
 * @author benjamin
 */
public class DB {
    
    protected String URL = "jdbc:derby://localhost:1527/";
    protected String db ="IoTDatabase";
    protected String dbuser = "iotadmin";
    protected String dbpass = "password";
    protected String driver = "org.apache.derby.jdbc.ClientDriver";
    protected Connection conn;
}
