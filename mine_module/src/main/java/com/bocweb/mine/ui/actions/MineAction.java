package com.bocweb.mine.ui.actions;

import com.bocweb.mine.api.ApiMineService;
import com.njh.base.ui.view.BaseView;
import com.njh.common.core.ReqTag;
import com.njh.common.flux.actions.ActionsCreator;
import com.njh.common.flux.base.BaseFluxActivity;
import com.njh.common.flux.dispatcher.Dispatcher;
import com.njh.network.api.ServiceManager;

/**
 * @author niejiahuan
 */

public class MineAction extends ActionsCreator {
    public MineAction(Dispatcher dispatcher, BaseView view) {
        super(dispatcher,view);
    }

    /**
     * 登录
     * @param act
     * @param phone
     * @param password
     */
    public void signin(BaseFluxActivity act, String phone,String password) {
        reqDate(ServiceManager.create(ApiMineService.class).signin(phone,password),
                act,false, ReqTag.REQ_TAG_POST_SIGNIN);
    }
}
