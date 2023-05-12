<%-- 
    Document   : profile
    Created on : 7 Apr 2023, 12:57:52 am
    Author     : @author (Tyler) Shi En Lim 13675919
    Author     : @author Wilson 14269118
--%>
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
        %>
        <h1>Profile PAGE</h1>
        
            <%
                //Save data from user object to be used later
                String fname = user.getUserFirstName();
                String lname = user.getUserLastName();
                String originalEmail = user.getUserEmail();
                
                //check if we have come from the edit.jsp page
                if (request.getMethod().equals("POST")){
                    //Data from edit.jsp page is saved to variables
                    fname = request.getParameter("fname");
                    lname = request.getParameter("lname");
                    String email = request.getParameter("email");
                    String password = request.getParameter("password");
                    String street = request.getParameter("street");
                    String postCode = request.getParameter("postcode");
                    String city = request.getParameter("city");
                    String state = request.getParameter("state");
                    String country = request.getParameter("country");

                    //Set user object variables to the updated versions
                    user.setUserFirstName(fname);
                    user.setUserLastName(lname);
                    user.setUserEmail(email);
                    user.setUserPassword(password);
                    user.setUserStreet(street);
                    user.setUserCity(city);
                    user.setUserPostCode(postCode);
                    user.setUserState(state);
                    user.setUserCountry(country);
                    
                    user.updateUser(originalEmail,fname,lname,email,password,street,postCode,city,state,country);
                    
                    AccessLog accesslog = new AccessLog();
                    accesslog.addAccessLogEntry(email, fname, "Update Details"); 
               }
            %>

        <h2><%= fname + " " + lname + "'s Details:" %></h2> 
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

        <button type="button" onClick="history.back()" >Back</button>    
        <button><a href="edit.jsp">Edit Details</a></button>
        <!--<button><a href = "ProductServlet"> View Products </button>-->
        <div align = "center">
            <h2> Available Products: </h2>
            <jsp:include page="products.jsp" flush = "true"/> 
        </div>
    </body>
</html>
<link rel="stylesheet" href="./css/profile.css">