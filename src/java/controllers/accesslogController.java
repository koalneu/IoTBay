package controllers;

import models.dao.DBManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;

/**
 *
 * @author Wilson
 */
public class accesslogController extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ACCESSLOG CONTROLLER");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String email = user.getUserEmail();
        DBManager manager = (DBManager) session.getAttribute("manager");
        ArrayList<ArrayList<String>> accessLogs = manager.getAccessLogs(email);
        request.setAttribute("accessLogs", accessLogs);
        request.getRequestDispatcher("accesslog.jsp").forward(request, response);
    }

}
