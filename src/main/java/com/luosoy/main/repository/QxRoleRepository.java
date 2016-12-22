/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luosoy.main.repository;

import com.luosoy.main.cmp.QxRoleCMP;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author 罗真朋
 * @version 1.0
 */
public interface QxRoleRepository extends JpaRepository<QxRoleCMP, String>, JpaSpecificationExecutor<QxRoleCMP> {

    @Query(value = "select t from QxRoleCMP t,QxUserRoleCMP a where t.uuid = a.qxUserRoleCMPPK.roleUuid and a.qxUserRoleCMPPK.userUuid=:uuid and t.yxbz='Y'")
    public List<QxRoleCMP> findByUuid(@Param(value = "uuid") String uuid);

}
