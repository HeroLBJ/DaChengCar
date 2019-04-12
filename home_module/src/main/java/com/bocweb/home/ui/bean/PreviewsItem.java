package com.bocweb.home.ui.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author libingjun
 * @date 2019/4/11
 */
public class PreviewsItem implements Serializable {
    private List<Previews> list;
    private int count;

    public PreviewsItem() {
    }

    public PreviewsItem(List<Previews> list, int count) {
        this.list = list;
        this.count = count;
    }

    @Override
    public String toString() {
        return "PreviewsItem{" +
                "list=" + list +
                ", count=" + count +
                '}';
    }

    public List<Previews> getList() {
        return list;
    }

    public void setList(List<Previews> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
