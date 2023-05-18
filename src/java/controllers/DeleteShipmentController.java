/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;


import models.dao.DBManager;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.*;
import models.dao.*;
/**
 *
 * @author Milad
 */
public class DeleteShipmentController extends HttpServlet{
    @Override
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        DBManager manager = (DBManager) session.getAttribute("manager");
        User user = (User)session.getAttribute("user");
        Shipment shipment = (Shipment) session.getAttribute("shipment");
        try{
        manager.deleteShipment(shipment.getShipmentID());
        shipment = manager.getShipment(shipment.getShipmentID());
        session.setAttribute("shipment", shipment);
        request.getRequestDispatcher("shipmentDetails.jsp").include(request, response); 
        }
        
         catch (SQLException ex)
                {
                    Logger.getLogger(DeleteShipmentController.class.getName()).log(Level.SEVERE, null, ex);
                }
        
        session.setAttribute("shipment", shipment);
        
    }
}