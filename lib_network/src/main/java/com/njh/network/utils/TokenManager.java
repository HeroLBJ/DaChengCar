package com.njh.network.utils;

import android.content.Context;
import android.text.TextUtils;

import com.orhanobut.hawk.Hawk;


/**
 *Token 管理类
 * @author niejiahuan
 */
public class TokenManager {
    Context context;

    private String mToken;
    public static final String SP_TOKEN = "token";
    public static final  String SP_USER_INFO="UserInfo";
    public static final  String SP_MEBER_INFO="MeberInfo";
    public static final  String SP_VERSION_INFO="VersionInfo";
    private static TokenManager sUserInfoManager = new TokenManager();

    private TokenManager() {
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public static TokenManager getInstance() {
        return sUserInfoManager;
    }

    public void initOnApplicationCreate(Context context) {
        setContext(context);
        mToken = Hawk.get(SP_TOKEN,"");
    }


    public boolean isLogin(){
        boolean isLogin=false;
        if (mToken!=null&&!TextUtils.isEmpty(mToken)){
            isLogin=true;
        }
        return isLogin;
    }

    public String getToken() {
        return mToken;
    }

    public void setToken(String token,Context context) {
        mToken = token;
        Hawk.put(SP_TOKEN, token);
    }

    public void clearToken() {
        mToken =null;
        Hawk.delete(SP_TOKEN);
        Hawk.delete(SP_USER_INFO);
    }

}
