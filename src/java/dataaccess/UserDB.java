package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import models.*;

public class UserDB {
    
    ConnectPool connectPool = ConnectPool.getInstance();
    Connection connect = connectPool.getConnection();
    
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public ArrayList<User> getUsers() throws Exception {
        
        String retrieveUsers = "SELECT *" + "FROM user;";
        
        ArrayList<User> appUsers = new ArrayList<>();
        
        try {
            
           ps = connect.prepareStatement(retrieveUsers);
           rs = ps.executeQuery();
           
           while (rs.next()) {
               
               String userEmail = rs.getString(1);
               String userFirstName = rs.getString(2);
               String userLastName = rs.getString(3);
               String userPassword = rs.getString(4);
               Role empRole = new Role(rs.getInt(5));
               
               User appUser = new User(userEmail, userFirstName, userLastName, userPassword, empRole);
               
               appUsers.add(appUser);
           }
        } finally {
            close();
        }
        return appUsers;
        
    }
        
        public String retrieveEmail(String userEmail) throws Exception {
            
            String retrieveUserEmail = "SELECT email " + "FROM user " + "WHERE email=?;";
            
            String emailRetrieved = null;
            
            try {
                
                ps = connect.prepareStatement(retrieveUserEmail);
                ps.setString(1, userEmail);
                rs = ps.executeQuery();
                
                while (rs.next()) {
                    emailRetrieved = rs.getString(1);
                }
            } finally {
                close();
            }
            return emailRetrieved;
        }

    public void addNewUser(String userEmail, String userFirstName, String userLastName, String userPassword, int roleNum) throws Exception {

        String addUser = "INSERT INTO user " + "VALUES (?, ?, ?, ?, ?);";
        
        try {
            
            ps = connect.prepareStatement(addUser);
            ps.setString(1, userEmail);
            ps.setString(2, userFirstName);
            ps.setString(3, userLastName);
            ps.setString(4, userPassword);
            ps.setInt(5, roleNum);
            ps.executeUpdate();
            
        } finally {
            close();
        }
    }
    
    public User retrieveExistingUser(String userEmail) throws Exception {
        
        String retrieveExUser = "SELECT * " + "FROM user " + "WHERE email=?;";
        
        User exUser = null;
        
        try {
            
            ps = connect.prepareStatement(retrieveExUser);
            ps.setString(1, userEmail);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                
                String userFirstName = rs.getString(2);
                String userLastName = rs.getString(3);
                String userPassword = rs.getString(4);
                Role role = new Role(rs.getInt(5));
                
                exUser = new User(userEmail, userFirstName, userLastName, userPassword, role);
            }
        } finally {
            close();
        }
        
        return exUser;
    }
    
    public void updateExistingUser(String userEmail, String userFirstName, String userLastName, String userPassword, int roleNum) throws Exception {
        
        String updateExUser = "UPDATE user " + "SET " + "first_name=? " + "last_name=? " + "password=? " + "role=? " + "WHERE email=?;";
        
        try {
            
            ps = connect.prepareStatement(updateExUser);
            ps.setString(1, userFirstName);
            ps.setString(2, userLastName);
            ps.setString(3, userPassword);
            ps.setInt(4, roleNum);
            ps.setString(5, userEmail);
            ps.executeUpdate();
        } finally {
            close();
        }
    }
    
    public void deleteUser (String userEmail) throws Exception {
        
        String delUser = "DELETE FROM user " + "WHERE email=?;";
        
        try {
            ps = connect.prepareStatement(delUser);
            ps.setString(1, userEmail);
            ps.executeUpdate();
            
        } finally {
            close();
        }
    }
    private void close() {
        DBUtil.closePreparedStatement(ps);
        connectPool.freeConnection(connect);
        if(rs != null) {
            DBUtil.closeResultSet(rs);
        }
    }
}
