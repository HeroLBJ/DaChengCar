package com.bocweb.mine.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author libingjun
 * @date 2019/4/8
 */
public class TargetInfo implements Serializable {
    private String iszan;
    private String collection;
    private String description;
    private String timeline;
    private String likes;
    private String id;
    private String accountId;
    private String views;
    private List<String> photoArr;
    private String replies;
    private String iscollect;
    private String photo;
    private UserMsg userInfo;
    private String title;
    private String cover;
    private String coverVal;
    private int count;

    public UserMsg getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserMsg userInfo) {
        this.userInfo = userInfo;
    }

    public TargetInfo() {
    }

    @Override
    public String toString() {
        return "TargetInfo{" +
                "iszan='" + iszan + '\'' +
                ", collection='" + collection + '\'' +
                ", description='" + description + '\'' +
                ", timeline='" + timeline + '\'' +
                ", likes='" + likes + '\'' +
                ", id='" + id + '\'' +
                ", accountId='" + accountId + '\'' +
                ", views='" + views + '\'' +
                ", photoArr=" + photoArr +
                ", replies='" + replies + '\'' +
                ", iscollect='" + iscollect + '\'' +
                ", photo='" + photo + '\'' +
                ", userInfo=" + userInfo +
                ", title='" + title + '\'' +
                ", cover='" + cover + '\'' +
                ", coverVal='" + coverVal + '\'' +
                ", count=" + count +
                '}';
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

    public String getCoverVal() {
        return coverVal;
    }

    public void setCoverVal(String coverVal) {
        this.coverVal = coverVal;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getIszan() {
        return iszan;
    }

    public void setIszan(String iszan) {
        this.iszan = iszan;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getReplies() {
        return replies;
    }

    public void setReplies(String replies) {
        this.replies = replies;
    }

    public String getIscollect() {
        return iscollect;
    }

    public void setIscollect(String iscollect) {
        this.iscollect = iscollect;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public List<String> getPhotoArr() {
        return photoArr;
    }

    public void setPhotoArr(List<String> photoArr) {
        this.photoArr = photoArr;
    }
}
