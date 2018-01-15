/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luosoy.test;

import com.luosoy.frame.jpa.NativeSqlRepository;
import com.luosoy.frame.jpa.search.SqlPage;
import com.luosoy.test.dto.TestDTO;
import java.util.HashMap;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kubek2k.springockito.annotations.SpringockitoContextLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
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
    @Qualifier(value = "testNativeSqlRepository")
    private NativeSqlRepository nativeSqlRepository;

    @Test
    public void test() {
        SqlPage sqlPage = new SqlPage();
        sqlPage.setPageNumber(1);
        sqlPage.setPageSize(10);
        Page<TestDTO> page = nativeSqlRepository.executeNamedQueryPageSql("queryTest", new HashMap<String, Object>(), sqlPage, TestDTO.class);
        System.out.println(page);
    }

}
