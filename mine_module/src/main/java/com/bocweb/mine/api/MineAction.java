package com.bocweb.mine.api;

import com.njh.base.ui.view.BaseView;
import com.njh.common.constant.Constant;
import com.njh.common.core.ReqTag;
import com.njh.common.flux.actions.ActionsCreator;
import com.njh.common.flux.base.BaseFluxActivity;
import com.njh.common.flux.base.BaseFluxFragment;
import com.njh.common.flux.dispatcher.Dispatcher;
import com.njh.network.api.ServiceManager;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @author niejiahuan
 */

public class MineAction extends ActionsCreator {
    public MineAction(Dispatcher dispatcher, BaseView view) {
        super(dispatcher, view);
    }

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

    public void getMemberCenter(BaseFluxFragment act) {
        reqDate(ServiceManager.create(ApiMineService.class).getUserInfo("1"),
                act, false, ReqTag.Mine.MINE_MEMBER_CENTER);
    }

    public void getIntegralInfo(BaseFluxActivity act) {
        reqDate(ServiceManager.create(ApiMineService.class).getIntegralInfo(),
                act, false, ReqTag.Mine.MINE_INTEGRAL_INFO);
    }

    public void getScoreGetList(BaseFluxFragment act, int page) {
        reqDate(ServiceManager.create(ApiMineService.class).getRecordList("10", page + "", "1"),
                act, false, ReqTag.Mine.MINE_SCORE_GET);
    }

    public void getScorePayList(BaseFluxFragment act, int page) {
        reqDate(ServiceManager.create(ApiMineService.class).getRecordList("10", page + "", "2"),
                act, false, ReqTag.Mine.MINE_SCORE_PAY);
    }

    public void uploadUser(BaseFluxActivity act, String nickname, String name, String gender, String year,
                           String month, String day, String provinceName, String cityName, String sightml) {
        reqDate(ServiceManager.create(ApiMineService.class).updateUser(nickname, name, gender, year, month, day, provinceName, cityName, sightml),
                act, false, ReqTag.Mine.MINE_UPLOAD_USER);
    }

    public void uploadUserSign(BaseFluxActivity act, String sightml) {
        reqDate(ServiceManager.create(ApiMineService.class).updateUser("", "", "", "", "", "", "", "", sightml),
                act, false, ReqTag.Mine.MINE_UPLOAD_USER_SIGN);
    }


    public void getPhoto(BaseFluxActivity act, File file) {
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("uploadFile", file.getName(), requestFile);
        reqDate(ServiceManager.create(ApiMineService.class).getPhoto(body),
                act, false, ReqTag.Mine.MINE_PHOTO);
    }

    public void uploadUserCenterBg(BaseFluxActivity act, String img) {
        reqDate(ServiceManager.create(ApiMineService.class).updateUserCenterBg(img),
                act, false, ReqTag.Mine.MINE_USER_CENTER_BG);
    }

    public void getUserMomentList(BaseFluxActivity act, int page) {
        reqDate(ServiceManager.create(ApiMineService.class).getMemberMomentList(Constant.Num.NUM_10 + "", page + ""),
                act, false, ReqTag.Mine.MINE_USER_MOMENT_LIST);
    }
}
