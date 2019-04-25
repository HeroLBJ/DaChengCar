package com.bocai.service.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author libingjun
 * @version 1.0
 * @date 2019/4/25
 */
public class FeedbackProvince implements Serializable {
    private String id;
    private String fullName;
    private List<FeedbackCity> city;

    public FeedbackProvince() {
    }

    public FeedbackProvince(String id, String fullName, List<FeedbackCity> city) {
        this.id = id;
        this.fullName = fullName;
        this.city = city;
    }

    @Override
    public String toString() {
        return "FeedbackProvince{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", city=" + city +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<FeedbackCity> getCity() {
        return city;
    }

    public void setCity(List<FeedbackCity> city) {
        this.city = city;
    }
}


































