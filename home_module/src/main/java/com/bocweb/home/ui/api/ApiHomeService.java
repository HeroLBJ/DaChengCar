package com.bocweb.home.ui.api;

import com.bocweb.home.ui.bean.ActivityList;
import com.bocweb.home.ui.bean.ActivityPreviewsList;
import com.bocweb.home.ui.bean.CustomData;
import com.bocweb.home.ui.bean.Friend;
import com.bocweb.home.ui.bean.MainComentList;
import com.bocweb.home.ui.bean.MainSelectedFlag;
import com.njh.network.bean.ResponseBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
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
    Observable<ResponseBean<Object>> postMomentPublish(@Field("content") String content, @Field("photo") String photo);
}
