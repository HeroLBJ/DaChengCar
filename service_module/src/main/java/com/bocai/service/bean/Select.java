package com.bocai.service.bean;

import java.io.Serializable;

/**
 * @author libingjun
 * @date 2019/4/23
 */
public class Select implements Serializable {
    private String id;
    private String price;
    private String title;
    private String partsNumber;
    private String carTitle;
    private String units;

    public Select() {
    }

    @Override
    public String toString() {
        return "Select{" +
                "id='" + id + '\'' +
                ", price='" + price + '\'' +
                ", title='" + title + '\'' +
                ", partsNumber='" + partsNumber + '\'' +
                ", carTitle='" + carTitle + '\'' +
                ", units='" + units + '\'' +
                '}';
    }

    public Select(String id, String price, String title, String partsNumber, String carTitle, String units) {
        this.id = id;
        this.price = price;
        this.title = title;
        this.partsNumber = partsNumber;
        this.carTitle = carTitle;
        this.units = units;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPartsNumber() {
        return partsNumber;
    }

    public void setPartsNumber(String partsNumber) {
        this.partsNumber = partsNumber;
    }

    public String getCarTitle() {
        return carTitle;
    }

    public void setCarTitle(String carTitle) {
        this.carTitle = carTitle;
    }
}
