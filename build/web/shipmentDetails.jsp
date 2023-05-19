<%-- 
    Document   : shipmentConfirmation
    Created on : 16/05/2023, 3:17:10 AM
    Author     : milad
--%>
<%@page import="java.util.Objects"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="models.*" %>
<%@page import="models.dao.*" %>
<%@page import="models.Order" %>
<%@page import="models.AccessLog"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>

     <%User user = (User) session.getAttribute("user");
        Shipment shipment = (Shipment) session.getAttribute("shipment");
        Order order = (Order) session.getAttribute("order");
     DBManager manager = (DBManager) session.getAttribute("manager");
      ArrayList shipmentList = manager.fetchShipment();
      session.setAttribute("shipments", shipmentList);
     %>  
       
 

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Shipment Details</title>
</head>
<body>
    <div class="header">
        <a href="index.jsp" class="left">IOT Store</a>
        <div class="right">
            <%
            boolean isLoggedIn = (user != null && !Objects.equals(user.getUserEmail(), ""));
            String saved = "saved";
            String payed = "payed";
             
            if (isLoggedIn) {
                //Logged In
        %>
            <button class="headerBtn"><a href="logout.jsp">Logout</a></button>
            <button class="headerBtn"><a href="profile.jsp">Profile</a></button>
            <button class="headerBtn"><a href="cart.jsp">View Order</a></button>
            <button class="headerBtn"><a href="OrderHistoryController?action=<%=saved%>">View Saved orders</a></button>
            <button class="headerBtn"><a href="OrderHistoryController?action=<%=payed%>"">View Order History</a></button>
            <button class="headerBtn"><a href="products.jsp">Product</a></button>
            <button class="headerBtn"><a href="payment.jsp">Payment</a></button>
            <button class="headerBtn"><a href="order.jsp">Order</a></button>

        <%
            } else {
                //Guest User ${pageContext.request.contextPath}/
        %>

            <th><button class="headerBtn"><a href="login.jsp">Login</a></button></th>
            <th><button class="headerBtn"><a href="register.jsp">Register</a></button></th>
            <th><button class="headerBtn"><a href="cart.jsp">View Order</a></button></th>
            <th><button class="headerBtn"><a href="GuestOrderHistoryController?action=<%=saved%>">View Saved orders</a></button></th>
            <th><button class="headerBtn"><a href="GuestOrderHistoryController?action=<%=payed%>">View Order History</a></button></th>
        <%
            }
        %>
                        
        </div>
        </div>
    <h1>Shipment Details</h1>
    
        </table>
         <form method="get" action="AddShipmentController">
            <table align="center">
                <th>
                    <tr>
                        <th>Search for S
                            hipment</th>
                        <th><label>shipment ID: </label></th>
                        <th><input type="text" name="shipmentID"></th>
                    </tr>
                </th>

            </table>
        </form>
    </form>
    
    <% if (shipment != null) { %>
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
            

        </table>
            <div align = "center">
             <button><a href = "editShipment.jsp"> Edit Shipment </a></button>
            
                <form align="center" action="DeleteShipmentController" method="post">
                <button align="center" type="submit">Delete Shipment</button>
                </form>
            </div>
    <% } else { %>
        <p>No shipment details found.</p>
        <div align = "center">
            <button align="center"><a href = "addShipment.jsp"> add </a></button>
        </div>
    <% } %>
    <div align = "center">
        <button><a href="index">Return to home page</a></button>
    </div>
</body>
</html>
<link rel="stylesheet" href="./css/index.css"/>