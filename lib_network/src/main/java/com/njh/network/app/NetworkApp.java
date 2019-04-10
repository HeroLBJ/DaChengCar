package com.njh.network.app;

import android.app.Application;
import android.content.Context;

import com.njh.base.app.modeule.IAppLife;
import com.njh.network.BuildConfig;
import com.njh.network.api.ServiceManager;
import com.njh.network.utils.TokenManager;

import me.jessyan.retrofiturlmanager.RetrofitUrlManager;

/**
 * @author niejiahuan
 */
public class NetworkApp implements IAppLife {

    @Override
    public void attachBaseContext(Context base) {

    }

    @Override
    public void onCreate(Application application) {
        RetrofitUrlManager.getInstance().setDebug(BuildConfig.DEBUG);
        ServiceManager.initServiceManager(application);
        TokenManager.getInstance().initOnApplicationCreate(application);
    }

    @Override
    public void onTerminate(Application application) {

    }
}
