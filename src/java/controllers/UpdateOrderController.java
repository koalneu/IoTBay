/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.*;
import models.dao.DBManager;

/**
 *
 * @author mjra9
 */
public class UpdateOrderController extends HttpServlet {

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
        Order order = (Order) session.getAttribute("order");
        User user = (User) session.getAttribute("user");
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        String action = request.getParameter("action");
        if(action.equals("delete")){
            try{
                manager.deleteOrderLine(orderID);
                manager.deleteOrder(orderID);
            }catch(SQLException ex){
                System.out.println("error in trying to delete order in updateOrderController " + ex.getMessage());
            }
            request.getRequestDispatcher("index.jsp").include(request, response);
        }
        else if(action.equals("edit")){
            try{
                order = manager.findOrder(orderID);
                order.setOrderLine(manager.getOrderLine(orderID));
                if(user == null){
                   user = new User();
                   order.setUserID(0);
               }
               else if(user.getUserType().equals("guest")){
                   order.setUserID(0);
               }
                session.setAttribute("order", order);
                request.getRequestDispatcher("cart.jsp").include(request, response);
            }catch(SQLException ex){
                System.out.println("error in trying to edit order in updateOrderController " + ex.getMessage());
            }
        }
        else if(action.equals("set")){
            try{
               order = manager.findOrder(orderID);
               order.setOrderLine(manager.getOrderLine(orderID));
               if(user == null){
                   user = new User();
                   order.setUserID(0);
               }
               if(user.getUserType().equals("guest")){
                   order.setUserID(0);
               }
               for(OrderLine orderLine : order.getOrderLine()){
                   orderLine.setOrderID(order.getOrderID());
               }
            }catch(SQLException ex){
                System.out.println("error seting order in UpdateOrderController " + ex.getMessage());
            }
            session.setAttribute("order", order);
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
        //processRequest(request, response);
    }

}
