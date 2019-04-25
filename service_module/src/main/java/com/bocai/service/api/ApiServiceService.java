package com.bocai.service.api;

import com.bocai.service.bean.CallPolice;
import com.bocai.service.bean.FeedbackProvince;
import com.bocai.service.bean.FeedbackType;
import com.bocai.service.bean.Select;
import com.bocai.service.bean.ServiceDealers;
import com.bocai.service.bean.ServicePackage;
import com.bocai.service.bean.SuperServiceBean;
import com.njh.network.bean.ResponseBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
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
    Observable<ResponseBean<SuperServiceBean<ServiceDealers>>>
    getServiceDealers(@Query("pageNo") String pageNo, @Query("limit") String limit,
                      @Query("longitude") String longitude, @Query("latitude") String latitude,
                      @Query("cityName") String cityName, @Query("keyword") String keyword);

    /**
     * 保养套餐
     *
     * @param pageNo 页码
     * @param limit  每页数量
     * @param type   套餐类型 1:免费 2:自费
     */
    @GET("v1/service/package")
    Observable<ResponseBean<SuperServiceBean<ServicePackage>>>
    getServicePackage(@Query("pageNo") String pageNo, @Query("limit") String limit, @Query("type") String type);

    /**
     * 获取维修类型
     */
    @GET("v1/service/repair_type")
    Observable<ResponseBean<SuperServiceBean<Object>>> getServicePackage();

    /**
     * 预约维修提交的订单
     *
     * @param phone         送修人手机号
     * @param serviceId     服务站id
     * @param realname      送修人姓名
     * @param cityName      城市名字
     * @param timeline      时间
     * @param startTime     开始时间段
     * @param endTime       结束时间段
     * @param repairId      维修类型
     * @param repairContent 故障现象描述
     * @param repairPhotos  图片
     */
    @POST("v1/repair_order")
    @FormUrlEncoded
    Observable<ResponseBean<SuperServiceBean<ServicePackage>>>
    postRepairOrder(@Field("phone") String phone, @Field("serviceId") String serviceId,
                    @Field("realname") String realname, @Field("cityName") String cityName,
                    @Field("timeline") String timeline, @Field("startTime") String startTime,
                    @Field("endTime") String endTime, @Field("repairId") String repairId,
                    @Field("repairContent") String repairContent, @Field("repairPhotos") String repairPhotos);


    /**
     * 预约维修提交的订单  二次修改该订单 @PATCH
     *
     * @param phone         送修人手机号
     * @param serviceId     服务站id
     * @param realname      送修人姓名
     * @param cityName      城市名字
     * @param timeline      时间
     * @param startTime     开始时间段
     * @param endTime       结束时间段
     * @param repairId      维修类型
     * @param repairContent 故障现象描述
     * @param repairPhotos  图片
     */
    @PATCH("v1/repair_order/info")
    @FormUrlEncoded
    Observable<ResponseBean<SuperServiceBean<ServicePackage>>>
    patchRepairOrder(@Field("phone") String phone, @Field("serviceId") String serviceId,
                     @Field("realname") String realname, @Field("cityName") String cityName,
                     @Field("timeline") String timeline, @Field("startTime") String startTime,
                     @Field("endTime") String endTime, @Field("repairId") String repairId,
                     @Field("repairContent") String repairContent, @Field("repairPhotos") String repairPhotos);

    /**
     * 预约维修订单详情
     *
     * @param id
     * @return
     */
    @GET("v1/repair_order/info")
    Observable<ResponseBean<SuperServiceBean<ServicePackage>>> getRepairOrderInfo(@Query("id") String id);

    /**
     * 预约保养提交的订单
     *
     * @param phone     送修人手机号
     * @param serviceId 服务站id
     * @param realname  送修人姓名
     * @param cityName  城市名字
     * @param timeline  时间
     * @param startTime 开始时间段
     * @param endTime   结束时间段
     * @param packageId 保养套餐id
     */
    @POST("v1/repair_order")
    @FormUrlEncoded
    Observable<ResponseBean<SuperServiceBean<ServicePackage>>>
    postKeepOrder(@Field("phone") String phone, @Field("serviceId") String serviceId,
                  @Field("realname") String realname, @Field("cityName") String cityName,
                  @Field("timeline") String timeline, @Field("startTime") String startTime,
                  @Field("endTime") String endTime, @Field("packageId") String packageId);

    /**
     * 预约保养提交的订单 二次修改该订单 @PATCH
     *
     * @param id        订单id
     * @param phone     送修人手机号
     * @param serviceId 服务站id
     * @param realname  送修人姓名
     * @param cityName  城市名字
     * @param timeline  时间
     * @param startTime 开始时间段
     * @param endTime   结束时间段
     * @param packageId 保养套餐id
     */
    @POST("v1/maintain_order/info")
    @FormUrlEncoded
    Observable<ResponseBean<SuperServiceBean<ServicePackage>>>
    patchKeepOrder(@Field("id") String id, @Field("phone") String phone, @Field("serviceId") String serviceId,
                   @Field("realname") String realname, @Field("cityName") String cityName,
                   @Field("timeline") String timeline, @Field("startTime") String startTime,
                   @Field("endTime") String endTime, @Field("packageId") String packageId);

    /**
     * 预约保养订单详情
     *
     * @param id 预约保养订单id
     */
    @GET("v1/maintain_order/info")
    Observable<ResponseBean<SuperServiceBean<Object>>> getKeepOrderInfo(@Query("id") String id);

    /**
     * 一键报案
     *
     * @param pageNo 页码
     * @param limit  每页数量
     */
    @GET("v1/service/report")
    Observable<ResponseBean<SuperServiceBean<CallPolice>>> getServiceReport(@Query("pageNo") String pageNo, @Query("limit") String limit);

    /**
     * 配件列表
     *
     * @param pageNo  页码
     * @param limit   每页数量
     * @param keyword 关键字
     * @param carId   汽车id 传0表示搜索全部
     */
    @GET("v1/service/parts")
    Observable<ResponseBean<SuperServiceBean<Select>>>
    getServiceParts(@Query("pageNo") String pageNo, @Query("limit") String limit,
                    @Query("keyword") String keyword, @Query("carId") String carId);

    /**
     * 获取投诉意见的类型
     *
     * @param pageNo 页码
     * @param limit  每页数量
     */
    @GET("v1/service/complain_type")
    Observable<ResponseBean<SuperServiceBean<FeedbackType>>> getFeedbackType(@Query("pageNo") String pageNo, @Query("limit") String limit);

    /**
     * 选择省市
     *
     * @param type 类型 1:经销商 2:服务站
     */
    @GET(" v1/service/service_provinces")
    Observable<ResponseBean<List<FeedbackProvince>>> selectCity(@Query("type") String type);
}























