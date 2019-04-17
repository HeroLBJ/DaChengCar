package com.bocweb.home.ui.bean;

import java.io.Serializable;

/**
 * @author libingjun
 * @date 2019/4/17
 */
public class StatusResponse implements Serializable {
    private int status;

    public StatusResponse() {
    }

    public StatusResponse(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "FollowResponse{" +
                "status=" + status +
                '}';
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
