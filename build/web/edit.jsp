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
             String errorMessage = (String) request.getSession().getAttribute("errorMessage"); 
            //Set the object from retrieving data from session
            User user = (User)session.getAttribute("user");
        
            String fname = user.getUserFirstName();
            String lname = user.getUserLastName();
            String state = user.getUserState();
            //Retrieve information from the current set user
            
            //Retrieve errors
            String fnameErr = (String) session.getAttribute("fnameErr");
            String lnameErr = (String) session.getAttribute("lnameErr");
            String emailErr = (String) session.getAttribute("emailErr");
            String passwordErr = (String) session.getAttribute("passwordErr");
            String streetErr = (String) session.getAttribute("streetErr");
            String cityErr = (String) session.getAttribute("cityErr");
            String postcodeErr = (String) session.getAttribute("postcodeErr");
            String countryErr = (String) session.getAttribute("countryErr");
        %>

        <h1><%= fname + " " + lname + "'s Details" %></h1>
        <form method="post" action="editController" >
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
                    <th><button><a href="profile.jsp">Cancel</a></button></th>
                    <th><input type="submit" value="Save Changes" /></th>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center;">
                        <label style="color: red;"><%=(fnameErr != null ? fnameErr : "")%></label>
                        <label style="color: red;"><%=(lnameErr != null ? lnameErr : "")%></label>
                        <label style="color: red;"><%=(emailErr != null ? emailErr : "")%></label>
                        <label style="color: red;"><%=(passwordErr != null ? passwordErr : "")%></label>
                        <label style="color: red;"><%=(streetErr != null ? streetErr : "")%></label>
                        <label style="color: red;"><%=(cityErr != null ? cityErr : "")%></label>
                        <label style="color: red;"><%=(postcodeErr != null ? postcodeErr : "")%></label>
                        <label style="color: red;"><%=(countryErr != null ? countryErr : "")%></label>
                    </td>
                </tr>

            </table>
        </form>
    </body>
</html>
<link rel="stylesheet" href="./css/edit.css"/>
