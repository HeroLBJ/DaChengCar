package com.njh.base.app;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.njh.base.BuildConfig;
import com.njh.base.app.modeule.IAppLife;
import com.socks.library.KLog;

import androidx.multidex.MultiDex;
import me.jessyan.autosize.AutoSize;
import me.jessyan.autosize.AutoSizeConfig;

/**
 * @author niejiahuan
 */
public class BaseApp extends Application implements IAppLife {

    private static BaseApp instance;
    public static BaseApp getAppInstance() {
        return instance;
    }

    public static void setInstance(BaseApp instance) {
        BaseApp.instance = instance;
    }

    private ApplicationDelegate applicationDelegate;

    /**
     * 初始化initARouter
     */
    public void initARouter(){
        if (BuildConfig.IS_DEBUG) {
            // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this);
    }

    /**
     * 初始化AutoSize
     */
    public void initAutoSize(){
        //在 Demo 中跳转的三方库中的 DefaultErrorActivity 就是在另外一个进程中, 所以要想适配这个 Activity 就需要调用 initCompatMultiProcess()
        AutoSize.initCompatMultiProcess(this);
        AutoSizeConfig.getInstance()
                .setLog(BuildConfig.IS_DEBUG)
                //如果为 false, 则会跟随系统设置中字体大小的改变, 默认为 false
                .setExcludeFontScale(false);
    }
    @Override
    public void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        applicationDelegate = new ApplicationDelegate(base);
        applicationDelegate.attachBaseContext(base);
        MultiDex.install(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        initARouter();
        initAutoSize();
        //日志初始化
        KLog.init(BuildConfig.IS_DEBUG);
    }

    @Override
    public void onCreate(Application application) {

    }


    @Override
    public void onTerminate(Application application) {
        applicationDelegate.onTerminate(this);
    }
}
