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
public class UrlAllDTO implements Serializable {

    private String url;
    private String role; //所需要的角色，可省略
    private int permission; //所需要的权限，可省略

    public UrlAllDTO() {
    }

    public UrlAllDTO(String url, String role, int permission) {
        this.url = url;
        this.role = role;
        this.permission = permission;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "UrlAllDTO{" + "url=" + url + ", role=" + role + ", permission=" + permission + '}';
    }

}
