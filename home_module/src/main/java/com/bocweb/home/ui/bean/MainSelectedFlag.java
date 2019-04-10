package com.bocweb.home.ui.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author libingjun
 * @date 2019/4/8
 */
public class MainSelectedFlag implements Serializable {
    private List<MainSelectedItem> list;
    private int count;

    public MainSelectedFlag() {
    }

    public MainSelectedFlag(List<MainSelectedItem> list, int count) {
        this.list = list;
        this.count = count;
    }

    @Override
    public String toString() {
        return "MainSelectedFlag{" +
                "list=" + list +
                ", count=" + count +
                '}';
    }

    public List<MainSelectedItem> getList() {
        return list;
    }

    public void setList(List<MainSelectedItem> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
