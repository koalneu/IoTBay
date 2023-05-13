package models.dao;

import models.Product;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author benjamin
 */
public class DBManager {
    private Statement st;
    
    public DBManager(Connection conn) throws SQLException {
        st = conn.createStatement();
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
}
