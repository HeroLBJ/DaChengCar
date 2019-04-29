package com.bocweb.mine.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author libingjun
 * @version 1.0
 * @date 2019/4/28
 */
public class SuperMineBean<T> implements Serializable {
    private int count;
    private List<T> list;

    public SuperMineBean() {
    }

    public SuperMineBean(int count, List<T> list) {
        this.count = count;
        this.list = list;
    }

    @Override
    public String toString() {
        return "SuperMineBean{" +
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
