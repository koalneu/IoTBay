<%-- 
    Document   : newProduct
    Created on : 15/05/2023, 9:22:23 PM
    Author     : legob
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Product Page</title>
    </head>
    <body>
        <%
            String errorMessage = (String) request.getSession().getAttribute("errorMessage");
            //retrieve product errors
            String nameErr = (String) session.getAttribute("productNameErr");
            String descErr = (String) session.getAttribute("productDescErr");
            String priceErr = (String) session.getAttribute("productPriceErr");
            String imageErr = (String) session.getAttribute("productImageErr");
            String stockErr = (String) session.getAttribute("productStockErr");
        %>
        <h1>Add New Product</h1>
        <form method="post" action="AddProductController">
            <table align="center">
                <tr>
                    <th><label>Product Name:</label></th>
                    <th><input type="text" name="productName" value="" required/></th>
                </tr>
                <tr>
                    <th><label>Product Description:</label></th>
                    <th><input type="text" name="productDesc" value="" required/></th>
                </tr>
                <tr>
                    <th><label>Product Price: </label></th>
                    <th><input type="text" name="productPrice" value="" required/></th>
                </tr>
                <tr>
                    <th><label>Product Image Location:</label></th>
                    <th><input type="text" name="productImage" value="" required/></th>
                </tr>
                <tr>
                    <th><label>Product Stock: </label></th>
                    <th><input type="text" name="productStock" value="" required/></th>
                </tr>
                <tr>
                    <th><button><a href="staffProducts.jsp">Cancel</a></button></th>
                    <th><input type="submit" value="Add Product" /></th>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center;">
                        <label style="color: red;"><%=(nameErr != null ? nameErr : "")%></label>
                        <label style="color: red;"><%=(descErr != null ? descErr : "")%></label>
                        <label style="color: red;"><%=(priceErr != null ? priceErr : "")%></label>
                        <label style="color: red;"><%=(imageErr != null ? imageErr : "")%></label>
                        <label style="color: red;"><%=(stockErr != null ? stockErr : "")%></label>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
