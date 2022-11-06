package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import models.Role;

public class RoleDB {
    
    public List<Role> retrieveAllRoles() throws Exception {
        List<Role> roleList = new ArrayList<>();
    
    ConnectPool connectPool = ConnectPool.getInstance();
    Connection connect = connectPool.getConnection();
    
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    String selectSql = "SELECT role_id, role_name FROM role";
    
    try {
         
        ps = connect.prepareStatement(selectSql);
        rs = ps.executeQuery(selectSql);
        
        while(rs.next()) {
            
            int rNum = rs.getInt(1);
            String rName = rs.getString(2);
            
            Role role = new Role(rNum, rName);
            roleList.add(role);
            }
        
        } finally {
        
        DBUtil.closePreparedStatement(ps);
        DBUtil.closeResultSet(rs);
        connectPool.freeConnection(connect);
    }
    
    return roleList;
    
}
    
    public Role retrieveRoles(int rNum) throws Exception {
        
        Role role = null;
        
        ConnectPool connectPool = ConnectPool.getInstance();
        Connection connect = connectPool.getConnection();
    
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String selectSql = "SELECT role_id, role_name FROM role WHERE role_id=?";
        
        try {
            
            ps = connect.prepareStatement(selectSql);
            
            ps.setInt(1, rNum);
            rs = ps.executeQuery();
            
            if(rs.next()) {
                
                int rNum2 = rs.getInt(1);
                String rName = rs.getString(2);
                role = new Role(rNum2, rName);
            }
            
            } finally {
            
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
            connectPool.freeConnection(connect);
        }
        
        return role;
    }
}
