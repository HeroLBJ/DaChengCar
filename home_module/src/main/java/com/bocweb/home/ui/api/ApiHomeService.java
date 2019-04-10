package com.bocweb.home.ui.api;

import com.bocweb.home.ui.bean.MainComentList;
import com.bocweb.home.ui.bean.MainSelectedFlag;
import com.njh.network.bean.ResponseBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author libingjun
 * @date 2019/4/8
 */
public interface ApiHomeService {
    @GET("v1/moment/selectedFlag")
    Observable<ResponseBean<MainSelectedFlag>> getSelectedFlag(@Query("limit") String limit, @Query("page") String page, @Query("city") String city);

    @GET("v1/moment/list")
    Observable<ResponseBean<MainComentList>> getMomentList(@Query("limit") String limit, @Query("page") String page);

}
