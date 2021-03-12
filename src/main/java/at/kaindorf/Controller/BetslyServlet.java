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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletConfig;
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
    
    
    
    @Override
    public void init(ServletConfig config)
            throws ServletException {
        super.init(config); //To change body of generated methods, choose Tools | Templates.
        
        //Country Liste erstellen für DB
//        File file = Paths.get(config.getServletContext().getRealPath("/fifa_countries_audience_csv.csv")).toFile();
//        FileReader fr;
//        try {
//            fr = new FileReader(file);
//            BufferedReader br = new BufferedReader(fr);
//            
//            List<String> c = new ArrayList<>();
//            
//            String line = "";
//            br.readLine();
//            while((line = br.readLine()) != null){
//                String[] tokens = line.split(",");
//                c.add(tokens[0]);
//            }
//            
//            DB_Access.getInstance().writeCountries(c);
//        } catch (FileNotFoundException ex) {
//            
//        } catch (IOException ex) {
//            
//        } catch (SQLException ex) {
//            
//        }
        
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        // Set User
        try {
            request.setAttribute("jwtUser", JWT.decodeJWT(jwtUser).getId());
        } catch (IllegalArgumentException e) {
            request.setAttribute("jwtUser", null);
        }
        // Registration 
        if (request.getParameter("registration") != null) {
            request.getRequestDispatcher("jRegistrationPage.jsp").forward(request, response);
            
            // Login 
        } else if (request.getParameter("login") != null) {
            request.getRequestDispatcher("jLoginPage.jsp").forward(request, response);

            // Create Group
        } else if (request.getParameter("createGroupForm") != null) {
            request.getRequestDispatcher("jCreateGroupPage.jsp").forward(request, response);

            // Join Group
        } else if (request.getParameter("joinGroupForm") != null) {
            request.getRequestDispatcher("jJoinGroupPage.jsp").forward(request, response);

            // Welcomepage
        } else {
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

            //--------------
            // Registration 
            //--------------
        if (request.getParameter("confirmRegistration") != null) {
            String username = request.getParameter("username");
            String passwort = request.getParameter("password");
            String email = request.getParameter("email");

            try {
                DB_Access.getInstance().insertUser(new User(email, username, passwort));
            } catch (SQLException ex) {
                databaseError = true;
            }

            //--------------
            // Login 
            //--------------
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
            else {
                request.setAttribute("test",  pwCompare + " " + password.hashCode());
            }

            //--------------
            // create Group 
            //--------------
        } else if (request.getParameter("createGroup") != null) {
            try {
                if ((request.getParameter("createGroupName") != null || !request.getParameter("createGroupName").trim().isEmpty())
                        && (request.getParameter("createGroupDes") != null || !request.getParameter("createGroupDes").trim().isEmpty())) {
                    DB_Access.getInstance().createGroup(request.getParameter("createGroupName"), request.getParameter("createGroupDes"), request.getParameter("createGroupPassword"), JWT.decodeJWT(jwtUser).getId());
                    createGroupError = false;
                }
            } catch (SQLException ex) {
                databaseError = true;
            } catch (IllegalArgumentException e) {
                createGroupError = true;
            }

            //--------------
            // join Group 
            //--------------
        } else if (request.getParameter("joinGroup") != null) {
            if ((request.getParameter("joinGroupName") != null || !request.getParameter("joinGroupName").trim().isEmpty()) 
                    && (request.getParameter("joinGroupPW") != null || !request.getParameter("joinGroupPW").trim().isEmpty())) {
                String groupName = request.getParameter("joinGroupName");
                String pw = request.getParameter("joinGroupPW");
                int pwCompare = -1;
                try {
                    pwCompare = DB_Access.getInstance().getPasswordByGroupName(groupName);
                    if(pwCompare != -1 && pwCompare == pw.hashCode()){
                        DB_Access.getInstance().joinGroup(groupName, JWT.decodeJWT(jwtUser).getId());
                    }
                } catch (SQLException ex) {
                    databaseError = true;
                } catch (IllegalArgumentException e) {
                    joinGroupError = true;
                }
            }

            //--------------
            // Logout 
            //--------------
        } else if (request.getParameter("logout") != null) {
            jwtUser = "";

            //--------------
            // show Group 
            //--------------
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
            //--------------
            // Error Handling 
            //--------------
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
