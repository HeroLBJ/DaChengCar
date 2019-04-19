package com.bocweb.dacheng.app;

import com.njh.common.CommonApp;

/**
 * @author niejiahuan
 */
public class App extends CommonApp {

    @Override
    public void onCreate() {
        super.onCreate();
        CollectLog.getInstance().init(this);
    }
}
