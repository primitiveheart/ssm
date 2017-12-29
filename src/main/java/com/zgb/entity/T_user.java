package com.zgb.entity;

import java.io.Serializable;

/**
 * Created by admin on 2017/12/28.
 */
public class T_user implements Serializable {
    private Integer id ;
    private String userName;
    private String password;
    private Integer roleId;

    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}

    public String getUserName() {return userName;}
    public void setUserName(String userName) {this.userName = userName;}

    public String getPassword() {return password;}
    public void setPassword(String passwword) {this.password = passwword;}

    public Integer getRoleId() {return roleId;}
    public void setRoleId(Integer roleId) {this.roleId = roleId;}
}
