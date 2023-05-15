<%-- 
    Document   : logout
    Created on : 7 Apr 2023, 7:18:32 pm
    Author     : @author (Tyler) Shi En Lim 13675919
    Author     : @author Wilson 14269118
--%>

<%@page import="models.dao.DBManager"%>
<%@page import="models.AccessLog"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="models.User" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logout Page</title>
    </head>
    <form method="post" action="logoutController">
        <body>
                <h1 align="center">You have successfully logged out</h1>
                <h3 align="center"><button  type="submit" >Return to home page</button></h3>
        </body>
    </form>
</html>
