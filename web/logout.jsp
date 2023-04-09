<%-- 
    Document   : logout
    Created on : 7 Apr 2023, 7:18:32 pm
    Author     : @author (Tyler) Shi En Lim 13675919
    Author     : @author Wilsan 14269118
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="models.User" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logout Page</title>
    </head>
    <body>
        <h1>You have logged out</h1>
        <h2><a href="index.html">Go back to home page</a></h2>
        <% 
            User user = (User)session.getAttribute("user");
            User.addUser(user);
        %>
        <% session.invalidate(); %>
    </body>
</html>
