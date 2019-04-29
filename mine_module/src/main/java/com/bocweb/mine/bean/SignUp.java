package com.bocweb.mine.bean;

import java.io.Serializable;

/**
 * @author libingjun
 * @version 1.0
 * @date 2019/4/29
 */
public class SignUp implements Serializable {
    private String forward;
    private String signDay;

    public String getForward() {
        return forward;
    }

    public void setForward(String forward) {
        this.forward = forward;
    }

    public String getSignDay() {
        return signDay;
    }

    public void setSignDay(String signDay) {
        this.signDay = signDay;
    }
}
