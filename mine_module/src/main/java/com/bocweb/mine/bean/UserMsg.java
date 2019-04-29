package com.bocweb.mine.bean;

import java.io.Serializable;

/**
 * @author libingjun
 * @date 2019/4/9
 */
public class UserMsg implements Serializable {
    private int isFollow;
    private String nickname;
    private String avatar;

    @Override
    public String toString() {
        return "UserMsg{" +
                "isFollow=" + isFollow +
                ", nickname='" + nickname + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }

    public UserMsg() {
    }

    public UserMsg(int isFollow, String nickname, String avatar) {
        this.isFollow = isFollow;
        this.nickname = nickname;
        this.avatar = avatar;
    }

    public int getIsFollow() {
        return isFollow;
    }

    public void setIsFollow(int isFollow) {
        this.isFollow = isFollow;
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
}
