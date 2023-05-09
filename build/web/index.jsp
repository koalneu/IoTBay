<%@page import="database.DBManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="models.User"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IoTBay Home Page</title>
    </head>
    <body>
        <h1>IoT Bay Website</h1>
    
    <%
        //Retrieve data from Register page
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String street = request.getParameter("street");
        String city = request.getParameter("city");
        String postcode = request.getParameter("postcode");
        String state = request.getParameter("state");
        String country = request.getParameter("country");
           
        if (fname==null ) {
            //User is coming from the Login Page
            User user = new User(null, null, email, password, null, null, null,null, null, null);
            user = DBManager.authenticateUser(email, password);
        
            //Incorrect Login Credentials
            if (user == null){
                if (request.getMethod().equals("POST")){
                    response.sendRedirect("login.jsp");
                }
            } 
            else {
                //Credentials Passed authentication
                //Save data to variables for a new user object with the retrieved information
                fname = user.getUserFirstName();
                lname = user.getUserLastName();
                street = user.getUserStreet();
                postcode = user.getUserPostCode();
                city = user.getUserCity();
                state = user.getUserState();
                country = user.getUserCountry();
            }
        } 
        else{
            //Register page
            DBManager.addUser(fname, lname, email, password, street, postcode, city, state, country, "Customer");
            DBManager.addAccessLogEntry(email, fname, "User added"); 
        }
        //Check if the user is already logged in
        User user = (User)session.getAttribute("user");
        if(fname == null){
            //create guest user
            user = new User(null, null, null, null, null, null, null, null, null, null);
            session.setAttribute("user", user);
    %>
                    <!-- Show login and register buttons -->
                    <table align="center">
                        <tr>
                            <th><button><a href="login.jsp">Login</a></button></th>
                            <th><button><a href="register.jsp">Register</a></button></th>
                        </tr>
                    </table>
    <%
        }
        else {
            //Set the user object with the variables from the DB
            user = new User(fname, lname, email, password, street, city,postcode, state, country, null);
            session.setAttribute("user", user);

            //Staff members get sent to the staff page
            String userType = DBManager.userType(user.getUserEmail());
            System.out.println("USER TYPE:" + userType);
            if(userType.equals("Staff") || userType.equals("Admin")){
                response.sendRedirect("staff.jsp");         
            }
    %>
                    <!-- Show edit, logout and profile buttons -->
                    <table align="center">
                        <tr>
                          <td align="center"><button><a href="logout.jsp">Log Out</a></button></td>
                          <td align="center"><button><a href="profile.jsp">My Profile</a></button></td>
                        </tr>
                    </table>
    <%
            DBManager.addAccessLogEntry(email, fname, "Log in");     
        }
    %>
        
    </body>
</html>
<link rel="stylesheet" href="./css/index.css"/>