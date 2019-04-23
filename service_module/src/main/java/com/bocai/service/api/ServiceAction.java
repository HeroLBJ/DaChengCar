package com.bocai.service.api;

import com.njh.base.ui.view.BaseView;
import com.njh.common.core.ReqTag;
import com.njh.common.flux.actions.ActionsCreator;
import com.njh.common.flux.base.BaseFluxActivity;
import com.njh.common.flux.base.BaseFluxFragment;
import com.njh.common.flux.dispatcher.Dispatcher;
import com.njh.network.api.ServiceManager;

/**
 * @author libingjun
 * @date 2019/4/22
 */
public class ServiceAction extends ActionsCreator {
    public ServiceAction(Dispatcher dispatcher, BaseView baseView) {
        super(dispatcher, baseView);
    }

    public void getServiceDealers(BaseFluxActivity act, int pageNo, double longitude, double latitude, String cityName, String keyword) {
        reqDate(ServiceManager.create(ApiServiceService.class).getServiceDealers(pageNo + "", "10", longitude + "", latitude + "", cityName, keyword),
                act, false, ReqTag.Service.SERVICE_DEALERS);
    }

    public void getServicePackage(BaseFluxFragment act, int pageNo, String type) {
        reqDate(ServiceManager.create(ApiServiceService.class).getServicePackage(pageNo + "", "10", type),
                act, false, ReqTag.Service.SERVICE_PACKAGE);
    }

    public void postServiceKeepOrder(BaseFluxActivity act, String phone, String serviceId, String realname, String cityName, String timeline, String startTime, String endTime, String packageId) {
        reqDate(ServiceManager.create(ApiServiceService.class).postKeepOrder(phone, serviceId, realname, cityName, timeline, startTime, endTime, packageId),
                act, false, ReqTag.Service.SERVICE_KEEP_ORDER);
    }
}
