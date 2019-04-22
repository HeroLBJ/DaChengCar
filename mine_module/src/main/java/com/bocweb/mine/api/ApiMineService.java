package com.bocweb.mine.api;


import com.bocweb.mine.bean.LoginInfo;
import com.njh.network.bean.ResponseBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import static me.jessyan.retrofiturlmanager.RetrofitUrlManager.DOMAIN_NAME_HEADER;

/**
 * @Description: 个人中心模块
 * @date: 2016-12-30 16:42
 */
public interface ApiMineService {
//    @Headers({DOMAIN_NAME_HEADER}) //如果不需要多个 BaseUrl ,继续使用初始化时传入 Retrofit 中的默认 BaseUrl ,就不要加上 DOMAIN_NAME_HEADER 这个 Header
//    @GET()
//    Observable<Object> get(@QueryMap Map<String, Object> maps);

    /**
     * 用户登录接口
     *
     * @param phone    手机号
     * @param password 密码
     * @return
     */
    @Headers({DOMAIN_NAME_HEADER})
    //如果不需要多个 BaseUrl ,继续使用初始化时传入 Retrofit 中的默认 BaseUrl ,就不要加上 DOMAIN_NAME_HEADER 这个 Header
    @POST("member/signin")
    @FormUrlEncoded
    Observable<ResponseBean<Object>> signin(@Field("phone") String phone, @Field("password") String password);

    /**
     * 登录并注册
     *
     * @param phone 手机号
     * @param code  验证码
     */
    @POST("v3/login/register")
    @FormUrlEncoded
    Observable<ResponseBean<LoginInfo>> register(@Field("phone") String phone, @Field("code") String code);

    /**
     * 获取验证码
     *
     * @param phone 手机号
     */
    @POST("v3/login/register_code")
    @FormUrlEncoded
    Observable<ResponseBean<LoginInfo>> registerCode(@Field("phone") String phone);

    /**
     * 登录
     *
     * @param phone    手机号
     * @param password 密码
     */
    @POST("v3/login/login")
    @FormUrlEncoded
    Observable<ResponseBean<LoginInfo>> login(@Field("phone") String phone, @Field("password") String password);


//
//
//    @Headers({DOMAIN_NAME_HEADER + GITHUB_DOMAIN_NAME})
//    @FormUrlEncoded
//    @POST()
//    Observable<ResultBean> post(@Url() String url, @FieldMap Map<String, Object> maps);


}
