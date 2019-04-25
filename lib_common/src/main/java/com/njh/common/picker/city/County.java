package com.njh.common.picker.city;

import java.io.Serializable;

public class County implements Serializable {

    private String AreaId;
    private String AreaName;

    public County() {
    }

    public County(String areaId, String areaName) {
        AreaId = areaId;
        AreaName = areaName;
    }

    @Override
    public String toString() {
        return "County{" +
                "AreaId='" + AreaId + '\'' +
                ", AreaName='" + AreaName + '\'' +
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
}