package com.njh.common;

import android.util.Log;

import com.baidu.mapapi.SDKInitializer;
import com.njh.base.app.BaseApp;
import com.njh.common.db.manager.GreenDaoManager;
import com.njh.common.location.LocationService;
import com.orhanobut.hawk.Hawk;
import com.tencent.smtt.sdk.QbSdk;

/**
 * @author niejiahuan
 */
public class CommonApp extends BaseApp {

    public LocationService locationService;

    @Override
    public void onCreate() {
        super.onCreate();
        //数据库初始化
        GreenDaoManager.getInstance().init(this);
        Hawk.init(this).build();
        setInitUIStutas();
        initX5();
        initLocation();
    }

    private void initX5() {
        QbSdk.setDownloadWithoutWifi(true);
        //x5内核初始化接口//搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
        QbSdk.initX5Environment(getApplicationContext(), new QbSdk.PreInitCallback() {
            @Override
            public void onViewInitFinished(boolean arg0) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.d("app", " onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
            }
        });
    }

    private void initLocation() {
        locationService = new LocationService(getApplicationContext());
        SDKInitializer.initialize(getApplicationContext());
    }

    public void setInitUIStutas() {
//        LoadingLayout.getConfig()
//                .setErrorText("出错啦~请稍后重试！")
//                .setEmptyText("暂无数据")
//                .setNoNetworkText("无网络连接，请检查您的网络···");
    }
}
