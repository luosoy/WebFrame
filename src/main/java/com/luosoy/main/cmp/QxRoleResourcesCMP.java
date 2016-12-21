/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.luosoy.main.cmp;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * <pre>类名: QxRoleResourcesCMP</pre>
 * <pre>描述: TODO</pre>
 * <pre>版权: 税友软件集团股份有限公司</pre>
 * <pre>日期: 2016-12-21 上午09:55:12 </pre>
 *
 * @author 罗真朋
 * @version 1.0
 */
@Entity
@Table(name = "qx_role_resources")
public class QxRoleResourcesCMP implements Serializable {

    private static final long serialVersionUID = 4433091393352208206L;

    @EmbeddedId
    protected QxRoleResourcesCMPPK qxRoleResourcesCMPPK;
    @Basic(optional = false)
    @Column(name = "PERMISSION_DM")
    private int permissionDm;

    public QxRoleResourcesCMP() {
    }

    public QxRoleResourcesCMP(QxRoleResourcesCMPPK qxRoleResourcesCMPPK) {
        this.qxRoleResourcesCMPPK = qxRoleResourcesCMPPK;
    }

    public QxRoleResourcesCMP(QxRoleResourcesCMPPK qxRoleResourcesCMPPK, int permissionDm) {
        this.qxRoleResourcesCMPPK = qxRoleResourcesCMPPK;
        this.permissionDm = permissionDm;
    }

    public QxRoleResourcesCMP(String roleUuid, String resourcesUuid) {
        this.qxRoleResourcesCMPPK = new QxRoleResourcesCMPPK(roleUuid, resourcesUuid);
    }

    public QxRoleResourcesCMPPK getQxRoleResourcesCMPPK() {
        return qxRoleResourcesCMPPK;
    }

    public void setQxRoleResourcesCMPPK(QxRoleResourcesCMPPK qxRoleResourcesCMPPK) {
        this.qxRoleResourcesCMPPK = qxRoleResourcesCMPPK;
    }

    public int getPermissionDm() {
        return permissionDm;
    }

    public void setPermissionDm(int permissionDm) {
        this.permissionDm = permissionDm;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (qxRoleResourcesCMPPK != null ? qxRoleResourcesCMPPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QxRoleResourcesCMP)) {
            return false;
        }
        QxRoleResourcesCMP other = (QxRoleResourcesCMP) object;
        if ((this.qxRoleResourcesCMPPK == null && other.qxRoleResourcesCMPPK != null) || (this.qxRoleResourcesCMPPK != null && !this.qxRoleResourcesCMPPK.equals(other.qxRoleResourcesCMPPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.luosoy.main.cmp.QxRoleResourcesCMP[ qxRoleResourcesCMPPK=" + qxRoleResourcesCMPPK + " ]";
    }

}
