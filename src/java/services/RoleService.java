package services;

import dataaccess.RoleDB;
import java.util.*;
import models.Role;

public class RoleService {
    
    public static ArrayList<Role> getRoles() {
        ArrayList<Role> empRoles;
        
        try {
            
            empRoles = new RoleDB.getRoles();
            
        } catch (Exception e) {
            System.out.println(e);
            empRoles = null;
        }
        
        return empRoles;
    }
    
    public static String retrieveRoleTitle (int roleNum) {
        
        String roleTitle;
        
        try {
            
            roleTitle = new RoleDB.getEmpRoleTitle();
        } catch (Exception e) {
            
            System.out.println(e);
            roleTitle = "null";
        
        }
        
        return roleTitle;
    }
}
