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
            session.setAttribute("imageSRC", "img/test image.jpg");

            if (isLoggedIn) {
                //Logged In
        %>
            <button class="headerBtn"><a href="logout.jsp">Logout</a></button>
            <button class="headerBtn"><a href="profile.jsp">Profile</a></button>
            <button class="headerBtn"><a href="staffProducts.jsp">Staff Products</a></button>
        
        <%
            } else {
                //Guest User ${pageContext.request.contextPath}/
        %>

                        <th><button class="headerBtn"><a href="login.jsp">Login</a></button></th>
                        <th><button class="headerBtn"><a href="register.jsp">Register</a></button></th>
        <%
            }
        %>
                        </div>
        </div>
                        
        <img class="imgTest" alt="Image cannot be displayed" src=imageLocation width="200" height="100"/>

        <jsp:include page="/ConnServlet" flush="true"/>
    </body>
</html>
<link rel="stylesheet" href="./css/index.css"/>
