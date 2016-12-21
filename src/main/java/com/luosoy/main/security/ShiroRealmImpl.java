/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luosoy.main.security;

import com.luosoy.common.utils.Const;
import com.luosoy.frame.beans.BeanConvertUtil;
import com.luosoy.main.cmp.QxUserCMP;
import com.luosoy.main.dto.QxUserDTO;
import com.luosoy.main.repository.QxUserRepository;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 罗真朋
 * @version 1.0
 */
public class ShiroRealmImpl extends AuthorizingRealm {

    @Autowired
    private QxUserRepository userRepository;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String username = token.getUsername();//用户名
        String password = new String(token.getPassword());//密码
        QxUserCMP qxuser = userRepository.findByLoginname(username);
        if (qxuser != null) {
            QxUserDTO qudto = new QxUserDTO();
            BeanConvertUtil.convert(qxuser, qudto);
            //组合username,两次迭代，对密码进行加密 
            String pwdEncrypt = CipherUtil.createPwdEncrypt(username, password, qudto.getSalt());
            if (qudto.getPassword().equals(pwdEncrypt)) {
                AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(qudto.getLoginname(), password, getName());
                this.setSession(Const.SESSION_USER, qudto);
                return authenticationInfo;
            } else {
                throw new IncorrectCredentialsException();
            }
        } else {
            throw new UnknownAccountException();
        }
    }
    
    private void setSession(Object key, Object value) {
        Subject currentUser = SecurityUtils.getSubject();
        if (null != currentUser) {
            Session session = currentUser.getSession();
            if (null != session) {
                session.setAttribute(key, value);
            }
        }
    }

}
