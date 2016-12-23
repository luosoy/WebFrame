/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luosoy.main.controller;

import com.luosoy.main.dto.QxUserRegisterDTO;
import com.luosoy.main.facade.QxUserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author lenovo
 */
@Controller
@RequestMapping("/user")
public class QxUserController {

    @Autowired
    private QxUserFacade qxUserFacade;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void save(QxUserRegisterDTO qxUser) {
        qxUserFacade.save(qxUser);
    }

    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public void updatePassword(QxUserRegisterDTO qxUser) {
        qxUserFacade.updatePassword(qxUser);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(QxUserRegisterDTO qxUser) {
        qxUserFacade.update(qxUser);
    }

}
