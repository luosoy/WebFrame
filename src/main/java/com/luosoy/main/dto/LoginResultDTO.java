/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luosoy.main.dto;

import java.io.Serializable;

/**
 *
 * @author lenovo
 */
public class LoginResultDTO implements Serializable {

    private static final long serialVersionUID = 3321516402995468091L;

    private String errMsg;
    private String successUrl;
    private boolean success;

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getSuccessUrl() {
        return successUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "LoginResultDTO{" + "errInfo=" + errMsg + ", successUrl=" + successUrl + ", success=" + success + '}';
    }

}
