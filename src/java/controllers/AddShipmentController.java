/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Shipment;
import models.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.dao.DBManager;
/**
 *
 * @author milad
 */
public class AddShipmentController extends HttpServlet {
   
    @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
         Validator validator = new Validator();
        DBManager manager = (DBManager) session.getAttribute("manager");
        Order order = (Order) session.getAttribute("order");
        User user = (User) session.getAttribute("user");
        Shipment shipment = (Shipment) session.getAttribute("shipment");
        ArrayList<Shipment> shipments = new ArrayList<Shipment>();
        validator.clear(session);


        
            
            String street = request.getParameter("street");
            String city = request.getParameter("city");
            String postcode = request.getParameter("postcode");
            String state = request.getParameter("state");
            String country = request.getParameter("country");
            int postCode = Integer.parseInt(postcode);
            String method = request.getParameter("method");
            int orderID = order.getOrderID();
            //int shipmentID = shipment.getShipmentID();
            // Set the address details in the shipment object
          
            try {
           
                if(!validator.validateStringPattern(street)){
            session.setAttribute("streetErr", "Error: street name must be less than 50 characters");
            request.getRequestDispatcher("addShipment.jsp").include(request, response);
            }
            else if(!validator.validateStringPattern(city)){
            session.setAttribute("cityErr", "Error: city name must be less than 50 characters");
            request.getRequestDispatcher("addShipment.jsp").include(request, response);
            }
            else if(!validator.validatepostCode(postcode)){
            session.setAttribute("postcodeErr", "Error: postcode format incorrect");
            request.getRequestDispatcher("addShipment.jsp").include(request, response);
            }
            else if(!validator.validateStringPattern(country)){
            session.setAttribute("countryErr", "Error: country name must be less than 50 characters");
            
            request.getRequestDispatcher("addShipment.jsp").include(request, response);
            }
            else{
                 
                    if (shipment == null) {
                     
                    shipment = new Shipment();

                    //manager.linkShipment(shipmentID,orderID);
                    manager.addShipment(street, postCode, city, state, country,orderID, method);
                    shipment = manager.findShipment(street);
                    //shipments = manager.fetchShipment();// Add the shipment to the ArrayList

                    session.setAttribute("shipment", shipment);
                    request.getRequestDispatcher("shipmentDetails.jsp").include(request, response);
                }
                }
               
                
            }
            catch (SQLException ex)
                {
                    Logger.getLogger(AddShipmentController.class.getName()).log(Level.SEVERE, null, ex);
                }
        
                  
    }
   
           
            
    
                
   


   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        DBManager manager = (DBManager) session.getAttribute("manager");
        int shipmentID = Integer.parseInt(request.getParameter("shipmentID"));
        Shipment shipment = null;
        try {
            shipment = manager.findShipment(shipmentID);
            if (shipment == null)
            {
            session.setAttribute("searchShipmentErr", "shipment not found");
            request.getRequestDispatcher("shipmentDetails.jsp").forward(request, response);

            }
            else{
            session.setAttribute("shipment", shipment);
            request.getRequestDispatcher("shipmentDetails.jsp").forward(request, response);
            }
            } 
        catch (SQLException ex) {
            Logger.getLogger(AddShipmentController.class.getName()).log(Level.SEVERE, null, ex);
            // Handle the error appropriately, e.g., show an error message to the user
        }
    }
}

