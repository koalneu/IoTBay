package controllers;

import DAO.DBManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class logoutController extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        DBManager manager = (DBManager) session.getAttribute("manager");
        try {
            manager.addAccessLogEntry(user.getUserEmail(), user.getUserFirstName(), "Log out");
            session.invalidate();
            
            response.sendRedirect("index.jsp");
        } catch (SQLException ex) {
            Logger.getLogger(logoutController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(logoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
