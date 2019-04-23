package com.bocai.service.api;

import com.njh.common.core.ReqTag;
import com.njh.common.flux.annotation.BindAction;
import com.njh.common.flux.dispatcher.Dispatcher;
import com.njh.common.flux.stores.Store;

import java.util.HashMap;

/**
 * @author libingjun
 * @date 2019/4/22
 */
public class ServiceStore extends Store {
    public ServiceStore(Dispatcher dispatcher) {
        super(dispatcher);
    }

    @BindAction(ReqTag.Service.SERVICE_DEALERS)
    public void getServiceDealers(HashMap<String, Object> data) {
        emitStoreChange(ReqTag.Service.SERVICE_DEALERS, data);
    }

    @BindAction(ReqTag.Service.SERVICE_PACKAGE)
    public void getServicePackage(HashMap<String, Object> data) {
        emitStoreChange(ReqTag.Service.SERVICE_PACKAGE, data);
    }

    @BindAction(ReqTag.Service.SERVICE_KEEP_ORDER)
    public void postServiceKeepOrder(HashMap<String, Object> data) {
        emitStoreChange(ReqTag.Service.SERVICE_KEEP_ORDER, data);
    }


}
