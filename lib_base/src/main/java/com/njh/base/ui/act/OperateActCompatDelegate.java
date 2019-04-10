package com.njh.base.ui.act;

import android.view.View;

import com.jakewharton.rxbinding2.view.RxView;
import com.njh.base.utils.Toastor;
import com.trello.rxlifecycle3.android.ActivityEvent;
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;

import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import cn.itsite.statemanager.StateManager;
import io.reactivex.Observable;

/**
 * @author niejiahuan
 */
public class OperateActCompatDelegate {
    RxAppCompatActivity rxAppCompatActivity;
    protected static StateManager mStateManager;
    protected Toastor toastor;


    public Toastor getToastor() {
        return toastor;
    }

    public StateManager getStateManager() {
        return mStateManager;
    }

    public OperateActCompatDelegate(RxAppCompatActivity rxAppCompatActivity) {
        this.rxAppCompatActivity = rxAppCompatActivity;
        toastor=new Toastor(rxAppCompatActivity);
    }
    public void setStatsManager(int resId){
        if (resId!=0) {
            setStatsManager(rxAppCompatActivity.findViewById(resId));
        }
    }
    public void setStatsManager(@NonNull View view){
        mStateManager=StateManager.builder(rxAppCompatActivity)
                .setContent(view)
                .build();
    }
    public Observable click(View view) {
        return throttleFirst(RxView.clicks(view));
    }

    /**
     * 防抖动，防止快速点击
     *
     * @param observable
     * @param <T>
     * @return
     */
    protected <T extends Object> Observable<T> throttleFirst(Observable<T> observable) {
        return observable.throttleFirst(500, TimeUnit.MILLISECONDS);
    }

    /**
     * 将事件与生命周期绑定
     *
     * @param observable
     * @return
     */
    protected <T extends Object> Observable<T> bindLife(Observable<T> observable) {
        return (Observable<T>) observable.compose(rxAppCompatActivity.bindToLifecycle());
    }

    /**
     * 指定事件在哪个生命周期结束
     *
     * @param observable
     * @param event      生命周期
     * @return
     */
    protected <T extends Object> Observable<T> bindUntil(Observable<T> observable, ActivityEvent event) {
        return (Observable<T>) observable.compose(rxAppCompatActivity.bindUntilEvent(event));
    }
}
