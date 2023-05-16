package models.dao;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import models.*;
import java.util.Date;

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
    
    //functions from wilson's branch
    
    public User authenticateUser(String email, String password) throws SQLException {
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

    
    public String userType(String email) throws SQLException {
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
    //order implementation CRUD
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
    
    public void addOrder(Order order) throws SQLException{
        SimpleDateFormat sm = new SimpleDateFormat("MM/dd/yyyy");
        int orderID = order.getOrderID();
        int userID = order.getUserID();
        String date = sm.format(new Date());
        String status = order.getStatus();
        boolean isPayed = order.isIsPayed();
        
        st.executeUpdate("INSERT INTO IOTADMIN.ORDERS" + "(ORDERID, CUSTOMERID, ORDERDATE, ORDERSTATUS, ISPAYED)" + 
                "VALUES(" + orderID + ", " + userID + ", \'" + date + "\'," + "\'" + status + "\', " + isPayed + ")");
        
        for(OrderLine product : order.getOrderLine()){
            addOrderLine(product);
        }
    }
    
    public void addOrderLine(OrderLine orderLine) throws SQLException {
        int orderID = orderLine.getOrderID();
        int productID = orderLine.getProduct().getProductID();
        int quantity = orderLine.getQuantity();
        double price = orderLine.getPrice();
        st.executeUpdate("INSERT INTO IOTADMIN.ORDERLINEITEM" + "(ORDERID, PRODUCTID, ORDERLINEQUANTITY, ORDERLINEPRICE)" +
                "VALUES("+orderID+", " +productID+ ", "+quantity+", " +price+ ")");
    }
    
    public ArrayList<Order> getOrderHistory(int userID, boolean isSaved) throws SQLException{
        String fetch = "SELECT * FROM IOTADMIN.ORDERS WHERE CUSTOMERID =" + userID + "AND ISPAYED =" + isSaved;
        ResultSet rs = st.executeQuery(fetch);
        ArrayList<Order> orders = new ArrayList<Order>();
        while(rs.next()){ 
            Order order = new Order();
            order.setOrderID(rs.getInt("ORDERID"));
            order.setUserID(rs.getInt("CUSTOMERID"));
            order.setDate(rs.getString("ORDERDATE"));
            order.setStatus(rs.getString("ORDERSTATUS"));
            order.setIsPayed(rs.getBoolean("ISPAYED"));
            //order.setOrderLine(getOrderLine(order.getOrderID()));
            orders.add(order);
        }
        
        for(Order order : orders){
            order.setOrderLine(getOrderLine(order.getOrderID())); 
        }
       
        return orders;
    }
    
    public ArrayList<OrderLine> getOrderLine(int orderID) throws SQLException{
        String fetch = "SELECT * FROM ORDERLINEITEM inner join PRODUCT ON ORDERLINEITEM.PRODUCTID = PRODUCT.PRODUCTID WHERE ORDERID = "+ orderID;
        ResultSet rs = st.executeQuery(fetch);
        ArrayList<OrderLine> orderLines = new ArrayList<OrderLine>();
        while(rs.next()){
            OrderLine orderLine= new OrderLine();
            // start here
            orderLine.setProduct(new Product(rs.getInt("PRODUCTID"), rs.getString("PRODUCTNAME"), rs.getString("PRODUCTDESC"), 
                    rs.getDouble("PRODUCTPRICE"), rs.getString("PRODUCTIMAGE"), rs.getInt("PRODUCTSTOCK")));
            //return new Product(productID, productName, productDesc, productPrice, productImage, productStock); 
            orderLine.setOrderID(rs.getInt("ORDERID"));
            orderLine.setQuantity(rs.getInt("ORDERLINEQUANTITY"));
            orderLine.setPrice(rs.getDouble("ORDERLINEPRICE"));
            orderLines.add(orderLine);
        }
        return orderLines;
    }
    
    //public ArrayList<Product>
    
    public Order findOrder(int ID) throws SQLException{
        String fetch = "SELECT * FROM ORDERS WHERE ORDERID = "+ ID;
        ResultSet rs = st.executeQuery(fetch);
        while(rs.next()){
            int orderID = rs.getInt("ORDERID");
            if(orderID == ID){
                int userID = rs.getInt("CUSTOMERID");
                String date = rs.getString("ORDERDATE");
                String status = rs.getString("ORDERSTATUS");
                boolean isPayed = rs.getBoolean("ISPAYED");
                ArrayList<OrderLine> orderLine= new ArrayList<>();
                return new Order(orderID, userID, status, date, isPayed, orderLine); 
            }  
        }
        return null;
    }
    
    //search product by ID
    public Product searchProduct(int ID) throws SQLException{
        String fetch = "select * from IOTADMIN.Product where PRODUCTID = " + ID;
        ResultSet rs = st.executeQuery(fetch);
        while(rs.next()){
           int productID = rs.getInt(1);
            String productName = rs.getString(2);
            String productDesc = rs.getString(3);
            double productPrice = rs.getDouble(4);
            String productImage = rs.getString(5);
            int productStock = rs.getInt(6);
            return new Product(productID, productName, productDesc, productPrice, productImage, productStock); 
        }
        return null;       
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
    //add product to database
    public void addProduct(String name, String desc, double price, String image, int stock) throws SQLException {
        String rows = "SELECT COUNT(*) FROM PRODUCT";
        ResultSet retrieveResult = st.executeQuery(rows);
        retrieveResult.next();
        int ID = retrieveResult.getInt(1);
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
    //create payment entry
    public void addPayment(int amount ) throws SQLException{
        try {
            //statement
            Statement statement = conn.createStatement();
            String command = "INSERT INTO PAYMENT(PAYMENTID, PAYMETHODID, PAYMENTAMOUNT) VALUES(?,?,?)";
            PreparedStatement pst = conn.prepareStatement(command);
            //calculate the new ID
            String rows = "select count(*) from PAYMENT";
            ResultSet retrieveResult = statement.executeQuery(rows);
            retrieveResult.next();
            int ID = retrieveResult.getInt(1);
            pst.setObject(1, ID);
            pst.setObject(2, ID);
            pst.setObject(3, amount);
            
            pst.executeUpdate();
            
            //copy items into a new payment + payment method object
            
        } catch (Error e) {
            
        }
    }
    //create paymethod entry
    public void addPayMethod(String email, int cardno, String cardname, String cardDate, int cardcvv) throws SQLException {
        try {
            //statement
            Statement statement = conn.createStatement();
            String command = "INSERT INTO PAYMENTMETHOD(PAYMETHODID, PAYMETHODCARDNO, PAYMETHODCARDHOLDER, PAYMETHODCARDSECURITY,PAYMETHODCARDEXPIRY) VALUES(?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(command);
            //set id to customer's id
            ResultSet retrieveResult = statement.executeQuery("SELECT CUSTOMERID FROM CUSTOMER WHERE CUSTOMEREMAIL = '" + email + "'");
            retrieveResult.next();
            int ID = retrieveResult.getInt(1);
            pst.setObject(1, ID);
            pst.setObject(2, cardno);
            pst.setObject(3, cardname);
            pst.setObject(4, cardcvv);
            pst.setDate(5, java.sql.Date.valueOf(cardDate));
            pst.executeUpdate();   
            
            System.out.println("done");
        } catch (Error e){
        }
    }
    //get payment method
    public PaymentMethod getPayMethod(String email){
        try {
        System.out.println("here1");
        //get all payment methods using customer email to get customer id
        //paymethodid == customerid
        ResultSet resultSet = st.executeQuery("SELECT*FROM PAYMENTMETHOD WHERE PAYMETHODID=(SELECT CUSTOMERID FROM CUSTOMER WHERE  CUSTOMEREMAIL = '" + email + "')");
        if(!resultSet.next()){
            return null;
        }        
        //Copy items from DB into a payment method object
        PaymentMethod paymethod = new PaymentMethod(
            resultSet.getInt("PAYMETHODID"),
            resultSet.getInt("PAYMETHODCARDNO"),
            resultSet.getString("PAYMETHODCARDHOLDER"), 
            resultSet.getInt("PAYMETHODCARDSECURITY"), 
            resultSet.getDate("PAYMETHODCARDEXPIRY")
        );
        return paymethod;
    } catch (SQLException ex) {
        System.out.println("Error Establishing Connection!");
        ex.printStackTrace();
    }
    return null;
    }
    //update payment method entry
    public void updatePaymentMethod(String email, int cardno, String cardname, String cardDate, int cardcvv) throws Exception{
        try {
            ResultSet resultSet = st.executeQuery("SELECT * FROM PAYMENTMETHOD WHERE PAYMETHODID=(SELECT CUSTOMERID FROM CUSTOMER WHERE  CUSTOMEREMAIL = '" + email + "')");
            resultSet.next();
            //update statement
            PreparedStatement update = conn.prepareStatement("UPDATE PAYMENTMETHOD SET PAYMETHODID=?, PAYMETHODCARDNO=?, PAYMETHODCARDHOLDER=?, PAYMETHODCARDSECURITY=?, PAYMETHODCARDEXPIRY=? WHERE PAYMETHODID=?");
            //Set the variables for the "update" statement
            update.setInt(1, resultSet.getInt("PAYMETHODID"));
            update.setInt(2, cardno);
            update.setString(3, cardname);
            update.setInt(4, cardcvv);
            update.setDate(5, java.sql.Date.valueOf(cardDate));
            update.setInt(6, resultSet.getInt("PAYMETHODID"));
            update.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("Error Establishing Connection!");
            ex.printStackTrace();
    }
    }
}
    
    
