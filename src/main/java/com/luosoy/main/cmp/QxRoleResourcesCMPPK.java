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
public class QxRoleResourcesCMPPK implements Serializable {

    private static final long serialVersionUID = -8772220964774183434L;

    @Basic(optional = false)
    @Column(name = "ROLE_UUID")
    private String roleUuid;
    @Basic(optional = false)
    @Column(name = "RESOURCES_UUID")
    private String resourcesUuid;

    public QxRoleResourcesCMPPK() {
    }

    public QxRoleResourcesCMPPK(String roleUuid, String resourcesUuid) {
        this.roleUuid = roleUuid;
        this.resourcesUuid = resourcesUuid;
    }

    public String getRoleUuid() {
        return roleUuid;
    }

    public void setRoleUuid(String roleUuid) {
        this.roleUuid = roleUuid;
    }

    public String getResourcesUuid() {
        return resourcesUuid;
    }

    public void setResourcesUuid(String resourcesUuid) {
        this.resourcesUuid = resourcesUuid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roleUuid != null ? roleUuid.hashCode() : 0);
        hash += (resourcesUuid != null ? resourcesUuid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QxRoleResourcesCMPPK)) {
            return false;
        }
        QxRoleResourcesCMPPK other = (QxRoleResourcesCMPPK) object;
        if ((this.roleUuid == null && other.roleUuid != null) || (this.roleUuid != null && !this.roleUuid.equals(other.roleUuid))) {
            return false;
        }
        if ((this.resourcesUuid == null && other.resourcesUuid != null) || (this.resourcesUuid != null && !this.resourcesUuid.equals(other.resourcesUuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.luosoy.main.cmp.QxRoleResourcesCMPPK[ roleUuid=" + roleUuid + ", resourcesUuid=" + resourcesUuid + " ]";
    }

}
