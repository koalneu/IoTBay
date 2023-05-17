/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import models.dao.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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
            ArrayList<Product> products = (ArrayList<Product>) session.getAttribute("products");
            Product productAdded = new Product();
            for(Product product : products){
                if(product.getProductName().equals(productName)){
                    productAdded = product;
                }
            } if(user == null){
                user = new User();
                session.setAttribute("user", user);
            }
            if(order == null){
                try{
                    if(user.getUserType().equals("guest")){
                        order = new Order (manager.countOrderRows(), 0);
                    } else {
                        order = new Order (manager.countOrderRows(), manager.getUserID(user.getUserEmail()));
                    }
                    
                }catch(SQLException ex){
                    System.out.print("unable to get user ID in orderController");
                }
                order.addToOrderLine(new OrderLine (productAdded, order.getOrderID(), 1));
            }
            else if (order.checkProduct(productAdded) != null){
                OrderLine productToAdd = order.checkProduct(productAdded);
                order.addQuantity(productToAdd);
            }
            else{
                order.addToOrderLine(new OrderLine(productAdded, order.getOrderID(), 1));
            }
            session.setAttribute("order", order);
            request.getRequestDispatcher("cart.jsp").include(request, response);
        } 
        else{
            request.getRequestDispatcher("cart.jsp").include(request, response);
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
        
    }


}
