package com.bocai.service.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author libingjun
 * @date 2019/4/22
 */
public class SuperServiceBean<T> implements Serializable {
    private int count;
    private List<T> list;

    public SuperServiceBean() {
    }

    public SuperServiceBean(int count, List<T> list) {
        this.count = count;
        this.list = list;
    }

    @Override
    public String toString() {
        return "SuperServiceBean{" +
                "count=" + count +
                ", list=" + list +
                '}';
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
