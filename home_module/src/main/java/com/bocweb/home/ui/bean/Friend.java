package com.bocweb.home.ui.bean;

import java.io.Serializable;

/**
 * @author libingjun
 * @date 2019/4/14
 */
public class Friend implements Serializable {
    private String  accountId;
    private String nickname;
    private String avatar;
    private String sightml;
    private String isFollow;

    public Friend() {
    }

    public Friend(String accountId, String nickname, String avatar, String sightml, String isFollow) {
        this.accountId = accountId;
        this.nickname = nickname;
        this.avatar = avatar;
        this.sightml = sightml;
        this.isFollow = isFollow;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "accountId='" + accountId + '\'' +
                ", nickname='" + nickname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", sightml='" + sightml + '\'' +
                ", isFollow='" + isFollow + '\'' +
                '}';
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
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

    public String getSightml() {
        return sightml;
    }

    public void setSightml(String sightml) {
        this.sightml = sightml;
    }

    public String getIsFollow() {
        return isFollow;
    }

    public void setIsFollow(String isFollow) {
        this.isFollow = isFollow;
    }
}
