package com.bocai.service.bean;

import java.io.Serializable;

/**
 * @author libingjun
 * @version 1.0
 * @date 2019/4/25
 */
public class Second implements Serializable {
    private String id;
    private String title;
    private String type;
    private String storeType;

    public Second() {
    }

    public Second(String id, String title, String type, String storeType) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.storeType = storeType;
    }

    @Override
    public String toString() {
        return "Second{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", storeType='" + storeType + '\'' +
                '}';
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStoreType() {
        return storeType;
    }

    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }
}
