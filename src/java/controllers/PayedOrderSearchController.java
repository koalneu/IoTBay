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
import models.Order;
import models.User;
import models.dao.DBManager;

/**
 *
 * @author mjra9
 */
public class PayedOrderSearchController extends HttpServlet {

    
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
        //processRequest(request, response);
        HttpSession session = request.getSession();
        DBManager manager = (DBManager) session.getAttribute("manager");
        ArrayList<Order> orderHistory = (ArrayList<Order>) session.getAttribute("orderHistory");
        User user =(User) session.getAttribute("user");
        String date = request.getParameter("date");
        String ID = request.getParameter("ID");
        if(date.equals("") && ID.equals("")){
            try{
                orderHistory = manager.getOrderHistory(manager.getUserID(user.getUserEmail()), true);
            }catch(SQLException ex){
                System.out.println("error in searching for orders with no search in payedordercontoller " + ex.getMessage());
            } 
            
            session.setAttribute("orderHistory", orderHistory);
        }
        else if(!date.equals("") && ID.equals("")){
            try{
                orderHistory = manager.findOrders(date, manager.getUserID(user.getUserEmail()), true);
            }catch(SQLException ex){
                System.out.println("error in searching for orders with date in payeddordercontoller " + ex.getMessage());
            } 
        }
        else if(date.equals("") && !ID.equals("")){
            int orderID = Integer.parseInt(ID);
            try{
                orderHistory.clear();
                Order order = manager.findOrder(orderID, manager.getUserID(user.getUserEmail()), true);
                if (order != null){
                    orderHistory.add(order);
                }
            }catch(SQLException ex){
                System.out.println("error in searching for orders with ID in payedordercontoller " + ex.getMessage());
            } 
        }
        else{
            int orderID = Integer.parseInt(ID);
            try{
                orderHistory.clear();
                Order order = manager.findOrder(orderID, manager.getUserID(user.getUserEmail()), true, date);
                if (order != null){
                    orderHistory.add(order);
                }
            }catch(SQLException ex){
                System.out.println("error in searching for orders with ID & date in payedordercontoller " + ex.getMessage());
            }   
        }
        session.setAttribute("orderHistory", orderHistory);
        request.getRequestDispatcher("orderHistory.jsp").include(request, response);
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
