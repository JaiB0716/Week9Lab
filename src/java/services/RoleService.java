package services;

import dataaccess.RoleDB;
import java.util.*;
import models.Role;

public class RoleService {
    
   public Role retrieveRoles(int roleId) throws Exception {
       
      RoleDB rolesDb =  new RoleDB(); 
       Role role = rolesDb.retrieveRoles(roleId);
       
       return role;
   }
   
   public List<Role> retrieveAllRoles() throws Exception {
       
       RoleDB rolesDb =  new RoleDB();
       List<Role> rolesList = rolesDb.retrieveAllRoles();
       
       return rolesList; 
   }
}
