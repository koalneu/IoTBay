/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
public class CheckoutController extends HttpServlet {


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
        Order order = (Order) session.getAttribute("order");
        String action = request.getParameter("action");
        if(action.equals("save")){
            try{
                manager.addOrder(order);
            } catch(SQLException ex){
                System.out.println("error adding order to database " + order.getOrderID() + " " + ex.getMessage());
            }
        }
        else if(action.equals("pay")){
            order.setIsPayed(true);
            try{
                manager.addOrder(order);
            } catch(SQLException ex){
                System.out.println("error adding order to database"  + order.getOrderID() + " " + ex.getMessage());
            }
        }
        session.setAttribute("order", null);
        response.sendRedirect("index.jsp");
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
