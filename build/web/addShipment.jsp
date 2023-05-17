<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="models.*"%>
<%@page import="models.dao.*"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.Map"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Shipment Form</title>
</head>
<body>
    <h1>Shipment Form</h1>
     <%-- Checking if the user is logged in and has address details --%>
    <% if (session.getAttribute("user") != null && session.getAttribute("shipment") != null) {
        // Retrieve user and shipment objects from the session
        User user = (User) session.getAttribute("user");
        Shipment shipment = (Shipment) session.getAttribute("shipment");
        Order order = (Order) session.getAttribute("order");
    %>

   
      <%-- Shipment form with autofilled fields --%>
    <form action="AddShipmentController" method="post">
        <table align="center" >
        <label for="street">Street:</label>
        <input type="text" name="street" value="<%= user.getUserStreet() %>" required><br>

        <label for="city">City:</label>
        <input type="text" name="city" value="<%= user.getUserCity()%>" required><br>

        <label for="postcode">Postcode:</label>
        <input type="text" name="postcode" value="<%= user.getUserPostCode() %>" required><br>

        <label for="country">Country:</label>
        <input type="text" name="country" value="<%= user.getUserCountry()%>" required><br>
        <tr>
                <th><label>State:</label>
                <th>
                    <select name="state" required>
                        <option value="">Select a state...</option>
                        <option value="ACT">ACT</option>
                        <option value="NSW">NSW</option>
                        <option value="NT">NT</option>
                        <option value="QLD">QLD</option>
                        <option value="SA">SA</option>
                        <option value="VIC">VIC</option>
                        <option value="TAS">TAS</option>
                        <option value="WA">WA</option>
                    </select>
                </th>
            </tr>
             <tr>
               <th><label>Method</label>
                <th>
               <select name="method" required>
                        <option value="">Select a method...</option>
                        <option value="postOffice">Post Office</option>
                        <option value="fedex">FedEx</option>
                        </select>
                    </th>
           </tr>
           <tr>
         <th><button type="button" onClick="history.back()" >Cancel</button></th>
                <th><input type="submit" value="Add Shipment"></th>
        </table>
    </form>

    <% } else {
        // User is not logged in or does not have a shipment object, display blank form
    %>
    <form method="post" action="AddShipmentController" >
         <table align="center" >
           
           <tr>
                <th><label>Street:</label></th>
                <th><input type="text" name="street" required/></th>
            </tr>
            <tr>
                <th><label>City:</label></th>
                <th><input type="text" name="city" required/></th>
            </tr>
            <tr>
                <th><label>PostCode:</label></th>
                <th><input type="text" name="postcode" required/></th>
            </tr>
            <tr>
                <th><label>State:</label>
                <th>
                    <select name="state" required>
                        <option value="">Select a state...</option>
                        <option value="ACT">ACT</option>
                        <option value="NSW">NSW</option>
                        <option value="NT">NT</option>
                        <option value="QLD">QLD</option>
                        <option value="SA">SA</option>
                        <option value="VIC">VIC</option>
                        <option value="TAS">TAS</option>
                        <option value="WA">WA</option>
                    </select>
                </th>
            </tr>
            <tr>
                <th><label>Country:</label></th>
                <th><input type="text" name="country" placeholder="Enter your country.." required/></th>
           </tr>
           <tr>
               <th><label>Method</label>
                <th>
               <select name="method" required>
                        <option value="">Select a method...</option>
                        <option value="postOffice">Post Office</option>
                        <option value="fedex">FedEx</option>
                        </select>
                    </th>
           </tr>
           <tr>
                <th><button type="button" onClick="history.back()" >Cancel</button></th>
                <th><input type="submit" value="Add Shipment"></th>
            </tr>
        </table>
       
    </form>

    
</body>
</html>
