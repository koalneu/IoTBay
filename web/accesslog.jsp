<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Access Logs</title>
</head>
<body>
    <h1 align="center">Access logs</h1>
    
    <form action="accesslogController" method="post">
        <!-- Include any form fields or input elements here -->
        <div style="text-align: center;">
            <input type="submit" value="Show Access Logs">
        </div>

    </form>
    
    <% ArrayList<ArrayList<String>> accessLogs = (ArrayList<ArrayList<String>>) request.getAttribute("accessLogs");
    
    
    // Check if accessLogs is not null and not empty before iterating and displaying the data
    if (accessLogs != null && !accessLogs.isEmpty()) {
    %>
        <table align="center">
            <thead>
                <tr align="center">
                    <th>Access Log ID</th>
                    <th>User ID</th>
                    <th>Name</th>
                    <th>Access Time</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody align="center">
                <% for (ArrayList<String> logEntry : accessLogs) { %>
                    <tr>
                        <% for (String value : logEntry) { %>
                            <td>
                                <%= value %>
                            </td>
                        <% } %>
                    </tr>
                <% } %>
            </tbody>
        </table>
    <% 
    } else {
        //should never get here
    }
    %>
    
</body>
</html>
