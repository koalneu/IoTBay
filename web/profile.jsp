<%-- 
    Document   : profile
    Created on : 7 Apr 2023, 12:57:52 am
    Author     : @author (Tyler) Shi En Lim 13675919
    Author     : @author Wilson 14269118
--%>

<%@page import="DAO.DBManager"%>
<%@page import="controllers.Validator"%>
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
        %>
        <h1>Profile Page</h1>
        
        <h2><%= user.getUserFirstName() + " " + user.getUserLastName() + "'s Details:" %></h2> 
        <table id="profile-table">
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
        <button><a href="index.jsp">Back</a></button>
        <button><a href="edit.jsp">Edit Details</a></button>
    </body>
</html>
<link rel="stylesheet" href="./css/profile.css">