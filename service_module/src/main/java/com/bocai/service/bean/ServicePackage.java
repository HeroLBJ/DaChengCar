package com.bocai.service.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author libingjun
 * @date 2019/4/22
 */
public class ServicePackage implements Serializable {
    /**
     * "id": "1",
     * 			"title": "首保",
     * 			"photo": "",
     * 			"price": "0.00",
     * 			"type": "1",
     * 			"details": [{
     * 				"id": "2",
     * 				"title": "机滤",
     * 				"price": "0.00"
     *                        }, {
     * 				"id": "1",
     * 				"title": "机油",
     * 				"price": "0.00"
     *            }]* 		}]
     */
    private String id;
    private String title;
    private String photo;
    private String price;
    private String type;
    private List<ServicePackageDetail> details;

    public ServicePackage() {
    }

    public ServicePackage(String id, String title, String photo, String price, String type, List<ServicePackageDetail> details) {
        this.id = id;
        this.title = title;
        this.photo = photo;
        this.price = price;
        this.type = type;
        this.details = details;
    }

    @Override
    public String toString() {
        return "ServicePackage{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", photo='" + photo + '\'' +
                ", price='" + price + '\'' +
                ", type='" + type + '\'' +
                ", details=" + details +
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ServicePackageDetail> getDetails() {
        return details;
    }

    public void setDetails(List<ServicePackageDetail> details) {
        this.details = details;
    }
}
