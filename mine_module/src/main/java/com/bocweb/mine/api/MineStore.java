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
}
