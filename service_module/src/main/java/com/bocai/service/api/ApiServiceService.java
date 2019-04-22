package com.bocai.service.api;

import com.bocai.service.bean.ServiceDealers;
import com.bocai.service.bean.SuperServiceBean;
import com.njh.network.bean.ResponseBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author libingjun
 * @date 2019/4/22
 */
public interface ApiServiceService {

    /**
     * 经销商列表
     *
     * @param pageNo    页码
     * @param limit     每页数量
     * @param longitude 经度
     * @param latitude  维度
     * @param cityName  城市全称
     * @param keyword   关键字
     */
    @GET("v2/service/dealers")
    Observable<ResponseBean<SuperServiceBean<ServiceDealers>>> getServiceDealers(@Query("pageNo") String pageNo, @Query("limit") String limit,
                                                                                 @Query("longitude") String longitude, @Query("latitude") String latitude,
                                                                                 @Query("cityName") String cityName, @Query("keyword") String keyword);
}























