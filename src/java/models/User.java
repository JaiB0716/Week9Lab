package models;

import java.io.Serializable;

public class User implements Serializable {
    
    private String userEmail;
    private String userFirstName;
    private String userLastName;
    private String userPassword;
    private Role role;
    
    public User(String userEmail, String userFirtName, String userLastName, String userPassword, Role role) {
        
        this.userEmail = userEmail;
        this.userFirstName = userFirstName; 
        this.userLastName = userLastName;
        this.userPassword = userPassword;
        this.role = role;
    }

    public User() {
     
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    
    
}
