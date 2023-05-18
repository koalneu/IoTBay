<%@page import="java.util.Objects"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="models.dao.*" %>
<%@page import="models.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shipment details</title>
    </head>
    <body>
        <%
            User user = (User) session.getAttribute("user");
        Shipment shipment = (Shipment) session.getAttribute("shipment");
        Order order = (Order) session.getAttribute("order");
     DBManager manager = (DBManager) session.getAttribute("manager");
        %>
        <table align="center">
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
        
        
        <button><a href="shipmentDetails.jsp">Back</a></button>
        </table>
    </body>
</html>
