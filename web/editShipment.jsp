<%-- 
    Document   : editShipment
    Created on : 17/05/2023, 1:18:44 PM
    Author     : milad
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="models.*"%>
<%@page import="models.dao.*"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.Map"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Shopping Details</title>
    </head>
    <% //} else {
        // User is not logged in or does not have a shipment object, display blank form

        String errorMessage = (String) request.getSession().getAttribute("errorMessage"); 

        User user = (User) session.getAttribute("user");
        Shipment shipment = (Shipment) session.getAttribute("shipment");
        Order order = (Order) session.getAttribute("order");
        String state = shipment.getShipmentState();
        String method = shipment.getShipmentMethod();
                    
            //Retrieve errors
            String streetErr = (String) session.getAttribute("streetErr");
            String cityErr = (String) session.getAttribute("cityErr");
            String postcodeErr = (String) session.getAttribute("postcodeErr");
            String countryErr = (String) session.getAttribute("countryErr");
    %>
    <body>
        <form method="post" action="EditShipment" >
            <table align="center" >
<tr>
                    <th><label>Street:</label></th>
                    <th><input type="text" name="street" value="${shipment.shipmentStreet}" required/></th>
                </tr>
                <tr>
                    <th><label>City:</label></th>
                    <th><input type="text" name="city" value="${shipment.shipmentCity}" required/></th>
                </tr>
                <tr>
                    <th><label>PostCode:</label></th>
                    <th><input type="text" name="postcode" value="${shipment.shipmentPostCode}" required/></th>
                </tr>
                <tr>
                    <th><label>State:</label>
                    <th>
                        <select name="state" required>
                            <option value="">Select a state...</option>
                            <option value="ACT" <% if (state.equals("ACT")) { %>selected<% } %>>ACT</option>
                            <option value="NSW" <% if (state.equals("NSW")) { %>selected<% } %>>NSW</option>
                            <option value="NT" <% if (state.equals("NT")) { %>selected<% } %>>NT</option>
                            <option value="QLD" <% if (state.equals("QLD")) { %>selected<% } %>>QLD</option>
                            <option value="SA" <% if (state.equals("SA")) { %>selected<% } %>>SA</option>
                            <option value="VIC" <% if (state.equals("VIC")) { %>selected<% } %>>VIC</option>
                            <option value="TAS" <% if (state.equals("TAS")) { %>selected<% } %>>TAS</option>
                            <option value="WA" <% if (state.equals("WA")) { %>selected<% } %>>WA</option>
                        </select>
                    </th>
                </tr>
                <tr>
                    <th><label>Country:</label></th>
                    <th><input type="text" name="country" placeholder="Enter your country.." value="${shipment.shipmentCountry}" required/></th>
                </tr>
                <tr>
                    <th><label>Shipping Method:</label>
                    <th>
                        <select name="state" required>
                            <option value="">Select a Shipping Method...</option>
                            <option value="ACT" <% if (method.equals("postOffice")) { %>selected<% } %>>Post Office</option>
                            <option value="NSW" <% if (method.equals("fedex")) { %>selected<% } %>>FedEx</option>
                           
                        </select>
                    </th>
                </tr>
                <tr>    
            </table>
        </form>
      </body>
</html>
