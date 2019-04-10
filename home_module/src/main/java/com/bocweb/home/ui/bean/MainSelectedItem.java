package com.bocweb.home.ui.bean;

import java.io.Serializable;

/**
 * @author libingjun
 * @date 2019/4/8
 */
public class MainSelectedItem implements Serializable {
    private String id;
    private String targetId;
    private String targetType;
    private String sortId;
    private TargetInfo targetInfo;

    @Override
    public String toString() {
        return "MainSelectedItem{" +
                "id='" + id + '\'' +
                ", targetId='" + targetId + '\'' +
                ", targetType='" + targetType + '\'' +
                ", sortId='" + sortId + '\'' +
                ", targetInfo=" + targetInfo +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public String getSortId() {
        return sortId;
    }

    public void setSortId(String sortId) {
        this.sortId = sortId;
    }

    public TargetInfo getTargetInfo() {
        return targetInfo;
    }

    public void setTargetInfo(TargetInfo targetInfo) {
        this.targetInfo = targetInfo;
    }
}
