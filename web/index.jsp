<%@ page import="controllers.Validator" %>
<%@ page import="DAO.DBManager" %>
<%@ page import="models.User" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.util.Objects" %>

<!DOCTYPE html>
<%@page import="models.Order"%>
<%@page import="controllers.userController"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="models.User"%>
<%@page import="models.AccessLog" %>
<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IoTBay Home Page</title>
    </head>
    <body>
        <h1>IoT Bay Website</h1>

        <%
            User user = (User) session.getAttribute("user");
            boolean isLoggedIn = (user != null && !Objects.equals(user.getUserEmail(), ""));

            if (isLoggedIn) {
                //Logged In
        %>
                <table align="center">
                    <tr>
                        <th><button><a href="logout.jsp">Logout</a></button></th>
                        <th><button><a href="profile.jsp">Profile</a></button></th>
                    </tr>
                </table>
        <%
            } else {
                //Guest User
        %>
                <table align="center">
                    <tr>
                        <th><button><a href="login.jsp">Login</a></button></th>
                        <th><button><a href="register.jsp">Register</a></button></th>
                    </tr>
                </table>
        <%
            }
        %>

        <jsp:include page="/ConnServlet" flush="true"/>
    </body>
</html>
<link rel="stylesheet" href="./css/index.css"/>
