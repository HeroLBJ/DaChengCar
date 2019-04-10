package com.njh.network.function;

import android.util.Log;

import com.njh.network.bean.ResponseBean;
import com.njh.network.exception.ServerException;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * 服务器结果处理函数
 *
 * @author niejiahuan
 */
public class ServerResultFunction implements Function<ResponseBean, Object> {
    @Override
    public Object apply(@NonNull ResponseBean response) throws Exception {
        //打印服务器回传结果
        Log.e("apply",response.toString());

        if (response.isSuccess()) {
            if (null == response.getData()) {
               return new Object();
            } else{
                return response.getData();
           }
        }else {
            throw new ServerException(response.getReturnCode(), response.getMsg());
        }
    }
}