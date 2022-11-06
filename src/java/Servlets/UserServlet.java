package Servlets;

import java.io.IOException;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.*;
import services.*;
import java.util.*;
import java.util.logging.Logger;

public class UserServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        UserService uService = new UserService();
        RoleService rService = new RoleService();
        
        String action = request.getParameter("action");

        try {
            List<User> userList = uService.retrieveAllUsers();
            
            request.setAttribute("userList", userList);
            
            if (userList.isEmpty()) {
                
                request.setAttribute("userInDB", true);
            }
        } catch (Exception ex) {
           
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (action != null) {
            if (action.equals("edit")) {
                
                try {
                    String userEmail = request.getParameter("email");
                    String roleName = request.getParameter("role");
                    
                    User user = uService.retrieveUser(userEmail);
                    
                    request.setAttribute("userEmail", userEmail);
                    request.setAttribute("selectedUser", user);

                    request.setAttribute("editUser", true);
                    
                } catch (Exception ex) {
                    
                    Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            } else if (action.equals("delete")) {
                
                try {
                    
                    String email = request.getParameter("email");
                    uService.removeUser(email);
                    
                    request.setAttribute("deleteUser", true);
                    
                    return;
                    
                } catch (Exception ex) {
                   
                    Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        UserService uService = new UserService();
        RoleService rService = new RoleService();

        String userEmail = request.getParameter("email");
        String userFirstName = request.getParameter("firstName");
        String userLastName = request.getParameter("lastName");
        String userPassword = request.getParameter("password");
        String roleNum = request.getParameter("role"); 
        int userRoleNum = 0;

        String action = request.getParameter("action");

        try {
            List<User> userList = uService.retrieveAllUsers();
            
            request.setAttribute("userList", userList);
            
            if (userEmail == null || userEmail.equals("") || userFirstName == null || userFirstName.equals("") || userLastName == null || userLastName.equals("")
                    || userPassword == null || userPassword.equals("")) {
                
                request.setAttribute("blankFields", true);
                
                if (userList.isEmpty()) {
                    request.setAttribute("userInDB", false);
                }
                getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
                
                return;
            }

            if (action != null) {
                
                switch (action) {
                    
                    case "add":
                        if (roleNum.equals("1")) {
                            userRoleNum = 1;
                        } else {
                            userRoleNum = 2;
                        }
                                            
                        for (int i = 0; i < userList.size(); i++) {
                            
                            if (userEmail.equals(userList.get(i).getUserEmail())) {
                                
                                request.setAttribute("mes", "Error. Email is already taken");
                                
                                getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
                            }
                        }
                        uService.addUser(userEmail, userFirstName, userLastName, userPassword, rService.retrieveRoles(userRoleNum));
                        
                        request.setAttribute("message", "add");
                        break;
                    case "update":
                        if (roleNum.equals("system admin")) {
                            userRoleNum =1 ;
                        } else{
                           userRoleNum = 2;
                        }
                        
                        uService.updateUser(userEmail, userFirstName, userLastName, userPassword, rService.retrieveRoles(userRoleNum));
                        request.setAttribute("message", "update");
                        break;
                }
            }
        } catch (Exception ex) {
            
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }

        try {
            
            List<User> userList = uService.retrieveAllUsers();
            request.setAttribute("userList", userList);

        } catch (Exception ex) {
            
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }

}
            

