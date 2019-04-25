package com.bocweb.mine.bean;

import java.io.Serializable;

/**
 * @author libingjun
 * @version 1.0
 * @date 2019/4/25
 */
public class MemberCenter implements Serializable {
    private String id;
    private String nickname;
    private String avatar;
    private String wxxAvatarurl;
    //  0-保密 1-男 2-女
    private String gender;
    private String signtml;
    // 背景图片
    private String backImg;
    //  0:粉丝  1:白银会员 2:黄金会员 3:铂金会员 4:钻石会员
    private String level;
    // 发帖数
    private int publish;
    // 关注数
    private int focus;
    // 粉丝数
    private int fans;

    public MemberCenter() {
    }

    public MemberCenter(String id, String nickname, String avatar, String wxxAvatarurl, String gender, String signtml, String backImg, String level, int publish, int focus, int fans) {
        this.id = id;
        this.nickname = nickname;
        this.avatar = avatar;
        this.wxxAvatarurl = wxxAvatarurl;
        this.gender = gender;
        this.signtml = signtml;
        this.backImg = backImg;
        this.level = level;
        this.publish = publish;
        this.focus = focus;
        this.fans = fans;
    }

    @Override
    public String toString() {
        return "MemberCenter{" +
                "id='" + id + '\'' +
                ", nickname='" + nickname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", wxxAvatarurl='" + wxxAvatarurl + '\'' +
                ", gender='" + gender + '\'' +
                ", signtml='" + signtml + '\'' +
                ", backImg='" + backImg + '\'' +
                ", level='" + level + '\'' +
                ", publish=" + publish +
                ", focus=" + focus +
                ", fans=" + fans +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getWxxAvatarurl() {
        return wxxAvatarurl;
    }

    public void setWxxAvatarurl(String wxxAvatarurl) {
        this.wxxAvatarurl = wxxAvatarurl;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSigntml() {
        return signtml;
    }

    public void setSigntml(String signtml) {
        this.signtml = signtml;
    }

    public String getBackImg() {
        return backImg;
    }

    public void setBackImg(String backImg) {
        this.backImg = backImg;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getPublish() {
        return publish;
    }

    public void setPublish(int publish) {
        this.publish = publish;
    }

    public int getFocus() {
        return focus;
    }

    public void setFocus(int focus) {
        this.focus = focus;
    }

    public int getFans() {
        return fans;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }
}
