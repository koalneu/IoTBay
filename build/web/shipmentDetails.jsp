<%-- 
    Document   : shipmentConfirmation
    Created on : 16/05/2023, 3:17:10 AM
    Author     : milad
--%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="models.User" %>
<%@page import="models.Shipment" %>
<%@page import="models.Order" %>
<%@page import="models.AccessLog"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.Map"%>

     <%User user = (User) session.getAttribute("user");
        Shipment shipment = (Shipment) session.getAttribute("shipment");
        Order order = (Order) session.getAttribute("order");%>  
 

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Shipment Details</title>
</head>
<body>
    <h1>Shipment Details</h1>
    <form action="AddShipmentController" method="get">
        <table align="center" >
            
           <tr>
                <th><label>Shipment ID</label></th>
                <th><input type="text" name="shipmentID" required/></th>
            </tr>
            <th><input type="submit" value="Search"></th>
        </table>
    </form>
    
    <% if (shipment != null) { %>
        <table>
            <tr>
                <th>Shipment ID:</th>
                <td><%= shipment.getShipmentID() %></td>
            </tr>
            <tr>
                <th>Order ID:</th>
                <td><%= shipment.getOrderID() %></td>
            </tr>
            <tr>
                <th>Street:</th>
                <td><%= shipment.getShipmentStreet() %></td>
            </tr>
            <tr>
                <th>City:</th>
                <td><%= shipment.getShipmentCity() %></td>
            </tr>
            <tr>
                <th>Postal Code:</th>
                <td><%= shipment.getShipmentPostCode() %></td>
            </tr>
            <tr>
                <th>State:</th>
                <td><%= shipment.getShipmentState() %></td>
            </tr>
            <tr>
                <th>Country:</th>
                <td><%= shipment.getShipmentCountry() %></td>
            </tr>
            <tr>
                <th>Shipment Method:</th>
                <td><%= shipment.getShipmentMethod() %></td>
            </tr>
        </table>
    <% } else { %>
        <p>No shipment details found.</p>
        
            <button><a href = "addShipment.jsp"> add </a></button>
        
    <% } %>
</body>
</html>
