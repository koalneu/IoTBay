<%-- 
    Document   : main
    Created on : 7 Apr 2023, 12:57:52 am
    Author     : @author (Tyler) Shi En Lim 13675919
    Author     : @author Wilson 14269118
--%>
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
        <title>Main Page</title>
    </head>
    <body>
        <%
            //Set the current user object by retrieving data from the session
            User user = (User)session.getAttribute("user");
        %>
        <h1>MAIN PAGE</h1>
        <% if (user != null) {%>
        
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

                    
                    //Update the database
                    //Set variables for the connection
                    String dbuser = "iotadmin";
                    String dbpass = "password";
                    String driver = "org.apache.derby.jdbc.ClientDriver";

                    //establish connection:
                    Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/IoTDatabase", dbuser, dbpass);
                    //update is used to seelct
                    //Statement to update the correct columns
                    PreparedStatement update = conn.prepareStatement("UPDATE CUSTOMER SET CUSTOMERFIRSTNAME=?, CUSTOMERLASTNAME=?, CUSTOMEREMAIL=?, CUSTOMERPASSWORD=?, CUSTOMERSTREET=?, CUSTOMERPOSTCODE=?, CUSTOMERCITY=?, CUSTOMERSTATE=?, CUSTOMERCOUNTRY=? WHERE CUSTOMEREMAIL=?");
                    //Set the variables for the "update" statement
                    update.setString(1, fname);
                    update.setString(2, lname);
                    update.setString(3, email);
                    update.setString(4, password);
                    update.setString(5, street);
                    update.setString(7, city);
                    update.setInt(6, Integer.parseInt(postCode));
                    update.setString(8, state);
                    update.setString(9, country);
                    update.setString(10, originalEmail);

                    update.executeUpdate();
                    conn.close();
               }
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
            <%} else { %>
            <h2>Guest Profile</h2>
            <table id="profile-table">
                <thead>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Password</th>
                    <th>Street</th>
                    <th>City</th>
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
                    <td>NA</td>
                </tr>
            </table>
            <%}%>
        <button><a href="logout.jsp">Log Out</a></button>
        <button><a href="edit.jsp">Edit Details</a></button>
    </body>
</html>
<link rel="stylesheet" href="./css/main.css">
