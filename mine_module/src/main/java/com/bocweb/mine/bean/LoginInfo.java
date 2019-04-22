package com.bocweb.mine.bean;

import java.io.Serializable;

/**
 * @author libingjun
 * @date 2019/4/22
 */
public class LoginInfo implements Serializable {
    private String token;
    private String integral;

    public LoginInfo() {
    }

    public LoginInfo(String token, String integral) {
        this.token = token;
        this.integral = integral;
    }

    @Override
    public String toString() {
        return "Register{" +
                "token='" + token + '\'' +
                ", integral='" + integral + '\'' +
                '}';
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }
}
