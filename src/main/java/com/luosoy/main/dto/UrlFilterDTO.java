/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.luosoy.main.dto;

import java.io.Serializable;

/**
 * <pre>类名: UrlFilterDTO</pre>
 * <pre>描述: TODO</pre>
 * <pre>版权: 税友软件集团股份有限公司</pre>
 * <pre>日期: 2016-12-22 上午09:44:30 </pre>
 *
 * @author 罗真朋
 * @version 1.0
 */
public class UrlFilterDTO implements Serializable{
    private String url;
    private String roles; //所需要的角色，可省略
    private String permissions; //所需要的权限，可省略

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "UrlFilterDTO{" + "url=" + url + ", roles=" + roles + ", permissions=" + permissions + '}';
    }
    
}
