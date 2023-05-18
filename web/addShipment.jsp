<%@page import="java.util.Objects"%>
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
    <title>Add Shipment</title>
</head>
<body>
    <h1>Shipment</h1>
     <%-- Checking if the user is logged in and has address details 
   <%String errorMessage = (String) request.getSession().getAttribute("errorMessage"); 

        User user = (User) session.getAttribute("user");
        Shipment shipment = (Shipment) session.getAttribute("shipment");
        Order order = (Order) session.getAttribute("order");
   if(order == null)
   {
  %>
  <p> no order has been made</p>--%>

    <% //} else {
        // User is not logged in or does not have a shipment object, display blank form

        String errorMessage = (String) request.getSession().getAttribute("errorMessage"); 

        User user = (User) session.getAttribute("user");
        Shipment shipment = (Shipment) session.getAttribute("shipment");
        Order order = (Order) session.getAttribute("order");
        
        
        String state = user.getUserState();
            //Retrieve errors
            String streetErr = (String) session.getAttribute("streetErr");
            String cityErr = (String) session.getAttribute("cityErr");
            String postcodeErr = (String) session.getAttribute("postcodeErr");
            String countryErr = (String) session.getAttribute("countryErr");
    %>
    <div class="header">
        <a href="index.jsp" class="left">IOT Store</a>
        <div class="right">
            <%
            boolean isLoggedIn = (user != null && !Objects.equals(user.getUserEmail(), ""));
            DBManager manager = (DBManager) session.getAttribute("manager");
            String saved = "saved";
            String payed = "payed";
             
            if (isLoggedIn) {
                //Logged In
        %>
            <button class="headerBtn"><a href="logout.jsp">Logout</a></button>
            <button class="headerBtn"><a href="profile.jsp">Profile</a></button>
            <button class="headerBtn"><a href="cart.jsp">View Order</a></button>
            <button class="headerBtn"><a href="OrderHistoryController?action=<%=saved%>">View Saved orders</a></button>
            <button class="headerBtn"><a href="OrderHistoryController?action=<%=payed%>"">View Order History</a></button>
            <button class="headerBtn"><a href="staffProducts.jsp">Products</a></button>
            <button class="headerBtn"><a href="payment.jsp">Payment</a></button>
            <button class="headerBtn"><a href="order.jsp">Order</a></button>

        <%
            } else {
                //Guest User ${pageContext.request.contextPath}/
        %>

            <th><button class="headerBtn"><a href="login.jsp">Login</a></button></th>
            <th><button class="headerBtn"><a href="register.jsp">Register</a></button></th>
            <th><button class="headerBtn"><a href="cart.jsp">View Order</a></button></th>
            <th><button class="headerBtn"><a href="GuestOrderHistoryController?action=<%=saved%>">View Saved orders</a></button></th>
            <th><button class="headerBtn"><a href="GuestOrderHistoryController?action=<%=payed%>">View Order History</a></button></th>
            <button class="headerBtn"><a href="staffProducts.jsp">Products</a></button>
        <%
            }
        %>
                        
        
        </div>
        </div>
    <form method="post" action="AddShipmentController" >
         <table align="center" >
           
           <tr>
                <th><label>Street:</label></th>
                <th><input type="text" name="street" placeholder="Enter your Street.." value="${user.userStreet}" required/></th>
            </tr>
            <tr>
                <th><label>City:</label></th>
                <th><input type="text" name="city" placeholder="Enter your city.." value="${user.userCity}" required/></th>
            </tr>
            <tr>
                <th><label>PostCode:</label></th>
                <th><input type="text" name="postcode" placeholder="Enter your postcode.." value="${user.userPostCode}"required/></th>
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
               <th><label>Method</label>
                <th>
               <select name="method" required>
                        <option value="">Select a method...</option>
                        <option value="Post Office">Post Office</option>
                        <option value="FedEx">FedEx</option>
                        </select>
                    </th>
           </tr>
           <tr>
                <th><button type="button" onClick="history.back()" >Cancel</button></th>
                <th><input type="submit" value="Add Shipment"></th>
            </tr>
            <tr><td colspan="2" style="text-align: center;">
                    
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
<link rel="stylesheet" href="./css/index.css"/>