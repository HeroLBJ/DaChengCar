package com.bocweb.home.ui.bean;

import java.io.Serializable;

/**
 * @author libingjun
 * @date 2019/4/11
 */
public class ActivityPreviewsList implements Serializable {
    private PreviewsItem data;
    private PreviewsItem flag;

    public ActivityPreviewsList() {
    }

    public ActivityPreviewsList(PreviewsItem data, PreviewsItem flag) {
        this.data = data;
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "ActivityPreviewsList{" +
                "data=" + data +
                ", flag=" + flag +
                '}';
    }

    public PreviewsItem getData() {
        return data;
    }

    public void setData(PreviewsItem data) {
        this.data = data;
    }

    public PreviewsItem getFlag() {
        return flag;
    }

    public void setFlag(PreviewsItem flag) {
        this.flag = flag;
    }
}
