/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Product;
import models.dao.DBManager;

/**
 *
 * @author legob
 */
public class DeleteProductController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        DBManager manager = (DBManager) session.getAttribute("manager");
        Product product = null;
        try {
            if (request.getParameter("productID") != null) {
                int id = Integer.parseInt(request.getParameter("productID"));
                manager.deleteProduct(id);
                session.setAttribute("product", null);
                request.getRequestDispatcher("staffProducts.jsp").include(request, response);
            } else {
                System.out.println("Error getting product details");
            }
        } catch (SQLException ex) {
            System.out.println("Error finding product");
        }
        
    }
}
