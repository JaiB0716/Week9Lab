package models;

import java.io.Serializable;

public class Role implements Serializable {
    
    String roleTitle;
    int roleNum;
    
    public Role(int roleNum) {
        this.roleNum = roleNum;
        this.roleTitle = null;
    }
    
    public Role(int roleNum, String roleTitle) {
        this.roleNum = roleNum;
        this.roleTitle = roleTitle;
    }

    public String getRoleTitle() {
        return roleTitle;
    }

    public void setRoleTitle(String roleTitle) {
        this.roleTitle = roleTitle;
    }

    public int getRoleNum() {
        return roleNum;
    }

    public void setRoleNum(int roleNum) {
        this.roleNum = roleNum;
    }
    
}
