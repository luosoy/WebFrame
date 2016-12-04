/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luosoy.test.service;

import com.luosoy.frame.beans.BeanConvertUtil;
import com.luosoy.test.cmp.TestCMP;
import com.luosoy.test.dto.TestDTO;
import com.luosoy.test.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author luozp
 */
@Service
public class TestService {

    @Autowired
    private TestRepository repository;
    
    public void save(TestDTO tdto){
        TestCMP tcmp = BeanConvertUtil.convert(tdto, new TestCMP());
        repository.save(tcmp);
    }
}
