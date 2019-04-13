package com.bocweb.home.ui.bean;

import java.io.Serializable;

/**
 * @author libingjun
 * @date 2019/4/11
 */
public class Previews implements Serializable {
    private String id;
    private String title;
    private String cover;
    private String timeline;
    private String views;
    private String collection;
    private String replies;
    private String likes;
    private String coverVal;
    private String iscollect;
    private String iszan;
    private int sign;

    public Previews() {
    }

    public Previews(String id, String title, String cover, String timeline, String views, String collection, String replies, String likes, String coverVal, String iscollect, String iszan) {
        this.id = id;
        this.title = title;
        this.cover = cover;
        this.timeline = timeline;
        this.views = views;
        this.collection = collection;
        this.replies = replies;
        this.likes = likes;
        this.coverVal = coverVal;
        this.iscollect = iscollect;
        this.iszan = iszan;
    }

    @Override
    public String toString() {
        return "Previews{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", cover='" + cover + '\'' +
                ", timeline='" + timeline + '\'' +
                ", views='" + views + '\'' +
                ", collection='" + collection + '\'' +
                ", replies='" + replies + '\'' +
                ", likes='" + likes + '\'' +
                ", coverVal='" + coverVal + '\'' +
                ", iscollect='" + iscollect + '\'' +
                ", iszan='" + iszan + '\'' +
                '}';
    }

    public int getSign() {
        return sign;
    }

    public void setSign(int sign) {
        this.sign = sign;
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

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getTimeline() {
        return timeline;
    }

    public void setTimeline(String timeline) {
        this.timeline = timeline;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String getReplies() {
        return replies;
    }

    public void setReplies(String replies) {
        this.replies = replies;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getCoverVal() {
        return coverVal;
    }

    public void setCoverVal(String coverVal) {
        this.coverVal = coverVal;
    }

    public String getIscollect() {
        return iscollect;
    }

    public void setIscollect(String iscollect) {
        this.iscollect = iscollect;
    }

    public String getIszan() {
        return iszan;
    }

    public void setIszan(String iszan) {
        this.iszan = iszan;
    }
}
