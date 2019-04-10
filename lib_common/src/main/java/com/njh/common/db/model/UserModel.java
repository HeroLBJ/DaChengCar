package com.njh.common.db.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class UserModel {
    private String userName;

    @Generated(hash = 200405722)
    public UserModel(String userName) {
        this.userName = userName;
    }

    @Generated(hash = 782181818)
    public UserModel() {
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
