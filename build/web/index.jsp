<%@page import="java.util.ArrayList"%>
<%@page import="models.Product"%>
<%@ page import="controllers.Validator" %>
<%@ page import="models.dao.DBManager" %>
<%@ page import="models.User" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.util.Objects" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IoTBay Home Page</title>
    </head>

    <body>
        <div class="header">
        <a href="index.jsp" class="left">IOT Store</a>
        <div class="right">
            <%
            User user = (User) session.getAttribute("user");
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
        <%
            } else {
                //Guest User ${pageContext.request.contextPath}/
        %>

            <th><button class="headerBtn"><a href="login.jsp">Login</a></button></th>
            <th><button class="headerBtn"><a href="register.jsp">Register</a></button></th>
            <th><button class="headerBtn"><a href="cart.jsp">View Order</a></button></th>
            <th><button class="headerBtn"><a href="GuestOrderHistoryController?action=<%=saved%>">View Saved orders</a></button></th>
            <th><button class="headerBtn"><a href="GuestOrderHistoryController?action=<%=payed%>">View Order History</a></button></th>
        <%
            }
        %>
                        
        
        </div>
        </div>
        
        <div class="centerBttn" align="center">
            <button><a href="staffProducts.jsp">View All Products</a></button>
        </div>
                        

        <jsp:include page="/ConnServlet" flush="true"/>
        <%--<jsp:include page = "products.jsp" flush ="true"/>--%>
    </body>
</html>
<link rel="stylesheet" href="./css/index.css"/>
