/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luosoy.test.service;

import com.luosoy.frame.beans.BeanConvertUtil;
import com.luosoy.frame.web.page.PageableDto;
import com.luosoy.test.cmp.TestCMP;
import com.luosoy.test.dto.TestDTO;
import com.luosoy.test.dto.TestPageDTO;
import com.luosoy.test.repository.TestRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    
    public void save(List<TestDTO> tdto){
        List<TestCMP> lt = BeanConvertUtil.convertList(TestDTO.class, TestCMP.class, tdto);
        repository.save(lt);
    }
    
    public TestPageDTO find(PageableDto pageable){
        Page<TestCMP> ps = repository.findTest(pageable.buildPageable());
        List<TestDTO> dTOs = BeanConvertUtil.convertList(TestCMP.class, TestDTO.class, ps.getContent());
        TestPageDTO pageDTO = new TestPageDTO(ps.getTotalElements(),dTOs);
        return pageDTO;
    }
}
