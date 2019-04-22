package com.bocai.service.bean;

import java.io.Serializable;

/**
 * @author libingjun
 * @date 2019/4/22
 */
public class ServiceDealers implements Serializable {
    /**
     * "id": "5500",
     * "title": "杭州测试经销商",
     * "fullTitle": "杭州测试经销商",
     * "address": "浙江省杭州市莫干山路1758号",
     * "telphone": "0571-88781688",
     * "lng": "1200868239923808",
     * "lat": "3036544436307472",
     * "star": "0",
     * "distance": "5899.6km"
     */
    private String id;
    private String title;
    private String fullTitle;
    private String address;
    private String telphone;
    private String lng;
    private String lat;
    private String star;
    private String distance;

    public ServiceDealers() {
    }

    public ServiceDealers(String id, String title, String fullTitle, String address, String telphone, String lng, String lat, String star, String distance) {
        this.id = id;
        this.title = title;
        this.fullTitle = fullTitle;
        this.address = address;
        this.telphone = telphone;
        this.lng = lng;
        this.lat = lat;
        this.star = star;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "ServiceDealers{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", fullTitle='" + fullTitle + '\'' +
                ", address='" + address + '\'' +
                ", telphone='" + telphone + '\'' +
                ", lng='" + lng + '\'' +
                ", lat='" + lat + '\'' +
                ", star='" + star + '\'' +
                ", distance='" + distance + '\'' +
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

    public String getFullTitle() {
        return fullTitle;
    }

    public void setFullTitle(String fullTitle) {
        this.fullTitle = fullTitle;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
