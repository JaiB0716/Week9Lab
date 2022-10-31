package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.*;
import services.*;
import java.util.*;

public class UserServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            HttpSession session = request.getSession();
            
            setTables(session);
            
        ArrayList<Role> empRole = RoleService.getRoles();
        session.setAttributes("empRole", empRole);
        
        String editUser = request.getParameter("edit");
        boolean editExUser = editUser != null;
        
    if(editExUser) {
        
        User existingUserEdit = UserService.getUsers(editUser);
        
        request.setAttribute("editUser", true);
    }
    
    
    }
}
            

