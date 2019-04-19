/**
  * Copyright 2019 bejson.com 
  */
package com.njh.common.city;
import java.util.List;

/**
 * Auto-generated: 2019-04-14 9:38:57
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class City {



    private String name;
    private String code;
    private List<Area> area;

    public City() {
    }

    public City(String name, String code, List<Area> area) {
        this.name = name;
        this.code = code;
        this.area = area;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", area=" + area +
                '}';
    }

    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

    public void setCode(String code) {
         this.code = code;
     }
     public String getCode() {
         return code;
     }

    public void setArea(List<Area> area) {
         this.area = area;
     }
     public List<Area> getArea() {
         return area;
     }

}