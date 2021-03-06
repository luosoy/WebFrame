/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.luosoy.main.cmp;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author 罗真朋
 * @version 1.0
 */
@Entity
@Table(name = "qx_user_role")
public class QxUserRoleCMP implements Serializable {

    private static final long serialVersionUID = 5644289882426109728L;

    @EmbeddedId
    protected QxUserRoleCMPPK qxUserRoleCMPPK;

    public QxUserRoleCMP() {
    }

    public QxUserRoleCMP(QxUserRoleCMPPK qxUserRoleCMPPK) {
        this.qxUserRoleCMPPK = qxUserRoleCMPPK;
    }

    public QxUserRoleCMP(String userUuid, String roleUuid) {
        this.qxUserRoleCMPPK = new QxUserRoleCMPPK(userUuid, roleUuid);
    }

    public QxUserRoleCMPPK getQxUserRoleCMPPK() {
        return qxUserRoleCMPPK;
    }

    public void setQxUserRoleCMPPK(QxUserRoleCMPPK qxUserRoleCMPPK) {
        this.qxUserRoleCMPPK = qxUserRoleCMPPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (qxUserRoleCMPPK != null ? qxUserRoleCMPPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QxUserRoleCMP)) {
            return false;
        }
        QxUserRoleCMP other = (QxUserRoleCMP) object;
        if ((this.qxUserRoleCMPPK == null && other.qxUserRoleCMPPK != null) || (this.qxUserRoleCMPPK != null && !this.qxUserRoleCMPPK.equals(other.qxUserRoleCMPPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.luosoy.main.cmp.QxUserRoleCMP[ qxUserRoleCMPPK=" + qxUserRoleCMPPK + " ]";
    }

}
