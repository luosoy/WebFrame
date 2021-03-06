/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luosoy.main.cmp;

import com.luosoy.frame.jpa.identity.IdInjectionEntityListener;
import com.luosoy.frame.jpa.identity.IdSequenceConsumer;
import com.luosoy.frame.jpa.identity.UUIDProducer;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author 罗真朋
 * @version 1.0
 */
@Entity
@Table(name = "qx_resources")
@EntityListeners(value = {IdInjectionEntityListener.class})
public class QxResourcesCMP implements Serializable {

    private static final long serialVersionUID = 6579114717245191602L;

    @Id
    @Basic(optional = false)
    @IdSequenceConsumer(producerClass = UUIDProducer.class)
    private String uuid;
    @Basic(optional = false)
    private String name;
    @Basic(optional = false)
    private String parentuuid;
    @Basic(optional = false)
    private int layer;
    @Basic(optional = false)
    @Column(name = "RES_TYPE_DM")
    private String resTypeDm;
    private String resurl;
    private String description;
    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    private Date lrrq;
    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    private Date xgrq;
    @Basic(optional = false)
    private Character yxbz;

    public QxResourcesCMP() {
    }

    public QxResourcesCMP(String uuid) {
        this.uuid = uuid;
    }

    public QxResourcesCMP(String uuid, String name, String parentuuid, int layer, String resTypeDm, Date lrrq, Date xgrq, Character yxbz) {
        this.uuid = uuid;
        this.name = name;
        this.parentuuid = parentuuid;
        this.layer = layer;
        this.resTypeDm = resTypeDm;
        this.lrrq = lrrq;
        this.xgrq = xgrq;
        this.yxbz = yxbz;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentuuid() {
        return parentuuid;
    }

    public void setParentuuid(String parentuuid) {
        this.parentuuid = parentuuid;
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public String getResTypeDm() {
        return resTypeDm;
    }

    public void setResTypeDm(String resTypeDm) {
        this.resTypeDm = resTypeDm;
    }

    public String getResurl() {
        return resurl;
    }

    public void setResurl(String resurl) {
        this.resurl = resurl;
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
    public int hashCode() {
        int hash = 0;
        hash += (uuid != null ? uuid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QxResourcesCMP)) {
            return false;
        }
        QxResourcesCMP other = (QxResourcesCMP) object;
        if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.luosoy.main.cmp.QxResourcesCMP[ uuid=" + uuid + " ]";
    }

}
