/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luosoy.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author 罗真朋
 * @version 1.0
 */
@Controller
@RequestMapping("/main")
public class MainController {

    @RequestMapping("/index")
    private String index() {
        return "main/index";
    }

    @RequestMapping("/login")
    private String login() {
        return "main/login";
    }

    @RequestMapping(value = "/dologin", method = RequestMethod.POST)
    private String dologin() {
        return "redirect:/web/main/index";
    }

    @RequestMapping(value = "/exitlogin", method = RequestMethod.POST)
    private String exitlogin() {
        return "redirect:/web/main/login";
    }

}
