package com.bocweb.action.app;

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
