/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luosoy.main.service;

import com.luosoy.frame.utils.CipherUtil;
import com.luosoy.frame.exception.BusinessException;
import com.luosoy.main.cmp.QxUserCMP;
import com.luosoy.main.dto.QxUserRegisterDTO;
import com.luosoy.main.repository.QxUserRepository;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 罗真朋
 * @version 1.0
 */
@Service
public class QxUserService {

    @Autowired
    private QxUserRepository qxUserRepository;

    public void save(QxUserRegisterDTO qxUser) {
        String salt = CipherUtil.createSalt();
        QxUserCMP qxUserCMP = new QxUserCMP();
        qxUserCMP.setLoginname(qxUser.getLoginname());
        qxUserCMP.setName(qxUser.getName());
        qxUserCMP.setEmail(qxUser.getEmail());
        qxUserCMP.setSalt(salt);
        qxUserCMP.setPassword(CipherUtil.createPwdEncrypt(qxUser.getLoginname(), qxUser.getPassword(), salt));
        qxUserCMP.setDescription(qxUser.getDescription());
        qxUserCMP.setLrrq(new Date());
        qxUserCMP.setXgrq(new Date());
        qxUserCMP.setYxbz('Y');
        qxUserRepository.save(qxUserCMP);
    }

    public void updatePassword(QxUserRegisterDTO qxUser) {
        QxUserCMP qxUserCMP = findByLoginname(qxUser.getLoginname());
        if (qxUserCMP != null) {
            if (qxUserCMP.getYxbz().equals('Y')) {
                String pwdEncrypt = CipherUtil.createPwdEncrypt(qxUser.getLoginname(), qxUser.getOldpassword(), qxUserCMP.getSalt());
                if (qxUserCMP.getPassword().equals(pwdEncrypt)) {
                    if (qxUser.getPassword() != null && qxUser.getPassword().equals(qxUser.getPassword2())) {
                        String salt = CipherUtil.createSalt();
                        qxUserCMP.setSalt(salt);
                        qxUserCMP.setPassword(CipherUtil.createPwdEncrypt(qxUserCMP.getLoginname(), qxUser.getPassword(), salt));
                        qxUserCMP.setXgrq(new Date());
                        qxUserRepository.save(qxUserCMP);
                    } else {
                        throw new BusinessException("两次填写的新密码不相同");
                    }
                } else {
                    throw new BusinessException("密码错误");
                }
            } else {
                throw new BusinessException("用户处于无效状态，不能修改密码");
            }
        } else {
            throw new BusinessException("用户不存在，请联系管理员");
        }
    }

    public void update(QxUserRegisterDTO qxUser) {
        QxUserCMP qxUserCMP = findByLoginname(qxUser.getLoginname());
        if (qxUserCMP != null) {
            qxUserCMP.setName(qxUser.getName());
            qxUserCMP.setEmail(qxUser.getEmail());
            qxUserCMP.setDescription(qxUser.getDescription());
            qxUserCMP.setXgrq(new Date());
            qxUserRepository.save(qxUserCMP);
        } else {
            throw new BusinessException("用户不存在，请联系管理员");
        }
    }

    private QxUserCMP findByLoginname(String loginName) {
        return qxUserRepository.findByLoginname(loginName);
    }

}
