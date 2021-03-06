
package com.njh.common.picker.city;
import java.io.Serializable;
import java.util.List;

public class City implements Serializable {

    private String AreaId;
    private String AreaName;
    private List<County> Cities;

    public City() {
    }

    public City(String areaId, String areaName, List<County> cities) {
        AreaId = areaId;
        AreaName = areaName;
        Cities = cities;
    }

    @Override
    public String toString() {
        return "City{" +
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

    public List<County> getCities() {
        return Cities;
    }

    public void setCities(List<County> cities) {
        Cities = cities;
    }
}