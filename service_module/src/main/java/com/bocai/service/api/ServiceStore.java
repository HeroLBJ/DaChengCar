package com.bocai.service.api;

import com.njh.common.core.ReqTag;
import com.njh.common.flux.annotation.BindAction;
import com.njh.common.flux.dispatcher.Dispatcher;
import com.njh.common.flux.stores.Store;

import java.util.HashMap;

import butterknife.BindView;

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

    @BindAction(ReqTag.Service.SERVICE_REPAIR_TYPE)
    public void getServiceRepairType(HashMap<String, Object> data) {
        emitStoreChange(ReqTag.Service.SERVICE_REPAIR_TYPE, data);
    }

    @BindAction(ReqTag.Service.SERVICE_REPORT)
    public void getServiceReport(HashMap<String, Object> data) {
        emitStoreChange(ReqTag.Service.SERVICE_REPORT, data);
    }

    @BindAction(ReqTag.Service.SERVICE_PARTS)
    public void getServiceParts(HashMap<String, Object> data) {
        emitStoreChange(ReqTag.Service.SERVICE_PARTS, data);
    }

    @BindAction(ReqTag.Service.SERVICE_FEEDBACK_TYPE)
    public void getFeedbackType(HashMap<String,Object> data){
        emitStoreChange(ReqTag.Service.SERVICE_FEEDBACK_TYPE, data);
    }
}
