package models.dao;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import models.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    //order implementation CRUD
    //Functions from Mark's branch
    
    public void addGuestUser(String email) throws SQLException{
        String fetch = "SELECT * FROM IOTADMIN.CUSTOMER";
        ResultSet rs = st.executeQuery(fetch);
        int ID = 0;
        while(rs.next()){
            int temp = rs.getInt(1) + 1;
            if(temp > ID){
                ID = temp;
            }
        }
        st.executeUpdate("INSERT INTO IOTADMIN.CUSTOMER (CUSTOMERID, CUSTOMEREMAIL, USERTYPE)" +
                "VALUES ( " +ID + ", \'" +email+ "\', \'guest\')");
    }
    public User getGuestUser(String email) throws SQLException{
        String fetch = "SELECT * FROM IOTADMIN.CUSTOMER WHERE CUSTOMEREMAIL = \'" +email+"\' AND USERTYPE = \'guest\'";
        ResultSet rs = st.executeQuery(fetch);
        while(rs.next()){
            String userEmail = rs.getString("CUSTOMEREMAIL");
            if(userEmail.equals(email)){
                User user = new User();
                user.setUserEmail(userEmail);
                return user;
            }
        }
        return null;
    }
    public int countOrderRows() throws SQLException {
        String fetch = "SELECT * FROM IOTADMIN.ORDERS";
        ResultSet rs = st.executeQuery(fetch);
        int ID = 0;
        while(rs.next()){
            int temp = rs.getInt(1) + 1;
            if(temp > ID){
                ID = temp;
            }
        }
        return ID;
    }
    public int getUserID(String email) throws SQLException {
        String fetch = "SELECT * FROM IOTADMIN.CUSTOMER WHERE CUSTOMEREMAIL = " + "\'" + email + "\'";
        ResultSet rs = st.executeQuery(fetch);
        if(rs.next()){
           int ID = rs.getInt("CUSTOMERID");
           return ID;
        }
        return -1;
    }
    
    public int checkGuestID(String email) throws SQLException{
        String fetch = "SELECT * FROM IOTADMIN.CUSTOMER WHERE CUSTOMEREMAIL = " + "\'" + email + "\' AND USERTYPE = \'guest\'";
        ResultSet rs = st.executeQuery(fetch);
        if(rs.next()){
           int ID = rs.getInt("CUSTOMERID");
           return ID;
        }
        return -1;
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
            if(isPayed){
                updateProductStock(product.getProduct().getProductID(), product.getProduct().getProductStock() - product.getQuantity());
            }
        }
    }
    
    
    public boolean findOrderLine(OrderLine orderLine) throws SQLException{
        String fetch = "SELECT  * FROM IOTADMIN.ORDERLINEITEM WHERE PRODUCTID=" + orderLine.getProduct().getProductID() + " AND ORDERID =" + orderLine.getOrderID();
        ResultSet rs = st.executeQuery(fetch);
        while(rs.next()){
            int oID = rs.getInt("ORDERID");
            int pID = rs.getInt("PRODUCTID");
            if(pID == orderLine.getProduct().getProductID() && oID == orderLine.getOrderID()){
                return true;
            }
        }
        return false;
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
    
    public ArrayList<Order> findOrders(String date, int userID, boolean isPayed) throws SQLException{
        String fetch = "SELECT * FROM IOTADMIN.ORDERS WHERE CUSTOMERID =" + userID + "AND ORDERDATE =\'" + date +"\'" + "AND ISPAYED =" + isPayed;
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
    // using orderID, userID, isPayed, date
    public Order findOrder(int orderID, int userID, boolean isPayed, String date)throws SQLException{
        String fetch = "SELECT * FROM IOTADMIN.ORDERS WHERE CUSTOMERID =" + userID + " AND ORDERID = " + orderID + " AND ORDERDATE =\'" + date +"\'" + "AND ISPAYED =" + isPayed;
        ResultSet rs = st.executeQuery(fetch);
        while(rs.next()){
            int ID = rs.getInt("ORDERID");
            if(orderID == ID){
                int uID = rs.getInt("CUSTOMERID");
                String dt = rs.getString("ORDERDATE");
                String status = rs.getString("ORDERSTATUS");
                boolean payed = rs.getBoolean("ISPAYED");
                ArrayList<OrderLine> orderLine= new ArrayList<>();
                return new Order(ID, uID, status, dt, payed, orderLine); 
            }  
        }
        return null;
    }
    public Order findOrder(int orderID, int userID, boolean isPayed)throws SQLException{
        String fetch = "SELECT * FROM IOTADMIN.ORDERS WHERE CUSTOMERID =" + userID + " AND ORDERID = " + orderID  + "AND ISPAYED =" + isPayed;
        ResultSet rs = st.executeQuery(fetch);
        while(rs.next()){
            int ID = rs.getInt("ORDERID");
            if(orderID == ID){
                int uID = rs.getInt("CUSTOMERID");
                String dt = rs.getString("ORDERDATE");
                String status = rs.getString("ORDERSTATUS");
                boolean payed = rs.getBoolean("ISPAYED");
                ArrayList<OrderLine> orderLine= new ArrayList<>();
                return new Order(ID, uID, status, dt, payed, orderLine); 
            }  
        }
        return null;
    }
    //delete order and its orderline
    public void deleteOrder(int orderID) throws SQLException{
        st.executeUpdate("DELETE FROM IOTADMIN.ORDERS WHERE ORDERID = " + orderID);
        
    }
    public void deleteOrderLine(int orderID) throws SQLException{
        st.executeUpdate("DELETE FROM IOTADMIN.ORDERLINEITEM WHERE ORDERID = " + orderID);
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
    
    //updates order
    public void updateOrder(Order order) throws SQLException{
        SimpleDateFormat sm = new SimpleDateFormat("MM/dd/yyyy");
        String date = sm.format(new Date());
        int userID = order.getUserID();
        boolean isPayed = order.isIsPayed();
        st.executeUpdate("UPDATE IOTADMIN.ORDERS SET ORDERDATE = \'" + date +"\' , ISPAYED =" + isPayed + ", CUSTOMERID = "+userID+" WHERE ORDERID = " + order.getOrderID());
        deleteOrderLine(order.getOrderID());
        for(OrderLine orderLine : order.getOrderLine()){
            addOrderLine(orderLine);
            if(isPayed){
                updateProductStock(orderLine.getProduct().getProductID(), orderLine.getProduct().getProductStock() - orderLine.getQuantity());
            }
        }
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
    //update only the stock of a product by ID
    public void updateProductStock(int productID, int stock) throws SQLException {
        st.executeUpdate("UPDATE IOTADMIN.PRODUCT SET PRODUCTSTOCK="+stock+" WHERE PRODUCTID="+productID );
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
    //delete payment method entry
    public void deletePaymentMethod(String email){
        try{
            PreparedStatement delete = conn.prepareStatement("DELETE FROM PAYMENTMETHOD WHERE PAYMETHODID=(SELECT CUSTOMERID FROM CUSTOMER WHERE CUSTOMEREMAIL='"+email+"')");
            delete.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error Establishing Connection!");
            ex.printStackTrace();
        }
    }
    
    public int getOrderID(int ID) throws SQLException {
        String fetch = "SELECT ORDERID FROM ORDERS WHERE ORDERID = " + "\'" + ID + "\'";
        ResultSet rs = st.executeQuery(fetch);
        rs.next();
        int orderID = rs.getInt("ORDERID");
        return orderID;
    }
    public Shipment findShipment(int ID) throws SQLException {
        String fetch = "select * from IOTADMIN.DELIVERY where DELIVERYID = " + ID + "";
        ResultSet rs = st.executeQuery(fetch);
        
        while (rs.next()) {
            int shipmentID = rs.getInt(1);
            if (shipmentID == ID) {
                String shipmentStreet = rs.getString(2);
                int shipmentPostCode = rs.getInt(3);
                String shipmentCity = rs.getString(4);
                String shipmentState = rs.getString(5);
                String shipmentCountry = rs.getString(6);
                int orderID = rs.getInt(7);
                String shipmentMethod = rs.getString(8);
              

                return new Shipment(shipmentID,orderID, shipmentStreet, shipmentPostCode, shipmentCity,shipmentCountry, shipmentMethod,shipmentState );            }
        }
        return null;
    }
    
     public Shipment findShipment(String street) throws SQLException {
        String fetch = "select * from IOTADMIN.DELIVERY where DELIVERYSTREET = '" + street + "'";
        ResultSet rs = st.executeQuery(fetch);
        
        while (rs.next()) {
            String shipmentStreet = rs.getString(2);
            if (shipmentStreet.equals(street)) {
                 int shipmentID = rs.getInt(1);
               
                int shipmentPostCode = rs.getInt(3);
                String shipmentCity = rs.getString(4);
                String shipmentState = rs.getString(5);
                String shipmentCountry = rs.getString(6);
                int orderID = rs.getInt(7);
                String shipmentMethod = rs.getString(8);
              

                return new Shipment(shipmentID,orderID, shipmentStreet, shipmentPostCode, shipmentCity,shipmentCountry, shipmentMethod,shipmentState );
            }
        }
        return null;
    }
     
    /**
     *
     * @param street
     * @param postCode
     * @param city
     * @param state
     * @param orderID
     * @param country
     * @param method
     * @throws SQLException
     */
    public void addShipment(String street, int postCode, String city,String state,String country,int orderID, String method) throws SQLException {
          /*String rows = "SELECT COUNT(*) FROM DELIVERY";
        ResultSet retrieveResult = st.executeQuery(rows);
        retrieveResult.next();
        
        int ID = retrieveResult.getInt(1);
        int orderID = retrieveResult.getInt(1);*/
        String rows = "SELECT COUNT(*) FROM DELIVERY";
        ResultSet rs = st.executeQuery(rows);
        int shipmentID = 0;
        
        while (rs.next()) {
            int temp = rs.getInt(1) + 1;
            if (temp > shipmentID) {
              shipmentID = temp;
             
            }   
        }
        
        
        st.executeUpdate("INSERT INTO IOTADMIN.DELIVERY " + "(DELIVERYID,DELIVERYSTREET,DELIVERYPOSTCODE,DELIVERYCITY,DELIVERYSTATE,DELIVERYCOUNTRY,ORDERID,DELIVERYMETHOD)" + " VALUES ("+shipmentID+", '"+street+"', "+postCode+", '"+city+"','"+state+"','"+country+"',"+orderID+", '"+method+"')");
    }
     public void linkShipment(int shipmentID, int orderID) throws SQLException{
       
        try{
            //statement
            String command ="INSERT INTO DELIVERY(DELIVERYID,ORDERID) VALUES ("+shipmentID+","+orderID+")";
            PreparedStatement pst = conn.prepareStatement(command);
            
            String rows = "select count(*) from IOTADMIN.DELIVERY";
            String row = "select count(*) FROM IOTADMIN.ORDERS";
            ResultSet retrieveResult = st.executeQuery(rows);
            ResultSet rs = st.executeQuery(row);
            retrieveResult.next();
            shipmentID = retrieveResult.getInt(1);
            orderID = rs.getInt(1);
            pst.setObject(1, shipmentID);
            pst.setObject(7, orderID);
           
            
                
            pst.executeUpdate();
               
            conn.close();
        }
        catch(Error e){
        }
     }
     
     
    public void updateShipment(int shipmentID, String street, int postCode, String city,String country, String method,int orderID, String state) throws SQLException {
        st.executeUpdate("UPDATE IOTADMIN.DELIVERY SET DELIVERYSTREET= '"+street+"',DELIVERYPOSTCODE= "+postCode+",DELIVERYCITY= '"+city+"',DELIVERYSTATE= '"+state+"',DELIVERYCOUNTRY= '"+country+"',ORDERID= "+orderID+" ,DELIVERYMETHOD= '"+method+"' WHERE DELIVERYID= "+shipmentID+"");
    }         
    
     public void deleteShipment(int ID) throws SQLException {
        st.executeUpdate("DELETE FROM IOTADMIN.DELIVERY WHERE DELIVERYID="+ID+"");
        
    }
         //fetch list of shipments
    public ArrayList<Shipment> fetchShipment() throws SQLException {
        String fetch = "SELECT * FROM DELIVERY";
        ResultSet rs = st.executeQuery(fetch);
        ArrayList<Shipment> temp = new ArrayList();
        
        while (rs.next()) {
            int ID = rs.getInt(1);
            String street = rs.getString(2);
            int postCode = rs.getInt(3);
            String city = rs.getString(4);
            String state = rs.getString(5);
            String country = rs.getString(6);
            int orderID = rs.getInt(7);
            String method = rs.getString(8);
            temp.add(new Shipment(ID,orderID, street, postCode, city,state, country, method));
        }
        return temp;
    }
     public boolean checkShipment(int ID) throws SQLException {
        String fetch = "SELECT * FROM IOTADMIN.DELIVERY WHERE DELIVERYID="+ID+"";
        ResultSet rs = st.executeQuery(fetch);
        
        while (rs.next()) {
            int shipmentID = rs.getInt(1);
            if (shipmentID == ID) {
                return true;
            }
        }
        return false;
    }
      public Shipment getShipment(int shipmentID){
        try {
        
        ResultSet resultSet = st.executeQuery("SELECT*FROM DELIVERY WHERE DELIVERYID = " + shipmentID + ")");
        if(!resultSet.next()){
            return null;
        }        
        //Copy items from DB into a payment method object
        Shipment shipment = new Shipment(
            resultSet.getInt("DELIVERYID"),
            resultSet.getInt("ORDERID"),
            resultSet.getString("DELIVERYSTREET"), 
            resultSet.getInt("DELIVERYPOSTCODE"),
            resultSet.getString("DELIVERYCITY"),
            resultSet.getString("DELIVERYCOUNTRY"),
            resultSet.getString("DELIVERYMETHOD"), 
            resultSet.getString("DELIVERYSTATE")
             
        );
        return shipment;
    } catch (SQLException ex) {
        System.out.println("Error Establishing Connection!");
        ex.printStackTrace();
    }
    return null;
      }
}
