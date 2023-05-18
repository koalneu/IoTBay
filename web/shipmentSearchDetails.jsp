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
            <button class="headerBtn"><a href="staffProducts.jsp">Products</a></button>
            <button class="headerBtn"><a href="payment.jsp">Payment</a></button>

        <%
            } else {
                //Guest User ${pageContext.request.contextPath}/
        %>

            <th><button class="headerBtn"><a href="login.jsp">Login</a></button></th>
            <th><button class="headerBtn"><a href="register.jsp">Register</a></button></th>
            <th><button class="headerBtn"><a href="cart.jsp">View Order</a></button></th>
            <th><button class="headerBtn"><a href="GuestOrderHistoryController?action=<%=saved%>">View Saved orders</a></button></th>
            <th><button class="headerBtn"><a href="GuestOrderHistoryController?action=<%=payed%>">View Order History</a></button></th>
            <button class="headerBtn"><a href="staffProducts.jsp">Products</a></button>
        <%
            }
        %>
                        
        </div>
        </div>
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
<link rel="stylesheet" href="./css/index.css"/>