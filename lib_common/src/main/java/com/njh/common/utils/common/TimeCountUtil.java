package com.njh.common.utils.common;

import android.annotation.SuppressLint;
import android.os.CountDownTimer;

/**
 * @author niejiahuan
 * 计时器
 */
public class TimeCountUtil extends CountDownTimer {
    OnTimeCountListenerUtil onTimeCountListenerUtil;
    public OnTimeCountListenerUtil getOnTimeCountListenerUtil() {
        return onTimeCountListenerUtil;
    }
    public void setOnTimeCountListenerUtil(OnTimeCountListenerUtil onTimeCountListenerUtil) {
        this.onTimeCountListenerUtil = onTimeCountListenerUtil;
    }
    /**
     * 在这个构造方法里需要传入三个参数，一个是Activity，一个是总的时间millisInFuture，一个是countDownInterval 间隔时间，然后就是你在哪个按钮上做这个是，就把这个按钮传过来就可以了
     */
    public TimeCountUtil(long millisInFuture,
                         long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }
    @SuppressLint("NewApi")
    @Override
    public void onTick(long millisUntilFinished) {
        if (null!=getOnTimeCountListenerUtil()){
            getOnTimeCountListenerUtil().onTick(millisUntilFinished/1000);
        }
    }
    @SuppressLint("NewApi")
    @Override
    public void onFinish() {
        this.cancel();
        if (null!=getOnTimeCountListenerUtil()){
            getOnTimeCountListenerUtil().onfinishListener(); 
        }
    }
  public interface  OnTimeCountListenerUtil{
      /**
       * 计时器完成
       */
      void onfinishListener();

      /**
       * 计时器计时中
       * @param second
       */
       void onTick(long second);
   }
}