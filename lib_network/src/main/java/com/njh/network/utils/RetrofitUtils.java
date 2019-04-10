package com.njh.network.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.njh.network.BuildConfig;

import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

/**
 * RetrofitUtils工具类
 *
 * @author niejiahuan
 */
public class RetrofitUtils {
    /**
     * 接口地址
     */
    public static final String BASE_URL = BuildConfig.BASE_URL;
    public static final int CONNECT_TIME_OUT = 60;//连接超时时长x秒
    public static final int READ_TIME_OUT = 60;//读数据超时时长x秒
    public static final int WRITE_TIME_OUT = 60;//写数据接超时时长x秒
    private static RetrofitUtils mInstance = null;
    Context mContext;
    private RetrofitUtils(Context context) {
        mContext=context;
    }

    public static RetrofitUtils get(Context context) {
        if (mInstance == null) {
            synchronized (RetrofitUtils.class) {
                if (mInstance == null) {
                    mInstance = new RetrofitUtils(context);
                }
            }
        }
        return mInstance;
    }

    /**
     * 设置okHttp
     *
     * @author niejiahuan
     */
    private OkHttpClient okHttpClient() {
        //开启Log
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e("okHttp","okHttp:" + message);
            }
        });
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = RetrofitUrlManager.getInstance().with(new OkHttpClient.Builder())
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request.Builder builder = chain.request().newBuilder();
                        if (null!= TokenManager.getInstance().getToken()&&
                                !TokenManager.getInstance().getToken().isEmpty()) {

                            Log.e("authorization","authorization :"+TokenManager.getInstance().getToken());

                            builder.addHeader("Authorization",TokenManager.getInstance().getToken());
                        }
                        builder.addHeader("platform","1");
                        return chain.proceed(builder.build());
                    }
                })
                .build();
        return client;
    }

    public String getRsRandom(){
        StringBuffer rs_key=new StringBuffer();
        Random rand = new Random();
        String str="0123456789abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i <16 ; i++) {
            int randNumber =rand.nextInt(str.length()-1 - 1 + 1) + 1;
            rs_key.append(str.charAt(randNumber));
        }
        return rs_key.toString();
    }
    /**
     * 获取Retrofit
     *
     * @author niejiahuan
     */
    public Retrofit retrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient())
                .baseUrl(BASE_URL)
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }
    /**
     * 默认信任所有的证书
     *
     * @return
     */
    @SuppressLint("TrulyRandom")
    private static SSLSocketFactory createSSLSocketFactory() {

        SSLSocketFactory sSLSocketFactory = null;

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllManager()},
                    new SecureRandom());
            sSLSocketFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }

        return sSLSocketFactory;
    }

    private static class TrustAllManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType)

                throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    private static class TrustAllHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }
}
