package com.bocweb.mine.api;

import com.bocweb.mine.bean.LoginInfo;
import com.bocweb.mine.bean.MyScore;
import com.bocweb.mine.bean.ScoreDetail;
import com.njh.common.sp.user.UserInfo;
import com.njh.network.bean.ResponseBean;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

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

    /**
     * 更新手机
     *
     * @param phone 手机号码
     * @param code  验证码
     * @param type  2：修改为新手机 4：绑定手机
     * @param lat   type=4时，传维度
     * @param lng   type=4时，传经度
     */
    @POST("v1/member/update_newPhone")
    @FormUrlEncoded
    Observable<ResponseBean<Object>>
    updateNewPhone(@Field("phone") String phone, @Field("code") String code,
                   @Field("type") String type, @Field("lat") String lat, @Field("lng") String lng);

    /**
     * 忘记密码发送验证码
     *
     * @param phone 手机号
     */
    @POST("v3/login/password_code")
    @FormUrlEncoded
    Observable<ResponseBean<Object>> forgetPwdCode(@Field("phone") String phone);

    /**
     * 找回密码 第一步 验证手机号
     *
     * @param phone 手机号
     * @param code  验证码
     */
    @POST("v3/login/check_phone")
    @FormUrlEncoded
    Observable<ResponseBean<Object>> checkPhone(@Field("phone") String phone, @Field("code") String code);

    /**
     * 找回密码 第一步 验证手机号
     *
     * @param phone    手机号
     * @param password 新密码 （必须包含数字和字母8-16位）
     */
    @POST("v3/login/password")
    @FormUrlEncoded
    Observable<ResponseBean<Object>> setNewPwd(@Field("phone") String phone, @Field("password") String password);

    /**
     * 会员信息
     */
    @GET("v3/member/info")
    Observable<ResponseBean<UserInfo>> getUserInfo(@Query("isuser") String isuser);

    /**
     * 我的积分
     */
    @GET("v3/member/integral_info")
    Observable<ResponseBean<MyScore>> getIntegralInfo();

    /**
     * 积分收入支出明细
     */
    @GET("v1/member/record_list")
    Observable<ResponseBean<ScoreDetail>>
    getRecordList(@Query("limit") String limit, @Query("page") String page, @Query("type") String type);

    @POST("v1/member/update_user")
    @FormUrlEncoded
    Observable<ResponseBean<String>>
    updateUser(@Field("nickname") String nickname, @Field("name") String name,
               @Field("gender") String gender, @Field("year") String year,
               @Field("month") String monty, @Field("day") String day,
               @Field("provinceName") String provinceName,
               @Field("cityName") String cityName, @Field("sightml") String sightml);

    /**
     * 图片上传
     */
    @Multipart
    @POST("v1/picupload/photo")
    Observable<ResponseBean<Object>> getPhoto(@Part MultipartBody.Part uploadFile);
}

























