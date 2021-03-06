package com.luosoy.test.repository;

import com.luosoy.test.cmp.TestCMP;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * <pre>类名: TestRepository</pre>
 * <pre>描述: TestRepository</pre>
 * <pre>版权: 税友软件集团股份有限公司</pre> Created by zhoudb on 2016/6/15.
 */
public interface TestRepository extends JpaRepository<TestCMP, String>, JpaSpecificationExecutor<TestCMP> {
    @Query(value = "select t from TestCMP t order by t.uuid asc")
    public Page<TestCMP> findTest(Pageable page);
}
