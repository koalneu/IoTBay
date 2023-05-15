<%-- 
    Document   : products
    Created on : 09/05/2023, 5:44:37 PM
    Author     : mjra9
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.LinkedList"%>
<%@page import="models.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <%
            ArrayList<Product> products = new ArrayList<Product>();
            products = (ArrayList<Product>) session.getAttribute("products");
        %>
<!--        <form action="/OrderController" method="post">-->
            <table align = "center" cellspacing = "10">
                <th>Product ID</th>
                <th> Name </th>
                <th> Price </th>
                <%if (products == null) {%>
                    <tr> 
                        <td> list </td>
                        <td>is </td>
                        <td>null </td>
                    </tr>
                <% }
                else{ 
                    for (Product product : products){
                        String name = product.getProductName();
                        int ID = product.getProductID();
                        double price = product.getProductPrice(); %>

                        <tr>
                            <td><%=ID%></td>
                            <td><%=name%></td>
                            <td>$<%=price%></td>
                            <td><button><a href = "OrderController?productName=<%=name%>" > Add to order</a></button></td>
                        </tr>
                <%  }
                 } %>
            </table>
            <div align = "center">
                <button><a href = "OrderController"> View Order </a></button>
            </div>
            <!--<input type = "button" value = "View Order">-->
        <!--</form>-->
        <%--<jsp:include page="ProductServlet" flush="true"/>--%>
    </body>
</html>
