<%-- 
    Document   : login
    Created on : 6 Apr 2023, 11:21:17 pm
    Author     : @author (Tyler) Shi En Lim 13675919
    Author     : @author Wilson 14269118
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="models.User" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IotBay Login Page</title>
    </head>
    
    <body>
        <%  //Retrieve errors
        String loginErr = (String) session.getAttribute("loginErr");
        %>
        <h1>Login Page</h1>        
    </body>
    
    <form method="post" action="loginController">          
        <table align="center">
            <th>
                <tr>
                    <th><label>Email: </label></th>
                    <th><input type="text" name="email" required=""/></th>
                </tr>
                <tr>
                    <th><label>Password: </label></th>
                    <th><input type="Password" name="password" required/></th>
                </tr>
                <tr>
                    <th><button><a href="index.jsp">Cancel</a></button></th>
                    <th><button type="submit" >Login</button></th>
                </tr>

                <tr>
                    <td colspan="2" style="text-align: center;">
                        <label style="color: red;"><%=(loginErr != null ? loginErr : "")%></label>
                    </td>
                </tr>
            </th>
        </table>
    </form>
</html>
<link rel="stylesheet" href="./css/login.css"/>