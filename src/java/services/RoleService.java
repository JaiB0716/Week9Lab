package services;

import dataaccess.RoleDB;
import java.util.*;
import models.Role;

public class RoleService {
    
   public Role get(int roleId) throws Exception {
       
      RoleDB roleDB =  new RoleDB(); 
       Role role = roleDB.get(roleId);
       
       return role;
   }
   
   public List<Role> getAll() throws Exception {
       
       RoleDB roleDB =  new RoleDB();
       List<Role> rolesList = roleDB.getAll();
       
       return rolesList; 
   }
}
