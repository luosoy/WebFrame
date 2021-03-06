/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luosoy.test.facade;

import com.luosoy.frame.web.page.PageableDto;
import com.luosoy.test.dto.TestDTO;
import com.luosoy.test.dto.TestPageDTO;
import com.luosoy.test.service.TestService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author luozp
 */
@Service
public class TestFacade {

    @Autowired
    private TestService ts;

    @Transactional(value = "testTransactionManager")
    public void save(TestDTO tdto) {
        ts.save(tdto);
    }
    
    @Transactional(value = "testTransactionManager")
    public void save(List<TestDTO> tdto) {
        ts.save(tdto);
    }
    
    public TestPageDTO find(PageableDto pageable){
        return ts.find(pageable);
    }
}
