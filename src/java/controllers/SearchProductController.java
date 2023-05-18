package controllers;

import models.dao.*;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Product;

/**
 *
 * @author benjamin
 */
public class SearchProductController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        DBManager manager = (DBManager) session.getAttribute("manager");
        String name = request.getParameter("productName");
        session.setAttribute("searchProductErr", null);
        Product product = null;
        try {
            product = manager.findProduct(name);
            
            if (product == null) {
                //System.out.println("Product is null");
                session.setAttribute("searchProductErr", "Product not found.");
                request.getRequestDispatcher("staffProducts.jsp").include(request, response);
            } else {
                session.setAttribute("product", product);
                request.getRequestDispatcher("productDetails.jsp").include(request, response);
            }
        } catch (SQLException ex) {
            System.out.println("Error finding product");
        }
    }
}
