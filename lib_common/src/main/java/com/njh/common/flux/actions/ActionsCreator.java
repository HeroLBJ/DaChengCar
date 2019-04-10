package com.njh.common.flux.actions;


import com.njh.base.ui.act.BaseAct;
import com.njh.base.ui.fmt.BaseFmt;
import com.njh.base.ui.view.BaseView;
import com.njh.common.core.Sniffer;
import com.njh.common.flux.dispatcher.Dispatcher;
import com.njh.network.observer.HttpRxObservable;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;


/**
 * @author niejiahuan
 */
public class ActionsCreator<V extends BaseView>{
    protected V mView;
    protected final Dispatcher dispatcher;
    Map<String, Sniffer> snifferMap=new HashMap<>();
    public ActionsCreator(Dispatcher dispatcher,V v) {
        this.dispatcher = dispatcher;
        this.mView=v;
    }
    protected void reqDate(Observable apiObservable, BaseAct activity, boolean isShow, final String tag){
        snifferMap.put(tag,new Sniffer(dispatcher,mView,isShow,tag));
        HttpRxObservable.getObservable(apiObservable, activity)
                .subscribe(snifferMap.get(tag));
    }
    protected void reqDate(Observable apiObservable, BaseFmt fragment, boolean isShow, final String tag){
        snifferMap.put(tag,new Sniffer(dispatcher,mView,isShow,tag));
        HttpRxObservable.getObservable(apiObservable, fragment)
                .subscribe(snifferMap.get(tag));
    }
}
