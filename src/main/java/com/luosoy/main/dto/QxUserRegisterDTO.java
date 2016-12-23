/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luosoy.main.dto;

import java.io.Serializable;

/**
 *
 * @author 罗真朋
 * @version 1.0
 */
public class QxUserRegisterDTO implements Serializable {

    private static final long serialVersionUID = -6909504593960061494L;

    private String loginname;
    private String oldpassword;
    private String password;
    private String password2;
    private String salt;
    private String name;
    private String email;
    private String description;

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getOldpassword() {
        return oldpassword;
    }

    public void setOldpassword(String oldpassword) {
        this.oldpassword = oldpassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "QxUserRegisterDTO{" + "loginname=" + loginname + ", password=" + password + ", password2=" + password2 + ", salt=" + salt + ", name=" + name + ", email=" + email + ", description=" + description + '}';
    }

}
