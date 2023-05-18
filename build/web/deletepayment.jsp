<%-- 
    Document   : deletepayment
    Created on : 17/05/2023, 12:35:30 AM
    Author     : @tylershienlim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete Payment Details</title>
    </head>
    <body>
        <h1>Delete Payment Details</h1>
        <h2>Do you really want to delete payment details?</h2>
        <form method="post" action="DeletePaymentController">
            <button onClick="history.back()"> Cancel</button>
            <input type="submit" value="Delete" />
        </form>
    </body>
</html>
