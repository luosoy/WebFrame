/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luosoy.main.controller;

import com.luosoy.common.utils.Const;
import com.luosoy.common.utils.SessionUtil;
import com.luosoy.frame.web.Response;
import com.luosoy.main.dto.LoginDTO;
import com.luosoy.main.dto.LoginResultDTO;
import com.luosoy.main.dto.QxUserDTO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author 罗真朋
 * @version 1.0
 */
@Controller
@RequestMapping("/main")
public class MainController {

    @RequestMapping("/index")
    private String index(Model model) {
        model.addAttribute(Const.USERNAME, ((QxUserDTO)SessionUtil.getSession(Const.SESSION_USER)).getName());
        return "main/index";
    }

    @RequestMapping("/login")
    private String login() {
        return "main/login";
    }
    
    @RequestMapping("/unauthorized")
    private String unauthorized() {
        return "main/unauthorized";
    }

    @RequestMapping(value = "/dologin", method = RequestMethod.POST)
    @ResponseBody
    private Response<LoginResultDTO> dologin(@RequestBody LoginDTO loginDTO) {
        LoginResultDTO lrdto = new LoginResultDTO();
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(
                loginDTO.getLoginName(), loginDTO.getPassword(), loginDTO.isRemember());
        try {
            if (!currentUser.isAuthenticated()) {
                currentUser.login(token);
            }
        } catch (UnknownAccountException ex) {
            lrdto.setErrMsg("用户名或密码有误");
        } catch (IncorrectCredentialsException ex) {
            lrdto.setErrMsg("密码错误");
        } catch (LockedAccountException ex) {
            lrdto.setErrMsg("未激活");
        } catch (ExcessiveAttemptsException ex) {
            lrdto.setErrMsg("错误次数过多");
        } catch (AuthenticationException ex) {
            lrdto.setErrMsg("验证未通过");
        }
        // 验证是否登录成功
        if (!currentUser.isAuthenticated()) {
            token.clear();
            lrdto.setSuccess(false);
        }else{
            lrdto.setSuccess(true);
            lrdto.setSuccessUrl("main/index");
        }

        return Response.success(lrdto);
    }

    @RequestMapping(value = "/exitlogin", method = RequestMethod.POST)
    private String exitlogin() {
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            currentUser.logout();
        }
        return "redirect:/web/main/login";
    }

}
