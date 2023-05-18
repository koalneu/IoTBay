<%-- 
    Document   : payment
    Created on : 14/05/2023, 10:11:34 PM
    Author     : tylershienlim
--%>

<%@page import="models.dao.DBManager"%>
<%@page import="java.util.Objects"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="models.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Payment Details</title>
    </head>
    <body>
        <%
            User user = (User) session.getAttribute("user");
            PaymentMethod paymethod = (PaymentMethod) session.getAttribute("paymethod");

            if (paymethod != null) {
                //has payment details created
                //view payment
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
        <h1>View Payment Details</h1>
        <form method="post" >
            <table class="payment-table" align="center" >
                <tr>
                    <th><label>Card Number:</label></th>
                    <th>${paymethod.payMethodCardNo}</th>
                </tr>
                <tr>
                    <th><label>Credit Card Name:</label></th>
                    <th>${paymethod.payMethodCardHolder}</th>
                </tr>
                <tr>
                    <th><label>Card Expiration Date:</label></th>
                    <th>${paymethod.payMethodCardExpiry}</th>
                </tr>
                <tr>
                    <th><label>CVV code:</label></th>
                    <th>${paymethod.payMethodCardSecurity}</th>
                </tr>
                <tr>
                    <th><button><a href="index.jsp">Cancel</a></button></th>
                    <th><button><a href="deletepayment.jsp">Delete details</a></button></th>
                    <th><button><a href="editpaymentprofile.jsp">Edit details</a></button></th>
                </tr>
            </table>
        </form>
        <%
            } else {
                //No payment details so create a new payment

                //retrieve errors
                String nameErr = (String) session.getAttribute("cardName");
                String cvvErr = (String) session.getAttribute("cvvNo");
                String cardNoErr = (String) session.getAttribute("cardNo");
                String dateErr = (String) session.getAttribute("cardDate");
        %>
        <h1>Add New Payment Details</h1>
        <form method="post" action="PaymentController" >
            <table align="center" >
                <tr>
                    <th><label>Card Number:</label></th>
                    <th>
                        <input 
                            type="text" name="cardno" 
                            placeholder="Enter credit card number..." 
                        />
                    </th>
                </tr>
                <tr>
                    <th><label>Credit Card Name:</label></th>
                    <th><input 
                            type="text" name="cardname" 
                            placeholder="Enter card holder name.." 

                        /></th>
                </tr>
                <tr>
                    <th><label>Card Expiration Date:</label></th>
                    <th><input 
                            type="date" name="cardexpiry" 
                            
                        /></th>
                </tr>
                <tr>
                    <th><label>CVV code:</label></th>
                    <th><input 
                            type="text" name="cardcvv" 
                            placeholder="Enter card 3 digits.." 
                            
                        /></th>
                </tr>
                <tr>
                    <th><button><a href="index.jsp">Cancel</a></button></th>
                    <th><input type="submit" value="Save payment details" /></th>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center;">
                        <label style="color: red;"><%=(nameErr != null ? nameErr : "")%></label>
                        <label style="color: red;"><%=(cvvErr != null ? cvvErr : "")%></label>
                        <label style="color: red;"><%=(cardNoErr != null ? cardNoErr : "")%></label>
                        <label style="color: red;"><%=(dateErr != null ? dateErr : "")%></label>
                    </td>
                </tr>
            </table>
        </form>
        <%
            }
        %>
    </body>
</html>
<link rel="stylesheet" href="./css/index.css"/>