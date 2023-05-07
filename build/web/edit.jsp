<%-- 
    Document   : edit
    Created on : 03/05/2023, 1:34:28 PM
    Author     : Wilson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="models.User" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Details </title>
    </head>
    <body>
        <%
            //Set the object from retrieving data from session
            User user = (User)session.getAttribute("user");
        
            String fname = user.getUserFirstName();
            String lname = user.getUserLastName();
            String state = user.getUserState();
            //Retrieve information from the current set user
            
            /*NOTES FOR THE JSP CODE BELOW:
             *  Register page is copied and adjusted to have the customer's details inputted into text fields already
             *  New button "Save Changes" POST's information the customer's has changed to profile.jsp & updates the DB accordingly
            */
        %>
        <h1><%= fname + " " + lname + "'s Details" %></h1>
        <form method="post" action="profile.jsp" >
            <table align="center" >
                <tr>
                    <th><label>First Name:</label></th>
                    <th><input type="text" name="fname" value="${user.userFirstName}" required/></th>
                </tr>
                <tr>
                    <th><label>Last Name:</label></th>
                    <th><input type="text" name="lname" value="${user.userLastName}" required/></th>
                </tr>
                <tr>
                    <th><label>Email:</label></th>
                    <th><input type="email" name="email" value="${user.userEmail}" required/></th>
                </tr>
                <tr>
                    <th><label>Password:</label></th>
                    <th><input type="password" name="password" value="${user.userPassword}" required/></th>
                </tr>
                <tr>
                    <th><label>Street:</label></th>
                    <th><input type="text" name="street" value="${user.userStreet}" required/></th>
                </tr>
                <tr>
                    <th><label>City:</label></th>
                    <th><input type="text" name="city" value="${user.userCity}" required/></th>
                </tr>
                <tr>
                    <th><label>PostCode:</label></th>
                    <th><input type="text" name="postcode" value="${user.userPostCode}" required/></th>
                </tr>
                <tr>
                    <th><label>State:</label>
                    <th>
                        <select name="state" required>
                            <option value="">Select a state...</option>
                            <option value="ACT" <% if (state.equals("ACT")) { %>selected<% } %>>ACT</option>
                            <option value="NSW" <% if (state.equals("NSW")) { %>selected<% } %>>NSW</option>
                            <option value="NT" <% if (state.equals("NT")) { %>selected<% } %>>NT</option>
                            <option value="QLD" <% if (state.equals("QLD")) { %>selected<% } %>>QLD</option>
                            <option value="SA" <% if (state.equals("SA")) { %>selected<% } %>>SA</option>
                            <option value="VIC" <% if (state.equals("VIC")) { %>selected<% } %>>VIC</option>
                            <option value="TAS" <% if (state.equals("TAS")) { %>selected<% } %>>TAS</option>
                            <option value="WA" <% if (state.equals("WA")) { %>selected<% } %>>WA</option>
                        </select>
                    </th>
                </tr>
                <tr>
                    <th><label>Country:</label></th>
                    <th><input type="text" name="country" placeholder="Enter your country.." value="${user.userCountry}" required/></th>
                </tr>
                <tr>
                    <th><label>Payment Method:</label></th>
                    <th><input type="radio" name="payment-method" value="Credit Card"/><label>Credit Card</label></th>
                </tr>
                <tr>
                    <th><button type="button" onClick="history.back()" >Cancel</button></th>
                    <th><input type="submit" value="Save Changes" /></th>
                </tr>
            </table>
        </form>
    </body>
</html>
<link rel="stylesheet" href="./css/edit.css"/>
