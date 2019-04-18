package com.bocweb.home.ui.api;

import com.njh.common.core.ReqTag;
import com.njh.common.flux.annotation.BindAction;
import com.njh.common.flux.dispatcher.Dispatcher;
import com.njh.common.flux.stores.Store;

import java.util.HashMap;

import butterknife.BindView;

/**
 * @author libingjun
 * @date 2019/4/8
 */
public class MainStore extends Store {
    public MainStore(Dispatcher dispatcher) {
        super(dispatcher);
    }

    @BindAction(ReqTag.REQ_TAG_GET_HOME_MOMENT_SELECTED_FLAG)
    public void getSelectedFlag(HashMap<String, Object> data) {
        emitStoreChange(ReqTag.REQ_TAG_GET_HOME_MOMENT_SELECTED_FLAG, data);
    }

    @BindAction(ReqTag.REQ_TAG_GET_HOME_MOMENT_LIST)
    public void getMomentList(HashMap<String, Object> data) {
        emitStoreChange(ReqTag.REQ_TAG_GET_HOME_MOMENT_LIST, data);
    }

    @BindAction(ReqTag.REQ_TAG_GET_HOME_ACTIVITY_ACTIVITY_LIST)
    public void getActivityList(HashMap<String, Object> data) {
        emitStoreChange(ReqTag.REQ_TAG_GET_HOME_ACTIVITY_ACTIVITY_LIST, data);
    }

    @BindAction(ReqTag.REQ_TAG_GET_HOME_ACTIVITY_PREVIEWS_LIST)
    public void getPreviewsList(HashMap<String, Object> data) {
        emitStoreChange(ReqTag.REQ_TAG_GET_HOME_ACTIVITY_PREVIEWS_LIST, data);
    }

    @BindAction(ReqTag.REQ_TAG_GET_HOME_MOMENT_MEMBER_SEARCH)
    public void getMomentMemberSearch(HashMap<String, Object> data) {
        emitStoreChange(ReqTag.REQ_TAG_GET_HOME_MOMENT_MEMBER_SEARCH, data);
    }

    @BindAction(ReqTag.REQ_TAG_POST_HOME_MOMENT_PUBLISH)
    public void postMomentPublish(HashMap<String, Object> data) {
        emitStoreChange(ReqTag.REQ_TAG_POST_HOME_MOMENT_PUBLISH, data);
    }

    @BindAction(ReqTag.REQ_TAG_POST_HOME_MOMENT_FOLLOW)
    public void postMomentFollow(HashMap<String, Object> data) {
        emitStoreChange(ReqTag.REQ_TAG_POST_HOME_MOMENT_FOLLOW, data);
    }

    @BindAction(ReqTag.REQ_TAG_POST_HOME_ACTIVITY_PREVIEWS_ZAN)
    public void postActivityPreviewsZan(HashMap<String, Object> data) {
        emitStoreChange(ReqTag.REQ_TAG_POST_HOME_ACTIVITY_PREVIEWS_ZAN, data);
    }
}
