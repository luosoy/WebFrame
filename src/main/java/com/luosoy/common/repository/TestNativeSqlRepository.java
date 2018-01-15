/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luosoy.common.repository;

import com.luosoy.frame.jpa.AbstractNativeSqlRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 * <pre>类名: TestNativeSqlRepository</pre>
 * <pre>描述: TODO</pre>
 * <pre>版权: 税友软件集团股份有限公司</pre>
 * <pre>日期: 2018-01-15 上午10:31:58 </pre>
 *
 * @author luozp
 * @version 1.0
 */
@Repository
public class TestNativeSqlRepository extends AbstractNativeSqlRepository {

    @PersistenceContext(unitName = "test-db")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

}
