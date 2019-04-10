package com.njh.network.api;

import android.content.Context;

import com.njh.network.utils.RetrofitUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xdj on 16/3/14.
 * 接口管理
 */
public class ServiceManager {
    static Context mContext;
    public static void initServiceManager(Context context){
        mContext=context;
    }
    private static final Map<Class, Object> mServiceMap = new HashMap<>();

    public static void clearMap(){
        mServiceMap.clear();
    }

    public static <T> T create(Class<T> serviceClass) {
        Object service = mServiceMap.get(serviceClass);
        if (service == null) {
            service = RetrofitUtils.get(mContext).retrofit().create(serviceClass);
            mServiceMap.put(serviceClass, service);
        }

        return (T) service;
    }
    public static <T> T defCreate(){
        return (T) create(ApiService.class);
    }
}
