<%-- 
    Document   : order
    Created on : 08/05/2023, 6:42:16 PM
    Author     : mjra9
--%>

<%@page import="java.text.DecimalFormat"%>
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
            String add = "+";
            String subtract = "-";
            DecimalFormat format = new DecimalFormat("$#.00");
            if(user == null){
                user = new User();
                session.setAttribute("user", user);
            }
            String stockErr = (String) session.getAttribute("stockErr");
            
        %>
            <div align = "center">
                <h1>Current Order:</h1>

                <h2 align> User: <%= user.getUserType().equals("guest")? "Guest" : user.getUserFirstName() + " " +  user.getUserLastName()%></h2>
                <%if (order == null){%>
                    Order is currently empty <a href = "OrderLineController"> Click here </a> to go back and browse products. 
                <%}else if(!(order.getOrderLine().isEmpty())) {%>
                
                    <table cellspacing = "10">
                        <tr>
                            <th> Product: </th>
                            <th> Quantity: </th>
                            <th> Price: </th>
                        </tr>
                            <% 
                           
                            for(OrderLine product : order.getOrderLine()){ 
                                String name = product.getProduct().getProductName(); 
                                int quantity = product.getQuantity();
                                double price = product.getPrice();
                                totalPrice += price;
                                
                            %>
                                <tr>
                                    <td><%=name%></td>
                                    <td>
                                        <button><a href = "OrderLineController?productName=<%=name%>&operation=<%=subtract%>"> - </a></button> 
                                            <%=quantity%>  
                                         <button><a href = "OrderLineController?productName=<%=name%>&operation=<%=add%>"> + </a></button>
                                    </td>
                                    <td><%=format.format(price)%></td>
                                </tr>
                            <% }%>
                    </table>
                            <p> Total = <%= format.format(totalPrice)%> </p>
                    <%if (order.isValid()){ %>
                
                    <form method = "post" action = "/OrderLineController">
                    <input type = "submit" value = "Proceed to checkout"/><br>
                    <!--<button><a href = "OrderLineController" > Proceed to checkout </button>-->
                    </form>
                <%} else {%>
                    <label style="color: red;"> Not enough stock for order<label><br>
                 <%}%>
                 <br>
                 <button><a href = "staffProducts.jsp"> Browse Products </a></button>
            </div>
            
        <%}else { %>
        <p>
            Cart is currently empty <a href = "OrderLineController"> Click here </a> to go back and browse products. 
        </p>
        <%}%>
       
    </body>
</html>
