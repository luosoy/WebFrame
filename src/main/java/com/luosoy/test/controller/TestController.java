/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luosoy.test.controller;

import com.luosoy.frame.web.Response;
import com.luosoy.frame.web.page.PageableDto;
import com.luosoy.test.dto.TestDTO;
import com.luosoy.test.dto.TestPageDTO;
import com.luosoy.test.facade.TestFacade;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping(value = "/findTest", method = RequestMethod.POST)
    @ResponseBody
    public Response<TestPageDTO> find(@RequestBody PageableDto pageable) {
        return Response.success(testFacade.find(pageable));
    }

}
