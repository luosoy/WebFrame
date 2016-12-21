/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luosoy.main.repository;

import com.luosoy.main.cmp.QxUserCMP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * <pre>类名: QxUserRepository</pre>
 * <pre>描述: TODO</pre>
 * <pre>版权: 税友软件集团股份有限公司</pre>
 * <pre>日期: 2016-12-21 下午02:21:44 </pre>
 *
 * @author 罗真朋
 * @version 1.0
 */
public interface QxUserRepository extends JpaRepository<QxUserCMP, String>, JpaSpecificationExecutor<QxUserCMP> {

    public QxUserCMP findByLoginname(String loginname);

}
