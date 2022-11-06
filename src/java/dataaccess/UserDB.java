package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import models.User;
import models.Role;

public class UserDB {
    
    public List<User> retrieveAllUsers() throws Exception {
        
        List<User> userList = new ArrayList<>();
        
    ConnectPool connectPool = ConnectPool.getInstance();
    Connection connect = connectPool.getConnection();
    
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    String selectSql = "SELECT email, first_name, last_name, password, role_id, role_name FROM user, role WHERE role_id=role";
    
    try {
        
        ps = connect.prepareStatement(selectSql);
        rs = ps.executeQuery();
        
        while(rs.next()) {
            
            String userEmail = rs.getString(1);
            String userFirstName = rs.getString(2);
            String userLastName = rs.getString(3);
            String userPassword = rs.getString(4);
            int rNum = rs.getInt(5);
            String rName = rs.getString(6);
            
            Role role = new Role(rNum, rName);
            
            User user = new User(userEmail, userFirstName, userLastName, userPassword, role);
            userList.add(user);
            }
            
        } finally {
                
                DBUtil.closeResultSet(rs);
                DBUtil.closePreparedStatement(ps);
                connectPool.freeConnection(connect);
                }
            
            return userList;
    }
    
    public User retrieveUser(String userEmail) throws Exception {
        
        User user = null;
        
        ConnectPool connectPool = ConnectPool.getInstance();
        Connection connect = connectPool.getConnection();
    
        PreparedStatement ps = null;
        ResultSet rs = null;
    
        String selectSql = "SELECT email, first_name, last_name, password, role_id, role_name FROM user u, role r WHERE r.role_id=u.role";
    
        try {
            
            ps = connect.prepareStatement(selectSql);
            ps.setString(1, userEmail);
            rs = ps.executeQuery();
            
            if(rs.next()) {
                
                String userFirstName = rs.getString(1);
                String userLastName = rs.getString(2);
                String userPassword = rs.getString(3);
                int rNum = rs.getInt(4);
                String rName = rs.getString(5);
                
                Role role = new Role(rNum, rName);
                
                user = new User(userEmail, userFirstName, userLastName, userPassword, role);
            }
            
            } finally {
            
                DBUtil.closeResultSet(rs);
                DBUtil.closePreparedStatement(ps);
                connectPool.freeConnection(connect);
                
        }
        
        return user;
    }
    
    public void addUser(User user) throws Exception {
        
        ConnectPool connectPool = ConnectPool.getInstance();
        Connection connect = connectPool.getConnection();
    
        PreparedStatement ps = null;
    
        String selectSql = "INSERT INTO user (email, first_name, last_name, password, role VALUES (?, ?, ?, ?, ?)";
        
        try {
            
            ps = connect.prepareStatement(selectSql);
            
            ps.setString(1, user.getUserEmail());
            ps.setString(2, user. getUserFirstName());
            ps.setString(3, user.getUserLastName());
            ps.setString(4, user.getUserPassword());
            ps.setInt(5, user.getRole().getRoleNum());
            
            ps.executeUpdate();
            
        } finally {
            
            DBUtil.closePreparedStatement(ps);
            connectPool.freeConnection(connect);
        }
        
    }
    
    public void updateUser(User user) throws Exception {
        
        ConnectPool connectPool = ConnectPool.getInstance();
        Connection connect = connectPool.getConnection();
    
        PreparedStatement ps = null;
    
        String selectSql = "UPDATE user SET first_name=?, last_name=?, password=?, role=? WHERE email=?";
        
        try {
            
            ps = connect.prepareStatement(selectSql);
            
            ps.setString(1, user. getUserFirstName());
            ps.setString(2, user.getUserLastName());
            ps.setString(3, user.getUserPassword());
            ps.setInt(4, user.getRole().getRoleNum());
            ps.setString(5, user.getUserEmail());
            
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            connectPool.freeConnection(connect);
        }
    }
    
    public void removeUser(User user) throws Exception {
        
        ConnectPool connectPool = ConnectPool.getInstance();
        Connection connect = connectPool.getConnection();
    
        PreparedStatement ps = null;
    
        String selectSql = "DELETE user WHERE email=?";
        
        try {
            
            ps = connect.prepareStatement(selectSql);
            ps.setString(1, user.getUserEmail());
            
            ps.executeUpdate();
        } finally {
            
            DBUtil.closePreparedStatement(ps);
            connectPool.freeConnection(connect);
        }
    }
}
