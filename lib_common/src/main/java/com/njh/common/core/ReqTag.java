package com.njh.common.core;

/**
 * @author niejiahuan
 */
public interface ReqTag {
    /**
     * 登录
     */
    String REQ_TAG_POST_SIGNIN = "post_signin";

    String REQ_TAG_GET_HOME_MOMENT_SELECTED_FLAG = "REQ_TAG_GET_HOME_MOMENT_SELECTED_FLAG";
    String REQ_TAG_GET_HOME_MOMENT_LIST = "REQ_TAG_GET_HOME_MOMENT_LIST";
    String REQ_TAG_GET_HOME_ACTIVITY_ACTIVITY_LIST = "REQ_TAG_GET_HOME_ACTIVITY_ACTIVITY_LIST";
    String REQ_TAG_GET_HOME_ACTIVITY_PREVIEWS_LIST = "REQ_TAG_GET_HOME_ACTIVITY_PREVIEWS_LIST";
    String REQ_TAG_GET_HOME_MOMENT_MEMBER_SEARCH = "REQ_TAG_GET_HOME_MOMENT_MEMBER_SEARCH";
    String REQ_TAG_POST_HOME_MOMENT_PUBLISH = "REQ_TAG_POST_HOME_MOMENT_PUBLISH";
    String REQ_TAG_POST_HOME_MOMENT_FOLLOW = "REQ_TAG_POST_HOME_MOMENT_FOLLOW";
    String REQ_TAG_POST_HOME_ACTIVITY_ACTIVITY_ZAN = "REQ_TAG_POST_HOME_ACTIVITY_ACTIVITY_ZAN";
    String REQ_TAG_POST_HOME_ACTIVITY_PREVIEWS_ZAN = "REQ_TAG_POST_HOME_ACTIVITY_PREVIEWS_ZAN";

    interface Service {
        String SERVICE_DEALERS = "SERVICE_DEALERS";
        String SERVICE_PACKAGE = "SERVICE_PACKAGE";
        String SERVICE_REPAIR_TYPE = "SERVICE_REPAIR_TYPE";
        String SERVICE_REPAIR_ORDER = "SERVICE_REPAIR_ORDER";
        String SERVICE_REPAIR_ORDER_PATCH = "SERVICE_REPAIR_ORDER_PATCH";
        String SERVICE_REPAIR_ORDER_INFO = "SERVICE_REPAIR_ORDER_INFO";

        String SERVICE_KEEP_TYPE = "SERVICE_KEEP_TYPE";
        String SERVICE_KEEP_ORDER = "SERVICE_KEEP_ORDER";
        String SERVICE_KEEP_ORDER_PATCH = "SERVICE_KEEP_ORDER_PATCH";
        String SERVICE_KEEP_ORDER_INFO = "SERVICE_KEEP_ORDER_INFO";
        String SERVICE_REPORT = "SERVICE_REPORT";
        String SERVICE_PARTS = "SERVICE_PARTS";
        String SERVICE_FEEDBACK = "SERVICE_FEEDBACK";
        String SERVICE_FEEDBACK_TYPE = "SERVICE_FEEDBACK_TYPE";
        String SERVICE_SELECT_CITY = "SERVICE_SELECT_CITY";
    }

    interface Mine {
        String MINE_LOGIN_REGISTER = "MINE_LOGIN_REGISTER";
        String MINE_LOGIN_REGISTER_CODE = "MINE_LOGIN_REGISTER_CODE";
        String MINE_LOGIN_LOGIN = "MINE_LOGIN_LOGIN";
        String MINE_UPLOAD_NEW_PHONE = "MINE_UPLOAD_NEW_PHONE";
        String MINE_FIND_PWD = "MINE_FIND_PWD";
        String MINE_FORGET_PWD_CODE = "MINE_FORGET_PWD_CODE";
        String MINE_CHECK_PHONE = "MINE_CHECK_PHONE";
        String MINE_SET_NEW_PWD = "MINE_SET_NEW_PWD";
        String MINE_MEMBER_CENTER = "MINE_MEMBER_CENTER";
        String MINE_INTEGRAL_INFO = "MINE_INTEGRAL_INFO";
        String MINE_SCORE_GET = "MINE_SCORE_GET";
        String MINE_SCORE_PAY = "MINE_SCORE_PAY";
        String MINE_PHOTO = "MINE_PHOTO";
        String MINE_UPLOAD_USER = "MINE_UPLOAD_USER";
        String MINE_UPLOAD_USER_SIGN = "MINE_UPLOAD_USER_SIGN";
        String MINE_USER_CENTER_BG = "MINE_USER_CENTER_BG";
        String MINE_USER_MOMENT_LIST = "MINE_USER_MOMENT_LIST";
    }

}
