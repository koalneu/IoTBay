<%-- 
    Document   : profile
    Created on : 7 Apr 2023, 12:57:52 am
    Author     : @author (Tyler) Shi En Lim 13675919
    Author     : @author Wilson 14269118
--%>

<%@page import="java.util.Objects"%>
<%@page import="models.dao.DBManager"%>
<%@page import="controllers.Validator"%>
<%@page import="models.AccessLog"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="models.User" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile Page</title>
    </head>
    <body>
        
        <%
            //Set the current user object by retrieving data from the session
            User user = (User)session.getAttribute("user");
            //operations for order history
            String saved = "saved";
            String payed = "payed";
        %>
        <div class="header">
        <a href="index.jsp" class="left">IOT Store</a>
        <div class="right">
            <%
            boolean isLoggedIn = (user != null && !Objects.equals(user.getUserEmail(), ""));
            DBManager manager = (DBManager) session.getAttribute("manager");
             
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
        <div class="center">
        <h1>Profile Page</h1>
        
        <h2 align="center"><%= user.getUserFirstName() + " " + user.getUserLastName() + "'s Details:" %></h2> 
        <table id="profile-table" align="center">
           <thead>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Password</th>
                <th>Street</th>
                <th>Postcode</th>
                <th>City</th>
                <th>State</th>
                <th>Country</th>
            </thead>
            <tr>
                <td>${user.userFirstName}</td>
                <td>${user.userLastName}</td>
                <td>${user.userEmail}</td>
                <td>${user.userPassword}</td>
                <td>${user.userStreet}</td>
                <td>${user.userPostCode}</td>
                <td>${user.userCity}</td>
                <td>${user.userState}</td>
                <td>${user.userCountry}</td>
            </tr>
        </table>
        <button class="center"><a href="edit.jsp">Edit Details</a></button>    
        <button class="center"><a href="index.jsp">Back</a></button>
        
    </div>
    </body>
</html>
<link rel="stylesheet" href="./css/index.css"/>
