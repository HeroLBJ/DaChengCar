/**
  * Copyright 2019 bejson.com 
  */
package com.bocweb.community.ui.fmt;

import java.io.Serializable;

/**
 * Auto-generated: 2019-04-14 9:38:57
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Area implements Serializable {

    public Area() {
    }

    @Override
    public String toString() {
        return "Area{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    public Area(String name, String code) {
        this.name = name;
        this.code = code;
    }

    private String name;
    private String code;
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

}