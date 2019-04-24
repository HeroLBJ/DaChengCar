package com.bocweb.mine.api;

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
        super(dispatcher, view);
    }

    /**
     * 登录
     *
     * @param act
     * @param phone
     * @param password
     */
    public void signin(BaseFluxActivity act, String phone, String password) {
        reqDate(ServiceManager.create(ApiMineService.class).signin(phone, password),
                act, false, ReqTag.REQ_TAG_POST_SIGNIN);
    }

    public void register(BaseFluxActivity act, String phone, String code) {
        reqDate(ServiceManager.create(ApiMineService.class).register(phone, code),
                act, false, ReqTag.Mine.MINE_LOGIN_REGISTER);
    }

    public void registerCode(BaseFluxActivity act, String phone) {
        reqDate(ServiceManager.create(ApiMineService.class).registerCode(phone),
                act, false, ReqTag.Mine.MINE_LOGIN_REGISTER_CODE);
    }

    public void login(BaseFluxActivity act, String phone, String password) {
        reqDate(ServiceManager.create(ApiMineService.class).login(phone, password),
                act, false, ReqTag.Mine.MINE_LOGIN_LOGIN);
    }

    public void updateNewPhone(BaseFluxActivity act, String phone, String code, String type, String lat, String lng) {
        reqDate(ServiceManager.create(ApiMineService.class).updateNewPhone(phone, code, type, lat, lng),
                act, false, ReqTag.Mine.MINE_UPLOAD_NEW_PHONE);
    }

    public void forgetPwdCode(BaseFluxActivity act, String phone) {
        reqDate(ServiceManager.create(ApiMineService.class).forgetPwdCode(phone),
                act, false, ReqTag.Mine.MINE_FORGET_PWD_CODE);
    }

    public void checkPhone(BaseFluxActivity act, String phone, String code) {
        reqDate(ServiceManager.create(ApiMineService.class).checkPhone(phone, code),
                act, false, ReqTag.Mine.MINE_CHECK_PHONE);
    }

    public void setNewPwd(BaseFluxActivity act, String phone, String password) {
        reqDate(ServiceManager.create(ApiMineService.class).setNewPwd(phone, password),
                act, false, ReqTag.Mine.MINE_SET_NEW_PWD);
    }
}
