/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luosoy.test.controller;

import com.luosoy.test.facade.TestFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author luozp
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestFacade testFacade;

    @RequestMapping(value = "/test")
    public String test() {
        return "test/test";
    }

}
