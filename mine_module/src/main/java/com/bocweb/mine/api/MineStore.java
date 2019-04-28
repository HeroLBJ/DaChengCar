package com.bocweb.mine.api;

import com.njh.common.core.ReqTag;
import com.njh.common.flux.annotation.BindAction;
import com.njh.common.flux.dispatcher.Dispatcher;
import com.njh.common.flux.stores.Store;

import java.util.HashMap;

/**
 * @author niejiahuan
 */
public class MineStore extends Store {
    public MineStore(Dispatcher dispatcher) {
        super(dispatcher);
    }

    @BindAction(ReqTag.REQ_TAG_POST_SIGNIN)
    public void signin(HashMap<String, Object> data) {
        emitStoreChange(ReqTag.REQ_TAG_POST_SIGNIN, data);
    }

    @BindAction(ReqTag.Mine.MINE_LOGIN_REGISTER)
    public void register(HashMap<String, Object> data) {
        emitStoreChange(ReqTag.Mine.MINE_LOGIN_REGISTER, data);
    }

    @BindAction(ReqTag.Mine.MINE_LOGIN_REGISTER_CODE)
    public void registerCode(HashMap<String, Object> data) {
        emitStoreChange(ReqTag.Mine.MINE_LOGIN_REGISTER_CODE, data);
    }

    @BindAction(ReqTag.Mine.MINE_LOGIN_LOGIN)
    public void login(HashMap<String, Object> data) {
        emitStoreChange(ReqTag.Mine.MINE_LOGIN_LOGIN, data);
    }

    @BindAction(ReqTag.Mine.MINE_UPLOAD_NEW_PHONE)
    public void uploadNewPhone(HashMap<String, Object> data) {
        emitStoreChange(ReqTag.Mine.MINE_UPLOAD_NEW_PHONE, data);
    }

    @BindAction(ReqTag.Mine.MINE_FORGET_PWD_CODE)
    public void forgetPwdCode(HashMap<String, Object> data) {
        emitStoreChange(ReqTag.Mine.MINE_FORGET_PWD_CODE, data);
    }

    @BindAction(ReqTag.Mine.MINE_CHECK_PHONE)
    public void checkPhone(HashMap<String, Object> data) {
        emitStoreChange(ReqTag.Mine.MINE_CHECK_PHONE, data);
    }

    @BindAction(ReqTag.Mine.MINE_SET_NEW_PWD)
    public void setNewPwd(HashMap<String, Object> data) {
        emitStoreChange(ReqTag.Mine.MINE_SET_NEW_PWD, data);
    }

    @BindAction(ReqTag.Mine.MINE_MEMBER_CENTER)
    public void getMemberCenter(HashMap<String, Object> data) {
        emitStoreChange(ReqTag.Mine.MINE_MEMBER_CENTER, data);
    }

    @BindAction(ReqTag.Mine.MINE_INTEGRAL_INFO)
    public void getIntegralInfo(HashMap<String, Object> data) {
        emitStoreChange(ReqTag.Mine.MINE_INTEGRAL_INFO, data);
    }

    @BindAction(ReqTag.Mine.MINE_SCORE_GET)
    public void getScoreGet(HashMap<String, Object> data) {
        emitStoreChange(ReqTag.Mine.MINE_SCORE_GET, data);
    }

    @BindAction(ReqTag.Mine.MINE_SCORE_PAY)
    public void getScorePay(HashMap<String, Object> data) {
        emitStoreChange(ReqTag.Mine.MINE_SCORE_PAY, data);
    }

    @BindAction(ReqTag.Mine.MINE_PHOTO)
    public void getPhoto(HashMap<String, Object> data) {
        emitStoreChange(ReqTag.Mine.MINE_PHOTO, data);
    }

    @BindAction(ReqTag.Mine.MINE_UPLOAD_USER)
    public void uploadUser(HashMap<String, Object> data) {
        emitStoreChange(ReqTag.Mine.MINE_UPLOAD_USER, data);
    }

    @BindAction(ReqTag.Mine.MINE_UPLOAD_USER_SIGN)
    public void uploadUserSign(HashMap<String, Object> data) {
        emitStoreChange(ReqTag.Mine.MINE_UPLOAD_USER_SIGN, data);
    }
}
