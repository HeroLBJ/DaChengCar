package com.bocai.service.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author libingjun
 * @date 2019/4/25
 */
public class FeedbackType implements Serializable {
    private String id;
    private String title;
    private String type;
    private String storeType;
    private List<Second> second;

    public FeedbackType() {
    }

    public FeedbackType(String id, String title, String type, String storeType, List<Second> second) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.storeType = storeType;
        this.second = second;
    }

    @Override
    public String toString() {
        return "FeedbackType{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", storeType='" + storeType + '\'' +
                ", second=" + second +
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

    public List<Second> getSecond() {
        return second;
    }

    public void setSecond(List<Second> second) {
        this.second = second;
    }
}
