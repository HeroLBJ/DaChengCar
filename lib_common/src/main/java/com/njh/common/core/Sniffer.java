package com.njh.common.core;

import com.njh.base.ui.view.BaseView;
import com.njh.common.flux.dispatcher.Dispatcher;
import com.njh.network.exception.ApiException;
import com.njh.network.observer.HttpRxObserver;

import io.reactivex.disposables.Disposable;

/**
 * Created by niejiahuan on 18/2/6.
 */

public class Sniffer extends HttpRxObserver {
    protected Dispatcher mDispatcher;
    protected BaseView mV;
    protected boolean mIsShow;
    public Sniffer(Dispatcher dispatcher, BaseView v, boolean isShow, String tag){
        super(tag);
        this.mDispatcher=dispatcher;
        this.mV=v;
        mIsShow=isShow;
    }
    @Override
    protected void onStart(Disposable d) {
        if (null!=this.mV&&mIsShow){
            this.mV.showLoading();
        }
    }

    @Override
    protected void onError(ApiException e) {
        if (null!=this.mV&&mIsShow){
            this.mV.hideLoading();
        }
        if (null!=this.mV){
            mV.onError(e.getCode(),e.getMsg(),mTag);
        }
        mDispatcher.dispatch(mTag,
                Constants.CODE, e.getCode(),
                Constants.MSG, e.getMsg(),Constants.DATA,"");
    }

    @Override
    protected void onSuccess(Object response) {
        if (null!=this.mV&&mIsShow){
            this.mV.hideLoading();
        }
        mDispatcher.dispatch(mTag,
                Constants.CODE, 200,
                Constants.MSG,"", Constants.DATA, response);
    }

}
