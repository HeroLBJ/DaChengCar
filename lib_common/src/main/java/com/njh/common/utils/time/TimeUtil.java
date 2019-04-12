package com.njh.common.utils.time;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author libingjun
 * @date 2019/4/10
 */
public final class TimeUtil {

    private static final String PATTERN_DEFAULT = "yyyy.MM.dd HH:mm:ss";
    public static final String PATTERN_DATA = "yyyy.MM.dd";
    public static final String PATTERN_MONTH_DAY = "MM.dd";

    private TimeUtil() {
    }

    public static String stampToDate(String s) {
        if (TextUtils.isEmpty(s)) {
            return "2000.01.01 00:00:00";
        }
        return stampToDate(s, PATTERN_DEFAULT);
    }

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s, String pattern) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        long lt = new Long(s+"000");
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }


    public static String dateToStamp(String s) throws ParseException {
        return dateToStamp(s, PATTERN_DEFAULT);
    }

    /*
     * 将时间转换为时间戳
     */
    public static String dateToStamp(String s, String pattern) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = simpleDateFormat.parse(s+"000");
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }
}
