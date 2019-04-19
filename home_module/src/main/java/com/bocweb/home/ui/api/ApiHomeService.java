package com.bocweb.home.ui.api;

import com.bocweb.home.ui.bean.ActivityList;
import com.bocweb.home.ui.bean.ActivityPreviewsList;
import com.bocweb.home.ui.bean.CustomData;
import com.bocweb.home.ui.bean.StatusResponse;
import com.bocweb.home.ui.bean.Friend;
import com.bocweb.home.ui.bean.MainComentList;
import com.bocweb.home.ui.bean.MainSelectedFlag;
import com.njh.network.bean.ResponseBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author libingjun
 * @date 2019/4/8
 */
public interface ApiHomeService {
    @GET("v1/moment/selectedFlag")
    Observable<ResponseBean<MainSelectedFlag>> getSelectedFlag(
            @Query("limit") String limit, @Query("page") String page, @Query("city") String city);

    @GET("v1/moment/list")
    Observable<ResponseBean<MainComentList>> getMomentList(
            @Query("limit") String limit, @Query("page") String page);

    /**
     * 获取活动列表
     *
     * @param limit 每页大小
     * @param page 页码
     * @param city 城市名称
     * @param type  0-全部 1-未开始 2-报名中 3-活动中 4-已结束
     */
    @GET("v2/activity/activity_list")
    Observable<ResponseBean<ActivityList>> getActivityList(
            @Query("limit") String limit, @Query("page") String page, @Query("city") String city, @Query("type") String type);

    @GET("v1/activity/previews_list")
    Observable<ResponseBean<ActivityPreviewsList>> getPreviewsList(
            @Query("limit") String limit, @Query("page") String page, @Query("city") String city);

    @GET("v1/moment/memberSearch")
    Observable<ResponseBean<CustomData<Friend>>> getMomentMemberSearch(
            @Query("limit") String limit, @Query("page") String page, @Query("keyword") String keyword);

    @POST("v1/moment/publish")
    @FormUrlEncoded
    Observable<ResponseBean<Object>> postMomentPublish(@Field("content") String content, @Field("photo") String photo);

    /**
     * 关注与取消关注
     *
     * @param mid 被关注人的id
     */
    @POST("v1/moment/follow")
    @FormUrlEncoded
    Observable<ResponseBean<StatusResponse>> postMomentFollow(@Field("mid") String mid);

    /**
     * 活动的点赞
     *
     * @param id 被关注人的id
     */
    @POST("v2/activity/activity_zan")
    @FormUrlEncoded
    Observable<ResponseBean<StatusResponse>> postActivityActivityZan(@Field("id") String id);

    /**
     * 新闻评论和新闻点赞
     *
     * @param id   记录id
     * @param type 0：点赞评论  1：点赞新闻
     */
    @POST("v2/activity/previews_zan")
    @FormUrlEncoded
    Observable<ResponseBean<StatusResponse>> postActivityPreviewsZan(@Field("id") String id, @Field("type") String type);
}
