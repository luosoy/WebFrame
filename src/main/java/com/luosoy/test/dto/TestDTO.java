/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luosoy.test.dto;

import java.io.Serializable;

/**
 *
 * @author luozp
 */
public class TestDTO implements Serializable{

    private static final long serialVersionUID = 9129250108440394430L;

    private String name;
    private Integer age;

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
    public String toString() {
        return "TestDTO{" + "name=" + name + ", age=" + age + '}';
    }
    
    
}
