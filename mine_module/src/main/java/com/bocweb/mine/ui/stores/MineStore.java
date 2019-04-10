package com.bocweb.mine.ui.stores;

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
}
