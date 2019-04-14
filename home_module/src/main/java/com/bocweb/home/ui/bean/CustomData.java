package com.bocweb.home.ui.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author libingjun
 * @date 2019/4/14
 */
public class CustomData<T> implements Serializable {
    private List<T> list;
    private int count;

    public CustomData() {
    }

    public CustomData(List<T> list, int count) {
        this.list = list;
        this.count = count;
    }

    @Override
    public String toString() {
        return "CustomData{" +
                "list=" + list +
                ", count=" + count +
                '}';
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
