package com.njh.common;

import com.njh.base.app.BaseApp;
import com.njh.common.db.manager.GreenDaoManager;
import com.orhanobut.hawk.Hawk;

/**
 * @author niejiahuan
 */
public class CommonApp extends BaseApp{

    @Override
    public void onCreate() {
        super.onCreate();
        //数据库初始化
        GreenDaoManager.getInstance().init(this);
        Hawk.init(this).build();
        setInitUIStutas();
    }

    public void setInitUIStutas(){
//        LoadingLayout.getConfig()
//                .setErrorText("出错啦~请稍后重试！")
//                .setEmptyText("暂无数据")
//                .setNoNetworkText("无网络连接，请检查您的网络···");
    }
}
