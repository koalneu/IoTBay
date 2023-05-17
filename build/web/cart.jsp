<%-- 
    Document   : order
    Created on : 08/05/2023, 6:42:16 PM
    Author     : mjra9
--%>

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
            
        %>
            <div align = "center">
                <h1>Current Order:</h1>

                <h2 align> User: <%= user.getUserFirstName() + " " +  user.getUserLastName()%></h2>
                <%if (order == null){%>
                    <p> Order is currently empty please add products to your order using the previous page</p>  
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
                                    <td>$<%=price%></td>
                                </tr>
                            <% }%>
                    </table>
                            <p> Total = $<%= totalPrice%> </p>
                            
                <form method = "post" action = "/OrderLineController">
                    <input type = "submit" value = "Proceed to checkout"/>
                    <!--<button><a href = "OrderLineController" > Proceed to checkout </button>-->
                </form>
            </div>
            
        <%}else { %>
        <p>
            Order is currently empty <a href = "OrderLineController"> Click here </a> to go back and browse products. 
        </p>
        <%}%>
       
    </body>
</html>
