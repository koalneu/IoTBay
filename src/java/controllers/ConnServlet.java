/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import models.dao.*;
import DAO.DBConnector;
import DAO.DBManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.*;

/**
 *
 * @author mjra9
 */
public class ConnServlet extends HttpServlet {
    private DBConnector db;
    private DBManager manager;
    private Connection conn;
    
    //this function runs once you load in the index page
    //make sure to include:
    //<jsp:include page="/ConnServlet" flush = "true">
    //in index.jsp file
    
    @Override
    public void init(){
        try{
            db = new DBConnector();
        }catch (ClassNotFoundException | SQLException ex){
            System.out.println("error connecting to database");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        conn = db.openConnection();
        try{
            manager = new DBManager(conn);
            
        }catch(SQLException ex){
            System.out.println("error in doGet ConnServlet"); 
        }
        //add manager to session
        session.setAttribute("manager", manager);

    }

    @Override
    public void destroy(){
        try{
            db.closeConnection();
        } catch(SQLException ex){
            System.out.println("Error disconnecting from database");
        }
    }

}
