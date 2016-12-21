/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luosoy.main.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author 罗真朋
 * @version 1.0
 */
public class QxUserDTO implements Serializable {

    private static final long serialVersionUID = -3258491866904384308L;

    private String uuid;
    private String loginname;
    private String password;
    private String salt;
    private String name;
    private String email;
    private String description;
    private Date lrrq;
    private Date xgrq;
    private Character yxbz;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Date getLrrq() {
        return lrrq;
    }

    public void setLrrq(Date lrrq) {
        this.lrrq = lrrq;
    }

    public Date getXgrq() {
        return xgrq;
    }

    public void setXgrq(Date xgrq) {
        this.xgrq = xgrq;
    }

    public Character getYxbz() {
        return yxbz;
    }

    public void setYxbz(Character yxbz) {
        this.yxbz = yxbz;
    }

    @Override
    public String toString() {
        return "QxUserDTO{" + "uuid=" + uuid + ", loginname=" + loginname + ", password=" + password + ", salt=" + salt + ", name=" + name + ", email=" + email + ", description=" + description + ", lrrq=" + lrrq + ", xgrq=" + xgrq + ", yxbz=" + yxbz + '}';
    }

}
