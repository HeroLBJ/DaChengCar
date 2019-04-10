package com.bocweb.action.interceptor;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.njh.base.utils.ActivityUtil;

/**
 * @author niejiahuan
 */
@Interceptor(priority = 1, name = "登录拦截器拦截器")
public class LoginInterceptor implements IInterceptor {
    Context mContext;
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        if ("/test/activity4".equals(postcard.getPath())) {
            //添加路径判断
            // 这里的弹窗仅做举例，代码写法不具有可参考价值
            final AlertDialog.Builder ab = new AlertDialog.Builder(ActivityUtil
                    .getInstance().getCurrentActivity());
            ab.setCancelable(false);
            ab.setTitle("温馨提醒");
            ab.setMessage("想要跳转到Test4Activity么？(触发了\"/inter/test1\"拦截器，拦截了本次跳转)");
            ab.setNegativeButton("继续", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    callback.onContinue(postcard);
                }
            });
            ab.setNeutralButton("算了", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    callback.onInterrupt(null);
                }
            });
            ab.setPositiveButton("加点料", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    postcard.withString("extra", "我是在拦截器中附加的参数");
                    callback.onContinue(postcard);
                }
            });

            MainLooper.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ab.create().show();
                }
            });
        } else {
            callback.onContinue(postcard);
        }
    }

    @Override
    public void init(Context context) {
    // 拦截器的初始化，会在sdk初始化的时候调用该方法，仅会调用一次
        mContext=context;
    }
}