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
public class JsonRootBean {

    private String name;
    private String code;
    private List<City> city;

    public JsonRootBean() {
    }

    public JsonRootBean(String name, String code, List<City> city) {
        this.name = name;
        this.code = code;
        this.city = city;
    }

    @Override
    public String toString() {
        return "JsonRootBean{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", city=" + city +
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

    public void setCity(List<City> city) {
         this.city = city;
     }
     public List<City> getCity() {
         return city;
     }

}