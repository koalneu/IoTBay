package models.dao;

import models.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.User;

/**
 *
 * @author benjamin
 */
public class DBManager {
    private Statement st;
    private Connection conn;
    
    public DBManager(Connection conn) throws SQLException {
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
    
    public ArrayList<ArrayList<String>> getAccessLogs(String email){
        try{
            // Retrieve the ID from the Email
            String fetch = "SELECT * FROM CUSTOMER WHERE CUSTOMEREMAIL = ?";
            PreparedStatement stmt = conn.prepareStatement(fetch);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            String ID = rs.getString("CUSTOMERID");
            // END OF THE FUNCTION
            ArrayList<ArrayList<String>> accessLogs = new ArrayList<>();
           
            String query = "SELECT * FROM ACCESSLOGS WHERE USERID = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, Integer.parseInt(ID));
            ResultSet resultSet = statement.executeQuery();
                
            while(resultSet.next()){
                ArrayList<String> logEntry = new ArrayList<>();
                
                int accessLogID = resultSet.getInt("ACCESSLOGID");
                String userName = resultSet.getString("USERNAME");
                String accessTime = resultSet.getString("ACCESSTIME");
                String action = resultSet.getString("ACTION");
                
                logEntry.add(accessLogID+"");
                logEntry.add(ID+"");
                logEntry.add(userName);
                logEntry.add(accessTime);
                logEntry.add(action);
                
                accessLogs.add(logEntry);
            }
            
            return accessLogs;
        }
        catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null; 
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
    
    //Functions from Mark's branch
    public int countOrderRows() throws SQLException {
        String fetch = "SELECT COUNT (*) FROM ORDERS";
        ResultSet rs = st.executeQuery(fetch);
        rs.next();
        int ID = rs.getInt(1);
        return ID;
    }
    public int getUserID(String email) throws SQLException {
        String fetch = "SELECT * FROM CUSTOMER WHERE CUSTOMEREMAIL = " + "\'" + email + "\'";
        ResultSet rs = st.executeQuery(fetch);
        rs.next();
        int ID = rs.getInt("CUSTOMERID");
        return ID;
    }
    
    //Functions related to products in the database
    //search product by name
    public Product findProduct(String name) throws SQLException {
        String fetch = "select * from IOTADMIN.Product where PRODUCTNAME = '" + name + "'";
        ResultSet rs = st.executeQuery(fetch);
        
        while (rs.next()) {
            String productName = rs.getString(2);
            if (productName.equals(name)) {
                int productID = rs.getInt(1);
                String productDesc = rs.getString(3);
                double productPrice = rs.getDouble(4);
                String productImage = rs.getString(5);
                int productStock = rs.getInt(6);
                return new Product(productID, productName, productDesc, productPrice, productImage, productStock);
            }
        }
        return null;
    }
    //search product by ID
    public Product findProduct(int id) throws SQLException {
        String fetch = "select * from IOTADMIN.Product where PRODUCTID = " + id + "";
        ResultSet rs = st.executeQuery(fetch);
        
        while (rs.next()) {
            int productID = rs.getInt(1);
            if (productID == id) {
                String productName = rs.getString(2);
                String productDesc = rs.getString(3);
                double productPrice = rs.getDouble(4);
                String productImage = rs.getString(5);
                int productStock = rs.getInt(6);
                return new Product(productID, productName, productDesc, productPrice, productImage, productStock);
            }
        }
        return null;
    }
    //add product to database
    public void addProduct(String name, String desc, double price, String image, int stock) throws SQLException {
        /*String rows = "SELECT COUNT(*) FROM PRODUCT";
        ResultSet retrieveResult = st.executeQuery(rows);
        retrieveResult.next();
        int ID = retrieveResult.getInt(1); */
        String query = "SELECT * FROM IOTADMIN.PRODUCT";
        ResultSet rs = st.executeQuery(query);
        int ID = 0;
        while (rs.next()) {
            int temp = rs.getInt(1) + 1;
            if (temp > ID) {
                ID = temp;
            }
        }
        
        //System.out.println("New product ID: " + ID);
        st.executeUpdate("INSERT INTO IOTADMIN.PRODUCT " + "(PRODUCTID, PRODUCTNAME, PRODUCTDESC, PRODUCTPRICE, PRODUCTIMAGE, PRODUCTSTOCK)" + " VALUES ("+ID+", '"+name+"', '"+desc+"', "+price+", '"+image+"', "+stock+")");
    }
    //update all details of a product by ID
    public void updateProduct(int id, String name, String desc, double price, String image, int stock) throws SQLException {
        st.executeUpdate("UPDATE IOTADMIN.PRODUCT SET PRODUCTNAME='"+name+"',PRODUCTDESC='"+desc+"',PRODUCTPRICE="+price+",PRODUCTIMAGE='"+image+"',PRODUCTSTOCK="+stock+" WHERE PRODUCTID="+id+"");
    }    
    //update only the price of a product by name
    public void updateProductPrice(String name, double price) throws SQLException {
        st.executeUpdate("UPDATE IOTADMIN.PRODUCT SET PRODUCTPRICE="+price+" WHERE PRODUCTNAME='"+name+"'");
    }
    //update only the stock of a product by name
    public void updateProductStock(String name, int stock) throws SQLException {
        st.executeUpdate("UPDATE IOTADMIN.PRODUCT SET PRODUCTSTOCK="+stock+" WHERE PRODUCTNAME='"+name+"'");
    }
    //delete a product by name
    public void deleteProduct(String name) throws SQLException {
        st.executeUpdate("DELETE FROM IOTADMIN.PRODUCT WHERE PRODUCTNAME='"+name+"'");
    }
    //delete a product by ID (incase there are 2 products with the same name
    public void deleteProduct(int id) throws SQLException {
        st.executeUpdate("DELETE FROM IOTADMIN.PRODUCT WHERE PRODUCTID="+id+"");
    }
    //fetch list of products
    public ArrayList<Product> fetchProducts() throws SQLException {
        String fetch = "SELECT * FROM PRODUCT";
        ResultSet rs = st.executeQuery(fetch);
        ArrayList<Product> temp = new ArrayList();
        
        while (rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String desc = rs.getString(3);
            double price = rs.getDouble(4);
            String image = rs.getString(5);
            int stock = rs.getInt(6);
            temp.add(new Product(id, name, desc, price, image, stock));
        }
        return temp;
    }
    public ArrayList<Product> fetchPopular() throws SQLException {
        String fetch = "SELECT * FROM PRODUCT";
        ResultSet rs = st.executeQuery(fetch);
        ArrayList<Product> temp = new ArrayList();
        int count = 0;
        while (count < 5) {
            while (rs.next()) {
                int stock = rs.getInt(6);
                if (stock < 10) {
                    int id = rs.getInt(1);
                    String name = rs.getString(2);
                    String desc = rs.getString(3);
                    double price = rs.getDouble(4);
                    String image = rs.getString(5);
                    temp.add(new Product(id, name, desc, price, image, stock));
                    count++;
                }
            }
        }
        return temp;
    }
    //check if product is in the database by ID
    public boolean checkProduct(int id) throws SQLException {
        String fetch = "SELECT * FROM IOTADMIN.PRODUCT WHERE PRODUCTID="+id+"";
        ResultSet rs = st.executeQuery(fetch);
        
        while (rs.next()) {
            int productID = rs.getInt(1);
            if (productID == id) {
                return true;
            }
        }
        return false;
    }
    //check if product is in the database by name
    public boolean checkProduct(String name) throws SQLException {
        String fetch = "SELECT * FROM IOTADMIN.PRODUCT WHERE PRODUCTNAME='"+name+"'";
        ResultSet rs = st.executeQuery(fetch);
        
        while (rs.next()) {
            String productName = rs.getString(2);
            if (productName.equals(name)) {
                return true;
            }
        }
        return false;
    }
}
