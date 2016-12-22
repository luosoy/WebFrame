/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luosoy.main.controller;

import com.luosoy.main.facade.QxUserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author lenovo
 */
@Controller
public class QxUserController {

    @Autowired
    private QxUserFacade qxUserFacade;

}
