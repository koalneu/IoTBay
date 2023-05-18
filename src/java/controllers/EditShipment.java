/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Order;
import models.Shipment;
import models.User;
import models.dao.DBManager;

/**
 *
 * @author milad
 */
public class EditShipment extends HttpServlet {

     
    /**ses requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
     @Override

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    HttpSession session = request.getSession();
    Validator validator = new Validator();
    DBManager manager = (DBManager) session.getAttribute("manager");
    //Order order = (Order) session.getAttribute("order");
    User user = (User) session.getAttribute("user");
    Shipment shipment = (Shipment) session.getAttribute("shipment");
    
    int shipmentID = shipment.getShipmentID();
    int orderID = shipment.getOrderID();
    
    validator.clear(session);
    
    String street = request.getParameter("street");
    String city = request.getParameter("city");
    String postcode = request.getParameter("postcode");
    String state = request.getParameter("state");
    String country = request.getParameter("country");
    int postCode = Integer.parseInt(postcode);
    String method = request.getParameter("method");
    //int orderID = shipment.getOrderID();
    
    try {
        if (!validator.validateStringPattern(street)) {
            session.setAttribute("streetErr", "Error: street name must be less than 50 characters");
            request.getRequestDispatcher("editDetails.jsp").include(request, response);
        } else if (!validator.validateStringPattern(city)) {
            session.setAttribute("cityErr", "Error: city name must be less than 50 characters");
            request.getRequestDispatcher("editDetails.jsp").include(request, response);
        } else if (!validator.validatepostCode(postcode)) {
            session.setAttribute("postcodeErr", "Error: postcode format incorrect");
            request.getRequestDispatcher("editDetails.jsp").include(request, response);
        } else if (!validator.validateStringPattern(country)) {
            session.setAttribute("countryErr", "Error: country name must be less than 50 characters");
            request.getRequestDispatcher("editDetails.jsp").include(request, response);
        } else {
            manager.updateShipment(shipmentID, street, postCode, city, country, method,orderID, state);
            shipment = new Shipment(shipmentID,orderID, street, postCode, city, country, method, state);
            session.setAttribute("shipment", shipment);
            request.getRequestDispatcher("shipmentDetails.jsp").include(request, response);
        }
    } catch (SQLException ex) {
        Logger.getLogger(EditShipment.class.getName()).log(Level.SEVERE, null, ex);
        //response.sendRedirect("index.jsp"); 
    }
}
}
