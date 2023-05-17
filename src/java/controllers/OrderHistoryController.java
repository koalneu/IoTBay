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
public class OrderHistoryController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        DBManager manager = (DBManager) session.getAttribute("manager");
        User user = (User) session.getAttribute("user");
        ArrayList<Order> orderHistory = new ArrayList<Order>();
        String action = request.getParameter("action");
        if(action.equals("saved")){
            try{            
               orderHistory = manager.getOrderHistory(manager.getUserID(user.getUserEmail()), false);
               
            }
            catch (SQLException ex){
                System.out.println("unable to retrieve saved orders " + ex.getMessage());
            }
            session.setAttribute("orderHistory", orderHistory);
            session.setAttribute("action", action);
            request.getRequestDispatcher("savedOrders.jsp").include(request, response);
        }
        else if(action.equals("payed")){
            try{
               if(user.getUserType().equals("guest")){
                   //int ID = manager.checkGuestID(request.getParameter)
               }
               else{
                   orderHistory = manager.getOrderHistory(manager.getUserID(user.getUserEmail()), true);
               }
               
               
            } catch (SQLException ex){
                System.out.println("unable to retrieve saved orders " + ex.getMessage());
            }
            
            session.setAttribute("orderHistory", orderHistory);
            session.setAttribute("action", action);
            request.getRequestDispatcher("orderHistory.jsp").include(request, response);
        }        
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
}
