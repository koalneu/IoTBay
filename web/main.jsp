<%-- 
    Document   : main
    Created on : 7 Apr 2023, 12:57:52 am
    Author     : @author (Tyler) Shi En Lim 13675919
    Author     : @author Wilsan 14269118
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="models.User" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Main Page</title>
    </head>
    <body>
        <%
            User user = (User)session.getAttribute("user");
        %>
        <h1>MAIN PAGE</h1>
        <% if (user != null) {%>
            <%
            String fname = user.getUserFirstName();
            String lname = user.getUserLastName();
            %>
            <h2><%= fname + " " + lname + "'s profile" %></h2> 
            <table id="profile-table">
                <thead>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Password</th>
                    <th>Street</th>
                    <th>Postcode</th>
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
                    <td>${user.userState}</td>
                    <td>${user.userCountry}</td>
                </tr>
            </table>
            <%} else { %>
            <h2>Guest Profile</h2>
            <table id="profile-table">
                <thead>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Password</th>
                    <th>Street</th>
                    <th>Postcode</th>
                    <th>State</th>
                    <th>Country</th>
                </thead>
                <tr>
                    <td>Guest</td>
                    <td>Guest</td>
                    <td>NA</td>
                    <td>NA</td>
                    <td>NA</td>
                    <td>NA</td>
                    <td>NA</td>
                    <td>NA</td>
                </tr>
            </table>
            <%}%>
        <button><a href="logout.jsp">Log Out</a></button>
    </body>
</html>
<link rel="stylesheet" href="./css/main.css">