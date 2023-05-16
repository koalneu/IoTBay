<%-- 
    Document   : payment
    Created on : 14/05/2023, 10:11:34 PM
    Author     : tylershienlim
--%>

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
            </table>
        </form>
        <%
            }
        %>
    </body>
</html>
<link rel="stylesheet" href="./css/payment.css"/>