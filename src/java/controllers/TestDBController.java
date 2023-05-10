package controllers;

import java.sql.*;
import java.util.*;
import java.util.logging.*;
import models.Product;
import models.dao.*;

/**
 *
 * @author benjamin
 */
public class TestDBController {
    private static Scanner in = new Scanner(System.in);
    private DBConnector connector;
    private Connection conn;
    private DBManager db;
    
    public static void main(String[] args) throws SQLException {
        (new TestDBController()).runQueries();
    }
    
    public TestDBController() {
        try {
            connector = new DBConnector();
            conn = connector.openConnection();
            db = new DBManager(conn);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TestDBController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private char readChoice() {
        System.out.print("Operation CRUDS or * to exit: ");
        return in.nextLine().charAt(0);
    }
    
    private void runQueries() throws SQLException {
        char c;
        while ((c = readChoice()) != '*') {
            switch (c) {
                case 'C':
                    testAdd();
                    break;
                case 'R':
                    testRead();
                    break;
                case 'U':
                    testUpdate();
                    break;
                case 'D':
                    testDelete();
                    break;
                case 'S':
                    showAll();
                    break;
                default:
                    System.out.println("Unknown command");
                    break;
            }
        }
    }
    
    private void testAdd() {
        System.out.print("Product name: ");
        String name = in.nextLine();
        System.out.print("Product description: ");
        String desc = in.nextLine();
        System.out.print("Product price: $");
        //String priceS = in.nextLine();
        //double price = Double.parseDouble(priceS);
        double price = Double.parseDouble(in.nextLine());
        System.out.print("Product image location: ");
        String image = in.nextLine();
        System.out.print("Product stock: ");
        String stockS = in.nextLine();
        int stock = Integer.parseInt(String.valueOf(stockS));
        try {
            db.addProduct(name, desc, price, image, stock);
        } catch (SQLException ex) {
            Logger.getLogger(TestDBController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(name+" has been added to the database");
    }
    
    private void testRead() throws SQLException {
        System.out.print("Product name: ");
        String name = in.nextLine();
        Product product = db.findProduct(name);
        if (product != null) {
            System.out.println("Product " + product.getProductName() + " found in database");
        } else {
            System.out.println("Product does not exist");
        }
    }
    
    private void testUpdate() {
        System.out.print("Product name: ");
        String name = in.nextLine();
        
        try {
            if (db.checkProduct(name)) {
                int id = db.findProduct(name).getProductID();
                System.out.print("Product name: ");
                name = in.nextLine();
                System.out.print("Product description: ");
                String desc = in.nextLine();
                System.out.print("Product price: ");
                double price = Double.parseDouble(in.nextLine());
                System.out.print("Product image: ");
                String image = in.nextLine();
                int stock = Integer.parseInt(in.nextLine());
                db.updateProduct(id, name, desc, price, image, stock);
            } else {
                System.out.println("Product does not exist");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestDBController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void testDelete() {
        System.out.print("Product name: ");
        String name = in.nextLine();
        
        try {
            if (db.checkProduct(name)) {
                db.deleteProduct(name);
            } else {
                System.out.println("Product does not exist");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestDBController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void showAll() {
        try {
            ArrayList<Product> products = db.fetchProducts();
            System.out.println("PRODUCTS TABLE: ");
            products.stream().forEach((product) -> {
                System.out.printf("%-10d %-50s %-50s %-10f %-10d \n", product.getProductID(), product.getProductName(), product.getProductDesc(), product.getProductPrice(), product.getProductStock());
            });
            System.out.println();
        } catch (SQLException ex) {
            Logger.getLogger(TestDBController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
