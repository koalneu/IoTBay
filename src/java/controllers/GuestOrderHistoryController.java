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
public class GuestOrderHistoryController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        DBManager manager = (DBManager) session.getAttribute("manager");
        ArrayList<Order> orderHistory = new ArrayList<Order>();
        String action = request.getParameter("action");
        Validator validator = new Validator();
        validator.clear(session);
        if(action.equals("saved")){
            session.setAttribute("orderHistory", orderHistory);
            session.setAttribute("action", action);
            request.getRequestDispatcher("guestSavedOrders.jsp").include(request, response);
        }
        else if(action.equals("payed")){
            session.setAttribute("orderHistory", orderHistory);
            session.setAttribute("action", action);
            request.getRequestDispatcher("guestOrderHistory.jsp").include(request, response);
        }         
    }

}
