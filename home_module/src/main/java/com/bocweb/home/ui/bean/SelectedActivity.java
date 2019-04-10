package com.bocweb.home.ui.bean;

import java.io.Serializable;

/**
 * @author libingjun
 * @date 2019/4/10
 */
public class SelectedActivity implements Serializable {
    private String id;
    private String title;
    private String applyStart;
    private String applyEnd;
    private String timelineStart;
    private String timelineEnd;
    private String cover;
    private String isclose;
    private String views;
    private String category;
    private String coverVal;
    private int statusVal;

    public SelectedActivity() {
    }

    public SelectedActivity(String id, String title, String applyStart, String applyEnd, String timelineStart, String timelineEnd, String cover, String isclose, String views, String category, String coverVal, int statusVal) {
        this.id = id;
        this.title = title;
        this.applyStart = applyStart;
        this.applyEnd = applyEnd;
        this.timelineStart = timelineStart;
        this.timelineEnd = timelineEnd;
        this.cover = cover;
        this.isclose = isclose;
        this.views = views;
        this.category = category;
        this.coverVal = coverVal;
        this.statusVal = statusVal;
    }

    @Override
    public String toString() {
        return "SelectedActivity{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", applyStart='" + applyStart + '\'' +
                ", applyEnd='" + applyEnd + '\'' +
                ", timelineStart='" + timelineStart + '\'' +
                ", timelineEnd='" + timelineEnd + '\'' +
                ", cover='" + cover + '\'' +
                ", isclose='" + isclose + '\'' +
                ", views='" + views + '\'' +
                ", category='" + category + '\'' +
                ", coverVal='" + coverVal + '\'' +
                ", statusVal=" + statusVal +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getApplyStart() {
        return applyStart;
    }

    public void setApplyStart(String applyStart) {
        this.applyStart = applyStart;
    }

    public String getApplyEnd() {
        return applyEnd;
    }

    public void setApplyEnd(String applyEnd) {
        this.applyEnd = applyEnd;
    }

    public String getTimelineStart() {
        return timelineStart;
    }

    public void setTimelineStart(String timelineStart) {
        this.timelineStart = timelineStart;
    }

    public String getTimelineEnd() {
        return timelineEnd;
    }

    public void setTimelineEnd(String timelineEnd) {
        this.timelineEnd = timelineEnd;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getIsclose() {
        return isclose;
    }

    public void setIsclose(String isclose) {
        this.isclose = isclose;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCoverVal() {
        return coverVal;
    }

    public void setCoverVal(String coverVal) {
        this.coverVal = coverVal;
    }

    public int getStatusVal() {
        return statusVal;
    }

    public void setStatusVal(int statusVal) {
        this.statusVal = statusVal;
    }
}
