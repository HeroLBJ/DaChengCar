package com.njh.network.function;

import android.util.Log;

import com.njh.network.exception.ExceptionEngine;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * http结果处理函数
 *
 * @author niejiahuan
 */
public class HttpResultFunction<T> implements Function<Throwable, Observable<T>> {
    @Override
    public Observable<T> apply(@NonNull Throwable throwable) throws Exception {
        //打印具体错误
        Log.e("HttpResultFunction","HttpResultFunction:" + throwable);
        return Observable.error(ExceptionEngine.handleException(throwable));
    }
}
