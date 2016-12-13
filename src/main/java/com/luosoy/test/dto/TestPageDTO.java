/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luosoy.test.dto;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author lenovo
 */
public class TestPageDTO implements Serializable{

    private static final long serialVersionUID = 5018680422390683521L;
    
    private long totalCount;
    private List<TestDTO> data;
    
    public TestPageDTO(){
        
    }

    public TestPageDTO(long totalCount, List<TestDTO> data) {
        this.totalCount = totalCount;
        this.data = data;
    }
    
    

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public List<TestDTO> getData() {
        return data;
    }

    public void setData(List<TestDTO> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TestPageDTO{" + "totalCount=" + totalCount + ", data=" + data + '}';
    }
    
    
    
}
