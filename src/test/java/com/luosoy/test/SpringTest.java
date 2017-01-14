/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luosoy.test;

import com.luosoy.test.dto.TestDTO;
import com.luosoy.test.facade.TestFacade;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kubek2k.springockito.annotations.SpringockitoContextLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * <pre>类名: TestTjycl</pre>
 * <pre>描述: TODO</pre>
 * <pre>版权: 税友软件集团股份有限公司</pre>
 * <pre>日期: 2016年05月30日 上午10:47:02 </pre>
 *
 * @author 罗真朋
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = SpringockitoContextLoader.class, locations = {"classpath*:/spring/test-context.xml"})
public class SpringTest {

    @Autowired
    private TestFacade testFacade;

    @Test
    public void test() {
        List<TestDTO> lt = new ArrayList<TestDTO>();
        for (int i = 0; i < 1000; i++) {
            TestDTO testDTO = new TestDTO();
            testDTO.setAge(12 + i);
            testDTO.setName("test" + i);
            lt.add(testDTO);

        }
        
        //testFacade.save(lt);
        
        TestDTO testDTO = new TestDTO();
        testDTO.setAge(12 );
        testDTO.setName("test");
        
        testFacade.save(testDTO);
    }

}
