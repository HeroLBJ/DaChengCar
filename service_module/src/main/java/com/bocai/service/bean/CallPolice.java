package com.bocai.service.bean;

import java.io.Serializable;

/**
 * @author libingjun
 * @date 2019/4/23
 */
public class CallPolice implements Serializable {
    private String id;
    private String title;
    private String telphone;

    @Override
    public String toString() {
        return "CallPolice{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", telphone='" + telphone + '\'' +
                '}';
    }

    public CallPolice(String id, String title, String telphone) {
        this.id = id;
        this.title = title;
        this.telphone = telphone;
    }

    public CallPolice() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }
}
