/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.Controller;

import at.kaindorf.Bl.JWT;
import at.kaindorf.DB.DB_Access;
import at.kaindorf.beans.Group;
import at.kaindorf.beans.User;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jiazh
 */
@WebServlet(name = "BetslyServlet", urlPatterns = {"/BetslyServlet"})
public class BetslyServlet extends HttpServlet {

    private String jwtUser = "";
    private boolean createGroupError = false;
    private boolean databaseError = false;
    private boolean joinGroupError = false;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        if (request.getParameter("registration") != null) {
            request.getRequestDispatcher("jRegistrationPage.jsp").forward(request, response);
        } else if (request.getParameter("login") != null) {
            request.getRequestDispatcher("jLoginPage.jsp").forward(request, response);
        } else if (request.getParameter("createGroupForm") != null) {
            request.getRequestDispatcher("jCreateGroupPage.jsp").forward(request, response);
        } else if (request.getParameter("joinGroupForm") != null) {
            request.getRequestDispatcher("jJoinGroupPage.jsp").forward(request, response);
        } else {
            try {
                request.setAttribute("jwtUser", JWT.decodeJWT(jwtUser).getId());
            } catch (IllegalArgumentException e) {
                request.setAttribute("jwtUser", null);
            }
            request.getRequestDispatcher("jWelcomePage.jsp").forward(request, response);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("confirmRegistration") != null) {
            String username = request.getParameter("username");
            String passwort = request.getParameter("password");
            String email = request.getParameter("email");

            try {
                DB_Access.getInstance().insertUser(new User(email, username, passwort));
            } catch (SQLException ex) {
                databaseError = true;
            }
        } else if (request.getParameter("confirmLogin") != null) {
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            int pwCompare = -1;
            try {
                pwCompare = DB_Access.getInstance().getPasswordByEmail(email);
            } catch (SQLException ex) {
                databaseError = true;
            }
            if (password.hashCode() == pwCompare) {
                jwtUser = JWT.createJWT(email, email, "login-success", 1000000000);
            }
        } else if (request.getParameter("createGroup") != null) {
            try {
                if ((request.getParameter("createGroupName") != null || !request.getParameter("createGroupName").trim().isEmpty())
                        && (request.getParameter("createGroupDes") != null || !request.getParameter("createGroupDes").trim().isEmpty())) {
                    DB_Access.getInstance().createGroup(request.getParameter("createGroupName"), request.getParameter("createGroupDes"), JWT.decodeJWT(jwtUser).getId());
                    createGroupError = false;
                }
                //boolean test = DB_Access.getInstance().createGroup("Sarahs gruppe", JWT.decodeJWT(jwtUser).getId());
                //List<Group> test = DB_Access.getInstance().getJoinedGroups("Sarahs gruppe");
                //request.setAttribute("test", test);
            } catch (SQLException ex) {
                databaseError = true;
            } catch (IllegalArgumentException e) {
                createGroupError = true;
            }
        } else if (request.getParameter("joinGroup") != null) {
            if (request.getParameter("joinGroupName") != null || !request.getParameter("joinGroupName").trim().isEmpty()) {
                try {
                    DB_Access.getInstance().joinGroup(request.getParameter("joinGroupName"), JWT.decodeJWT(jwtUser).getId());
                } catch (SQLException ex) {
                    databaseError = true;
                } catch (IllegalArgumentException e) {
                    joinGroupError = true;
                }
            }

        } else if (request.getParameter("logout") != null) {
            jwtUser = "";
        } else if (request.getParameter("showGroups") != null) {
            try {
                    List<Group> groups = DB_Access.getInstance().getJoinedGroups(JWT.decodeJWT(jwtUser).getId());
                    request.setAttribute("joinedGroups", groups);
                } catch (SQLException ex) {
                    databaseError = true;
                } catch (IllegalArgumentException e) {
                    joinGroupError = true;
                }
        } 
        else {
            createGroupError = false;
            databaseError = false;
            joinGroupError = false;
        }

        request.setAttribute("createGroupError", createGroupError);
        request.setAttribute("databaseError", databaseError);
        request.setAttribute("joinGroupError", joinGroupError);
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
