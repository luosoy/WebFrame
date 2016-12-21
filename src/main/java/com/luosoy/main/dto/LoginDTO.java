/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luosoy.main.dto;

import java.io.Serializable;

/**
 * @author 罗真朋
 * @version 1.0
 */
public class LoginDTO implements Serializable {

    private static final long serialVersionUID = -4850793284494121844L;

    private String loginName;
    private String password;
    private boolean remember;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

    @Override
    public String toString() {
        return "UserLoginDTO{" + "loginName=" + loginName + ", password=" + password + ", remember=" + remember + '}';
    }

}
