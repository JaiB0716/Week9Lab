package services;

import java.util.*;
import models.User;
import dataaccess.UserDB;

public class UserService {
    
    public UserService() {
        
    }
    
    public static User retrieveUser(String userEmail) {
        User user;
        
        try {
            
            user = new UserDB.retrieveUser(userEmail);
            
        } catch (Exception e) {
            
            System.out.println(e);
            user = null;
        }
        
        return user;
    }
    
    public static Arraylist<User> getUsers() {
        
        ArrayList<User> appUsers;
        
        try {
            
            appUsers = new UserDB.getUsers();
            
            for(User appUsers :)
        }
    }
}
