/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.Controller;

import at.kaindorf.Bl.JWT;
import at.kaindorf.DB.DB_Access;
import at.kaindorf.beans.BetGroupPhase;
import at.kaindorf.beans.BetKoPhase;
import at.kaindorf.beans.Country;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
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
    private List<Country> countries = new ArrayList<>();
    private List<Group> joinedGroups = null;
    private int groupIdCreateBet;
    int groupIDShowBets;
    private int betIdMakeBetKO;
    private int betIdMakeBetGroup;
    List<BetKoPhase> betListKO;
    List<BetGroupPhase> betListGroup;
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
        try {
            countries = DB_Access.getInstance().getCountries();
            config.getServletContext().setAttribute("countries", countries);
        } catch (SQLException ex) {
            config.getServletContext().setAttribute("countries", "fehler");
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // Set User
        try {
            request.setAttribute("userID", JWT.decodeJWT(jwtUser).getId());
        } catch (IllegalArgumentException e) {
            request.setAttribute("userID", null);
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

            // Create Bet
        } else if (request.getParameter("createBetForm") != null || (request.getParameter("betType") != null && request.getParameter("createBetDB") == null)) {
            groupIdCreateBet = request.getParameter("createBetForm") != null ? Integer.parseInt(request.getParameter("createBetForm")) : groupIdCreateBet;
            request.setAttribute("betType", request.getParameter("betType") != null ? request.getParameter("betType") : "group");
            request.getRequestDispatcher("jCreateBetPage.jsp").forward(request, response);

//            // Show Joined Groups
//        } else if(request.getParameter("showGroups") != null){
//            request.getRequestDispatcher("jShowJoinedGroupsPage.jsp").forward(request, response);
//            
            // Welcomepage
        } else if (request.getParameter("makeBetKO") != null) {
            String[] tokens = request.getParameter("makeBetKO").split(" ");
            betIdMakeBetKO = Integer.parseInt(tokens[1]);
            for (BetKoPhase betKoPhase : betListKO) {
                if (betKoPhase.getBetId() == Integer.parseInt(tokens[1])) {
                    request.setAttribute("bet", betKoPhase);
                    break;
                }
            }
            request.getRequestDispatcher("jMakeBetKOPage.jsp").forward(request, response);

        } else if (request.getParameter("makeBetGroup") != null) {
            String[] tokens = request.getParameter("makeBetGroup").split(" ");
            betIdMakeBetGroup = Integer.parseInt(tokens[1]);
            for (BetGroupPhase betGroupPhase : betListGroup) {
                if (betGroupPhase.getId() == Integer.parseInt(tokens[1])) {
                    request.setAttribute("bet", betGroupPhase);
                    break;
                }
            }
            request.getRequestDispatcher("jMakeBetGroupPage.jsp").forward(request, response);

        } else if (request.getParameter("logout") != null) {
            request.getRequestDispatcher("homepage.jsp").forward(request, response);
        } else if (request.getParameter("showGroupsForm") != null) {
            request.getRequestDispatcher("jShowJoinedGroupsPage.jsp").forward(request, response);
        } else if (request.getParameter("showDetail") != null || request.getParameter("createBetDB") != null
                || request.getParameter("makeBetDBKO") != null || request.getParameter("makeBetDBGroup") != null
                || request.getParameter("closeBetGroup") != null || request.getParameter("closeBetKO") != null) {
            request.getRequestDispatcher("jShowGroupDetail.jsp").forward(request, response);
        } else if(request.getParameter("closeGroupBet") != null){
            int betId = Integer.parseInt(request.getParameter("closeGroupBet"));
            for (BetGroupPhase betGroupPhase : betListGroup) {
                if (betGroupPhase.getId() == betId) {
                    request.setAttribute("bet", betGroupPhase);
                    break;
                }
            }
            request.getRequestDispatcher("jCloseBetGroup.jsp").forward(request, response);
        } 
        else if (jwtUser != null && !jwtUser.isEmpty()){
            request.getRequestDispatcher("jWelcomePage.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("homepage.jsp").forward(request, response);
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
                jwtUser = JWT.createJWT(email, DB_Access.getInstance().getUsernameByEmail(email), "login-success", 1000000000);
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
                System.out.println(ex.toString());
            }
            if (pwCompare != -1 && password.hashCode() == pwCompare) {
                try {
                    jwtUser = JWT.createJWT(email, DB_Access.getInstance().getUsernameByEmail(email), "login-success", 1000000000);
                } catch (SQLException ex) {
                    Logger.getLogger(BetslyServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println(pwCompare + "----" + password.hashCode());
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
                    if (pwCompare != -1 && pwCompare == pw.hashCode()) {
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
        } else if (request.getParameter("showGroupsForm") != null) {
            try {
                joinedGroups = DB_Access.getInstance().getJoinedGroups(JWT.decodeJWT(jwtUser).getId());
                request.getSession().setAttribute("joinedGroups", joinedGroups);
            } catch (SQLException ex) {
                databaseError = true;
            } catch (IllegalArgumentException e) {
                joinGroupError = true;
            }

            //--------------
            // create Bet Form
            //--------------  
        } else if (request.getParameter("createBetDB") != null) {
            if (request.getParameter("betType").equals("group")) {
                Country[] countryAr = new Country[4];
                String name = request.getParameter("betName");
                for (int i = 1; i <= 4; i++) {
                    for (Country country : countries) {
                        if (country.getName().equals(request.getParameter("country" + i))) {
                            countryAr[i - 1] = country;
                        }
                    }
                }
                try {
                    DB_Access.getInstance().createBetGroupPhase(countryAr, groupIdCreateBet, name);
                } catch (SQLException ex) {
                    Logger.getLogger(BetslyServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                Country[] countryAr = new Country[2];
                String name = request.getParameter("betName");
                for (int i = 1; i <= 2; i++) {
                    for (Country country : countries) {
                        if (country.getName().equals(request.getParameter("country" + i))) {
                            countryAr[i - 1] = country;
                        }
                    }
                }
                try {
                    DB_Access.getInstance().createBetKOPhase(countryAr, groupIdCreateBet, name);
                } catch (SQLException ex) {
                    Logger.getLogger(BetslyServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            //--------------
            // Make Bet KO
            //--------------
        } else if (request.getParameter("makeBetDBKO") != null) {
            try {
                DB_Access.getInstance().makeBetKOPhase(betIdMakeBetKO, JWT.decodeJWT(jwtUser).getId(), Integer.parseInt(request.getParameter("tip")), request.getParameter("score"));

            } catch (SQLException ex) {
                Logger.getLogger(BetslyServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            //--------------
            // Make Bet Group
            //--------------
        } else if (request.getParameter("makeBetDBGroup") != null) {
            try {
                DB_Access.getInstance().makeBetGroupPhase(betIdMakeBetGroup, JWT.decodeJWT(jwtUser).getId(), Integer.parseInt(request.getParameter("first")),
                        Integer.parseInt(request.getParameter("second")), Integer.parseInt(request.getParameter("third")), Integer.parseInt(request.getParameter("fourth")));

            } catch (SQLException ex) {
                request.setAttribute("exe", ex);
            }

        } //--------------
        // Close Bet KO
        //--------------
        else if (request.getParameter("closeBetKO") != null) {
            try {
                String tokens[] = request.getParameter("closeBetKO").split(" ");
                int betID = Integer.parseInt(tokens[0]);
                int groupID = Integer.parseInt(tokens[1]);
                DB_Access.getInstance().closeBetKOPhase(betID);
                if (request.getParameter("scoreClose") != null && !request.getParameter("scoreClose").trim().equals("")) {
                    String[] endScore = request.getParameter("scoreClose").split(":");
                    DB_Access.getInstance().distributePointsKO(request.getParameter("winner"), endScore, betID, groupID);
                }
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        } //--------------
        // Close Bet Group
        //--------------
        else if (request.getParameter("closeBetGroup") != null) {
            try {
                String tokens[] = request.getParameter("closeBetGroup").split(" ");
                int betID = Integer.parseInt(tokens[0]);
                int groupID = Integer.parseInt(tokens[1]);
                DB_Access.getInstance().closeBetGroupPhase(betID);
                DB_Access.getInstance().distributePointsGroup(request.getParameter("c1"), request.getParameter("c2"),
                        request.getParameter("c3"), request.getParameter("c4"), betID, groupID);
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }

        //--------------
        // Show bets 
        //--------------
        if (request.getParameter("showDetail") != null || request.getParameter("createBetDB") != null
                || request.getParameter("makeBetDBKO") != null || request.getParameter("makeBetDBGroup") != null
                || request.getParameter("closeBetGroup") != null || request.getParameter("closeBetKO") != null) {
            betListKO = new ArrayList<>();
            betListGroup = new ArrayList<>();
            groupIDShowBets = request.getParameter("showDetail") != null ? Integer.parseInt(request.getParameter("showDetail")) : groupIDShowBets;
            for (Group group : joinedGroups) {
                if (groupIDShowBets == group.getId()) {
                    request.getSession().setAttribute("currentGroup", group);
                }
            }
            try {
                betListKO.addAll(DB_Access.getInstance().getKOPhaseBetsByGroup(groupIDShowBets));
                betListGroup.addAll(DB_Access.getInstance().getGroupPhaseBetsByGroup(groupIDShowBets));
                request.getSession().setAttribute("betListGroup", betListGroup);
                request.getSession().setAttribute("betListKo", betListKO);
                request.getSession().setAttribute("ranks", DB_Access.getInstance().getRanksForUser(groupIDShowBets));
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }

        if (jwtUser != null && !jwtUser.isEmpty()) {
            request.setAttribute("username", JWT.decodeJWT(jwtUser).getIssuer());
        }
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
