/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luosoy.main.cmp;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author 罗真朋
 * @version 1.0
 */
@Embeddable
public class QxUserRoleCMPPK implements Serializable {

    private static final long serialVersionUID = -6421758200539795487L;

    @Basic(optional = false)
    @Column(name = "USER_UUID")
    private String userUuid;
    @Basic(optional = false)
    @Column(name = "ROLE_UUID")
    private String roleUuid;

    public QxUserRoleCMPPK() {
    }

    public QxUserRoleCMPPK(String userUuid, String roleUuid) {
        this.userUuid = userUuid;
        this.roleUuid = roleUuid;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public String getRoleUuid() {
        return roleUuid;
    }

    public void setRoleUuid(String roleUuid) {
        this.roleUuid = roleUuid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userUuid != null ? userUuid.hashCode() : 0);
        hash += (roleUuid != null ? roleUuid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QxUserRoleCMPPK)) {
            return false;
        }
        QxUserRoleCMPPK other = (QxUserRoleCMPPK) object;
        if ((this.userUuid == null && other.userUuid != null) || (this.userUuid != null && !this.userUuid.equals(other.userUuid))) {
            return false;
        }
        if ((this.roleUuid == null && other.roleUuid != null) || (this.roleUuid != null && !this.roleUuid.equals(other.roleUuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.luosoy.main.cmp.QxUserRoleCMPPK[ userUuid=" + userUuid + ", roleUuid=" + roleUuid + " ]";
    }

}
