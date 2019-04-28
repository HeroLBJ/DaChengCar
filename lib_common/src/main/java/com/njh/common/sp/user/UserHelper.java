package com.njh.common.sp.user;

import com.orhanobut.hawk.Hawk;

/**
 * @author libingjun
 * @version 1.0
 * @date 2019/4/28
 */
public class UserHelper {
    public static final String USER_INFO = "USER_INFO";

    public static UserInfo getUserInfo() {
        return Hawk.get(USER_INFO);
    }

    public static void putUserInfo(UserInfo userInfo) {
        Hawk.put(USER_INFO, userInfo);
    }

    public static String getUserAvatar() {
        return getUserInfo().getAvatar();
    }
}
