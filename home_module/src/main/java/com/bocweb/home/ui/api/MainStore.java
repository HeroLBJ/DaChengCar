package com.bocweb.home.ui.api;

import com.njh.common.core.ReqTag;
import com.njh.common.flux.annotation.BindAction;
import com.njh.common.flux.dispatcher.Dispatcher;
import com.njh.common.flux.stores.Store;

import java.util.HashMap;

/**
 * @author libingjun
 * @date 2019/4/8
 */
public class MainStore extends Store {
    public MainStore(Dispatcher dispatcher) {
        super(dispatcher);
    }

    @BindAction(ReqTag.REQ_TAG_GET_HOME_MOMENT_SELECTED_FLAG)
    public void getSelectedFlag(HashMap<String, Object> data) {
        emitStoreChange(ReqTag.REQ_TAG_GET_HOME_MOMENT_SELECTED_FLAG, data);
    }

    @BindAction(ReqTag.REQ_TAG_GET_HOME_MOMENT_LIST)
    public void getMonentList(HashMap<String, Object> data) {
        emitStoreChange(ReqTag.REQ_TAG_GET_HOME_MOMENT_LIST, data);
    }
}