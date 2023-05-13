/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dao.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.*;

/**
 *
 * @author mjra9
 */
public class OrderController extends HttpServlet {


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        DBManager manager = (DBManager) session.getAttribute("manager");
        String productName = request.getParameter("productName");
        if(productName != null){
            User user = (User) session.getAttribute("user");
            Order order = (Order) session.getAttribute("order");
            LinkedList<Product> products = (LinkedList<Product>) session.getAttribute("products");
            Product productAdded = new Product();
            for(Product product : products){
                if(product.getName().equals(productName)){
                    productAdded = product;
                }
            }
            if(order == null){
                try{
                    order = new Order (manager.countOrderRows(), manager.getUserID(user.getUserEmail()));
                }catch(SQLException ex){
                    System.out.print("unable to get user ID in orderController");
                }
                order.getProducts().put(productAdded, 1);
            }
            else if (order.getProducts().containsKey(productAdded)){
                HashMap<Product, Integer> productsInOrder = order.getProducts();
                productsInOrder.put(productAdded, productsInOrder.get(productAdded) + 1);

            }
            else{
                HashMap<Product, Integer> productsInOrder = order.getProducts();
                productsInOrder.put(productAdded, 1);
            }
            session.setAttribute("order", order);
            request.getRequestDispatcher("profile.jsp").include(request, response);
        }
        else{
            request.getRequestDispatcher("order.jsp").include(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("checkout.jpg").include(request, response);
    }


}
