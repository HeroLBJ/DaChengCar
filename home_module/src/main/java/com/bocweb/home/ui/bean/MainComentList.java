package com.bocweb.home.ui.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author libingjun
 * @date 2019/4/10
 */
public class MainComentList implements Serializable {
    private List<TargetInfo> list;
    private int count;

    public MainComentList() {
    }

    public MainComentList(List<TargetInfo> list, int count) {
        this.list = list;
        this.count = count;
    }

    @Override
    public String toString() {
        return "MainComentList{" +
                "list=" + list +
                ", count=" + count +
                '}';
    }

    public List<TargetInfo> getList() {
        return list;
    }

    public void setList(List<TargetInfo> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
