package services;

import java.util.*;
import models.User;
import models.Role;
import dataaccess.UserDB;

public class UserService {
        
    public User retrieveUser(String userEmail) throws Exception {
        
        UserDB usersDb = new UserDB();
        User appUser = usersDb.retrieveUser(userEmail);
        
        return appUser;
    }
    
    public List<User> retrieveAllUsers() throws Exception {
        
        UserDB usersDb = new UserDB();
        List<User> usersList = usersDb.retrieveAllUsers();
        
        return usersList;
    }
    
    public void addUser(String userEmail, String userFirstName, String userLastName, String userPassword, Role role) throws Exception {
        
        User user = new User();
        UserDB usersDb = new UserDB();
        usersDb.addUser(user);
    }
    
    public void updateUser(String userEmail, String userFirstName, String userLastName, String userPassword, Role role) throws Exception {
        
        User user = new User(userEmail, userFirstName, userLastName, userPassword, role);
        UserDB usersDb = new UserDB();
        usersDb.updateUser(user);
    }
    
    public void removeUser(String userEmail) throws Exception {
        
        User user = new User();
        user.setUserEmail(userEmail);
        UserDB usersDb = new UserDB();
        usersDb.removeUser(user);
    }
}
