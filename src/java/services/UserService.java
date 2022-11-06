package services;

import java.util.*;
import models.User;
import models.Role;
import dataaccess.UserDB;

public class UserService {
        
    public User get(String email) throws Exception {
        
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        
        return user;
    }
    
    public List<User> getAll() throws Exception {
        
        UserDB userDB = new UserDB();
        List<User> usersList = userDB.getAll();
        
        return usersList;
    }
    
    public void insert(String email, String firstName, String lastName, String password, Role role) throws Exception {
        
        User user = new User();
        UserDB userDB = new UserDB();
        userDB.insert(user);
    }
    
    public void update(String email, String firstName, String lastName, String password, Role role) throws Exception {
        
        User user = new User(email, firstName, lastName, password, role);
        UserDB userDB = new UserDB();
        userDB.update(user);
    }
    
    public void delete(String email) throws Exception {
        
        User user = new User();
        user.setEmail(email);
        UserDB userDB = new UserDB();
        userDB.delete(user);
    }
}
