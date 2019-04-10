package com.njh.common.utils.common;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * @author libingjun
 * @date 2019/4/10
 */
public final class ScreenUtil {

    private ScreenUtil(){}

    public static int getScreenWdith(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }
}
