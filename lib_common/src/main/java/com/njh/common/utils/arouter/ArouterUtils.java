/*
 * Copyright 2018 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.njh.common.utils.arouter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;

/**
 * @author niejiahuan
 * Arouter 工具类
 */
public class ArouterUtils {
    private static ArouterUtils arouterUtils;
    private ArouterUtils(){}
    public static synchronized ArouterUtils getInstance(){
        if (arouterUtils == null) {
            arouterUtils = new ArouterUtils();
        }
        return arouterUtils;
    }

    /**
    /**
     * 使用 {@link ARouter} 根据 {@code path} 跳转到对应的页面, 如果参数 {@code context} 传入的不是 {@link Activity}
     * {@link ARouter} 就会自动给 {@link android.content.Intent} 加上 Intent.FLAG_ACTIVITY_NEW_TASK
     * 如果不想自动加上这个 Flag 请使用 {@link Activity} 作为 {@code context} 传入
     *
     * @param context
     * @param path
     */
    public  void navigation(Context context, String path) {
        ARouter.getInstance().build(path).navigation(context, new NavigationCallback() {
            @Override
            public void onFound(Postcard postcard) {
                Log.e("onFound",postcard.toString());
            }

            @Override
            public void onLost(Postcard postcard) {
                Log.e("onLost",postcard.toString());
            }

            @Override
            public void onArrival(Postcard postcard) {
                Log.e("onArrival",postcard.toString());
            }

            @Override
            public void onInterrupt(Postcard postcard) {
                Log.e("onArrival",postcard.toString());
            }
        });
    }
    public Postcard navigationAdd(String path) {
       return ARouter.getInstance().build(path);
    }
}
