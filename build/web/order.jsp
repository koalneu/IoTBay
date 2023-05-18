<%-- 
    Document   : order
    Created on : 08/05/2023, 6:42:16 PM
    Author     : mjra9
--%>

<%@page import="models.dao.DBManager"%>
<%@page import="java.util.Objects"%>
<%@page import="java.util.Map"%>
<%@page import="models.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Order</title>
    </head>
    <body>
        <%
            Order order = (Order) session.getAttribute("order");
            User user = (User) session.getAttribute("user");
            double totalPrice = 0;
            
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
            <div align = "center">
                <h1>Current Order:</h1>

                <h2 align> User:<%= user.getUserFirstName() + user.getUserLastName()%></h2>
                <%if(order != null) {%>
                <form method = "post" action = "/OrderController">
                    <table cellspacing = "10">
                        <tr>
                            <th> Product: </th>
                            <th> Quantity: </th>
                            <th> Price: </th>
                        </tr>
                            <% for(Map.Entry<Product, Integer> product : order.getProducts().entrySet()){ 
                            String name = product.getKey().getProductName(); 
                            int quantity = product.getValue();
                            double price =product.getKey().getProductPrice();
                            totalPrice += price * quantity;
                            %>
                                <tr>
                                    <td><%=name%></td>
                                    <td><%=quantity%></td>
                                    <td>$<%=price * quantity%></td>
                                </tr>
                            <% }%>
                    </table>
                            <p> Total = $<%= totalPrice%> </p>
                    <input type = "submit" value = "Proceed to checkout"/>
                </form>
            </div>
            
        <%}else { %>
            <p> Order is currently empty please add products to your order using the previous page</p>
        <%}%>
       
    </body>
</html>
<link rel="stylesheet" href="./css/index.css"/>