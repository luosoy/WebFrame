/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.luosoy.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <pre>类名: MainController</pre>
 * <pre>描述: TODO</pre>
 * <pre>版权: 税友软件集团股份有限公司</pre>
 * <pre>日期: 2016-12-15 下午05:25:43 </pre>
 *
 * @author 罗真朋
 * @version 1.0
 */
@Controller
@RequestMapping("/main")
public class MainController {
    
    @RequestMapping("/index")
    private String index(){
        return "main/index";
    }

}
