package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import models.Role;

public class RoleDB {
    
    ConnectPool connectPool = ConnectPool.getInstance();
    Connection connect = connectPool.getConnection();
    
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public ArrayList<Role> getAllRoles() throws Exception {
        
        String retrieveRoles = "SELECT *" + "FROM role;";
        
        ArrayList<Role> empRole = new ArrayList<>();
        
    try {
        
        ps = connect.prepareStatement(retrieveRoles);
        rs = ps.executeQuery();
        
        while (rs.next()) {
            
            int roleNum = rs.getInt(1);
            String roleTitle = rs.getString(2);
            
            Role empRoles = new Role(roleNum, roleTitle);
            empRole.add(empRoles);
            
        } 
    }finally {
        close();
     }
        return empRole;
    }
    
    public String getEmpRoleTitle(int roleNum) throws Exception {
        
        String selectEmpTitle = "SELECT role_name FROM role" + "WHERE role_id = ?;";
        
        String roleTitle; 
        
        try {
             
            ps = connect.prepareStatement(selectEmpTitle);
            ps.setInt(1, roleNum);
            
            rs = ps.executeQuery();
            rs.next();
            
            roleTitle = rs.getString(1);
        } finally {
            close();
        }
        return roleTitle;
    }

    private void close() {
        
        DBUtil.closePreparedStatement(ps);
        DBUtil.closeResultSet(rs);
        connectPool.freeConnection(connect);
    }
    
}
