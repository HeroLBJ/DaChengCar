package com.bocai.service.bean;

import java.io.Serializable;

/**
 * @author libingjun
 * @version 1.0
 * @date 2019/4/25
 */
public class FeedbackCity implements Serializable {

    //"id": "110100","parentId": "110000","fullName": "北京市
    private String id;
    private String parentId;
    private String fullName;

    public FeedbackCity() {
    }

    public FeedbackCity(String id, String parentId, String fullName) {
        this.id = id;
        this.parentId = parentId;
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "FeedbackCity{" +
                "id='" + id + '\'' +
                ", parentId='" + parentId + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
