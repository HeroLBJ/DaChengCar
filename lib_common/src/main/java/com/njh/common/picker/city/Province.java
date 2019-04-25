package com.njh.common.picker.city;

import java.io.Serializable;
import java.util.List;

public class Province implements Serializable {

    private String AreaId;
    private String AreaName;
    private List<City> Cities;

    public Province() {
    }

    public Province(String areaId, String areaName, List<City> cities) {
        AreaId = areaId;
        AreaName = areaName;
        Cities = cities;
    }

    @Override
    public String toString() {
        return "Province{" +
                "AreaId='" + AreaId + '\'' +
                ", AreaName='" + AreaName + '\'' +
                ", Cities=" + Cities +
                '}';
    }

    public String getAreaId() {
        return AreaId;
    }

    public void setAreaId(String areaId) {
        AreaId = areaId;
    }

    public String getAreaName() {
        return AreaName;
    }

    public void setAreaName(String areaName) {
        AreaName = areaName;
    }

    public List<City> getCities() {
        return Cities;
    }

    public void setCities(List<City> cities) {
        Cities = cities;
    }
}