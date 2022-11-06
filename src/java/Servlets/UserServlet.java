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
            List<User> userList = uService.getAll();
            
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
                    
                    User user = uService.get(userEmail);
                    
                    request.setAttribute("userEmail", userEmail);
                    request.setAttribute("selectedUser", user);

                    request.setAttribute("editUser", true);
                    
                } catch (Exception ex) {
                    
                    Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            } else if (action.equals("delete")) {
                
                try {
                    
                    String email = request.getParameter("email");
                    uService.delete(email);
                    
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

        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        String role = request.getParameter("role"); 
        int userRoleNum = 0;

        String action = request.getParameter("action");

        try {
            List<User> userList = uService.getAll();
            
            request.setAttribute("userList", userList);
            
            if (email == null || email.equals("") || firstName == null || firstName.equals("") || lastName == null || lastName.equals("")
                    || password == null || password.equals("")) {
                
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
                        if (role.equals("1")) {
                            userRoleNum = 1;
                        } else {
                            userRoleNum = 2;
                        }
                                            
                        for (int i = 0; i < userList.size(); i++) {
                            
                            if (email.equals(userList.get(i).getEmail())) {
                                
                                request.setAttribute("mes", "Error. Email is already taken");
                                
                                getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
                            }
                        }
                        uService.insert(email, firstName, lastName, password, rService.get(userRoleNum));
                        
                        request.setAttribute("message", "add");
                        break;
                    case "update":
                        if (role.equals("system admin")) {
                            userRoleNum =1 ;
                        } else{
                           userRoleNum = 2;
                        }
                        
                        uService.update(email, firstName, lastName, password, rService.get(userRoleNum));
                        request.setAttribute("message", "update");
                        break;
                }
            }
        } catch (Exception ex) {
            
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }

        try {
            
            List<User> userList = uService.getAll();
            request.setAttribute("userList", userList);

        } catch (Exception ex) {
            
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }

}
            

