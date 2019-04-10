package com.njh.common.utils;

import android.util.Log;

/**
 * @author libingjun
 * @date 2019/4/9
 */
public class LogUtil {
    //规定每段显示的长度
    private static int LOG_MAX_LENGTH = 2000;
    private static String TAG = "#http#";

    public static void e(String msg) {
        int strLength = msg.length();
        int start = 0;
        int end = LOG_MAX_LENGTH;
        for (int i = 0; i < 100; i++) {
            //剩下的文本还是大于规定长度则继续重复截取并输出
            if (strLength > end) {
                Log.e(TAG + i, msg.substring(start, end));
                start = end;
                end = end + LOG_MAX_LENGTH;
            } else {
                Log.e(TAG, msg.substring(start, strLength));
                break;
            }
        }
    }
}
