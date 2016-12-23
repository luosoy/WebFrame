/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luosoy.main.facade;

import com.luosoy.main.dto.QxUserRegisterDTO;
import com.luosoy.main.service.QxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author lenovo
 */
@Service
public class QxUserFacade {

    @Autowired
    private QxUserService qxUserService;

    @Transactional(value = "mainTransactionManager")
    public void save(QxUserRegisterDTO qxUser) {
        qxUserService.save(qxUser);
    }

    @Transactional(value = "mainTransactionManager")
    public void updatePassword(QxUserRegisterDTO qxUser) {
        qxUserService.updatePassword(qxUser);
    }

    @Transactional(value = "mainTransactionManager")
    public void update(QxUserRegisterDTO qxUser) {
        qxUserService.update(qxUser);
    }
}
