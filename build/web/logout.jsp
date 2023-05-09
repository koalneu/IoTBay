<%-- 
    Document   : logout
    Created on : 7 Apr 2023, 7:18:32 pm
    Author     : @author (Tyler) Shi En Lim 13675919
    Author     : @author Wilson 14269118
--%>

<%@page import="database.DBManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="models.User" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logout Page</title>
    </head>
    <body>
        <h1 align="center">You have successfully logged out</h1>
        <h2 align="center"><a href="index.jsp">Go back to home page</a></h2>
        <% 
            
            //Set the current user object by retrieving data from the session
            User user = (User)session.getAttribute("user");
            
            DBManager.addAccessLogEntry(user.getUserEmail(), user.getUserFirstName(), "Log out");
            session.invalidate(); 
        %>
    </body>
</html>
