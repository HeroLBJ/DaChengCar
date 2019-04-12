package com.bocweb.home.ui.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author libingjun
 * @date 2019/4/11
 */
public class ActivityList implements Serializable {
    private int count;
    private List<ActivityListItem> list;

    public ActivityList() {
    }

    public ActivityList(int count, List<ActivityListItem> list) {
        this.count = count;
        this.list = list;
    }

    @Override
    public String toString() {
        return "ActivityList{" +
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

    public List<ActivityListItem> getList() {
        return list;
    }

    public void setList(List<ActivityListItem> list) {
        this.list = list;
    }
}
