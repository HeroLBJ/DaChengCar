package com.njh.base.ui.fmt;

import android.view.View;

import com.jakewharton.rxbinding2.view.RxView;
import com.njh.base.app.BaseApp;
import com.njh.base.utils.Toastor;
import com.trello.rxlifecycle3.components.support.RxFragment;

import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import cn.itsite.statemanager.StateManager;
import io.reactivex.Observable;

/**
 * @author niejiahuan
 */
public class OperateFmtCompatDelegate {
    RxFragment rxFragment;

    StateManager mStateManager;
    protected Toastor toastor;

    public Toastor getToastor() {
        return toastor;
    }

    public StateManager getStateManager() {
        return mStateManager;
    }
    public OperateFmtCompatDelegate(RxFragment rxFragment) {
        this.rxFragment = rxFragment;
        // TODO Toastor 需要实例化
        toastor = new Toastor(BaseApp.getAppInstance());
    }

    public void setStatsManager(int resId){
        if (resId!=0) {
            setStatsManager(rxFragment.getView().findViewById(resId));
        }
    }
    public void setStatsManager(@NonNull View view){
        mStateManager= StateManager.builder(rxFragment.getContext())
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
            return (Observable<T>) observable.compose(rxFragment.bindToLifecycle());
    }

}
