package dataaccess;

import java.util.*;
import javax.persistence.EntityManager;
import models.Role;

public class RoleDB {
    
    public List<Role> getAll() throws Exception {
 
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
    
    try {
         
        List<Role> rolesList = em.createNamedQuery("Role.findAll", Role.class).getResultList();
        return rolesList;
        
        } finally {
        
          em.close();
    }
}
    
    public Role get(int roleId) throws Exception {
        
           EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {

            Role role = em.find(Role.class, roleId);
            return role;
            } finally {
            
              em.close();
        }

}
}
