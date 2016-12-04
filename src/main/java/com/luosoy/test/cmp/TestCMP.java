/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luosoy.test.cmp;

import com.luosoy.frame.jpa.identity.IdInjectionEntityListener;
import com.luosoy.frame.jpa.identity.IdSequenceConsumer;
import com.luosoy.test.sequence.TestSequenceProducer;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author luozp
 */
@Entity
@Table(name = "TEST")
@EntityListeners(value = {IdInjectionEntityListener.class})
public class TestCMP implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @IdSequenceConsumer(producerClass = TestSequenceProducer.class)
    private String uuid;
    @Size(max = 100)
    private String name;
    private Integer age;

    public TestCMP() {
    }

    public TestCMP(String uuid) {
        this.uuid = uuid;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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
        if (!(object instanceof TestCMP)) {
            return false;
        }
        TestCMP other = (TestCMP) object;
        if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.luosoy.frame.test.cmp.TestCMP[ uuid=" + uuid + " ]";
    }
    
}
