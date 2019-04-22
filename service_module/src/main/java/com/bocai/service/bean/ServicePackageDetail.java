package com.bocai.service.bean;

import java.io.Serializable;

/**
 * @author libingjun
 * @date 2019/4/22
 */
public class ServicePackageDetail implements Serializable {
    /**
     * "id": "1",
     * * 				"title": "机油",
     * * 				"price": "0.00"
     */
    private String id;
    private String title;
    private String price;

    public ServicePackageDetail() {
    }

    public ServicePackageDetail(String id, String title, String price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    @Override
    public String toString() {
        return "ServicePackageDetail{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", price='" + price + '\'' +
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
