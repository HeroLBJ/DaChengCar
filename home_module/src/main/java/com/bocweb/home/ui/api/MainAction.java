package com.bocweb.home.ui.api;

import com.bocweb.home.ui.api.ApiHomeService;
import com.njh.base.ui.view.BaseView;
import com.njh.common.core.ReqTag;
import com.njh.common.flux.actions.ActionsCreator;
import com.njh.common.flux.base.BaseFluxFragment;
import com.njh.common.flux.dispatcher.Dispatcher;
import com.njh.network.api.ServiceManager;

/**
 * @author libingjun
 * @date 2019/4/8
 */
public class MainAction extends ActionsCreator {
    public MainAction(Dispatcher dispatcher, BaseView baseView) {
        super(dispatcher, baseView);
    }

    public void getSelectedFlag(BaseFluxFragment act, String limit, String page, String city) {
        reqDate(ServiceManager.create(ApiHomeService.class).getSelectedFlag(limit,page,city),
                act,false, ReqTag.REQ_TAG_GET_HOME_MOMENT_SELECTED_FLAG);
    }

    public void getMomentList(BaseFluxFragment act, String limit, String page) {
        reqDate(ServiceManager.create(ApiHomeService.class).getMomentList(limit,page),
                act,false, ReqTag.REQ_TAG_GET_HOME_MOMENT_LIST);
    }
}
