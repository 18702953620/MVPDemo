package com.h.cheng.mvpdemo.livedata.base;

import org.json.JSONException;
import org.reactivestreams.Subscription;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.ParseException;

import io.reactivex.FlowableSubscriber;
import retrofit2.HttpException;

/**
 * 作者： ch
 * 时间： 2019/10/9 10:48
 * 描述：
 * 来源：
 */
public abstract class BaseObserver<T> implements FlowableSubscriber<T> {

    public static final String PARSE_ERROR_MSG = "解析数据失败";
    /**
     * 网络问题
     */
    public static final String BAD_NETWORK_MSG = "网络问题";
    /**
     * 连接错误
     */
    public static final String CONNECT_ERROR_MSG = "连接错误";
    /**
     * 连接超时
     */
    public static final String CONNECT_TIMEOUT_MSG = "连接超时";
    /**
     * 未知错误
     */
    public static final String OTHER_MSG = "未知错误";


    public abstract void onSuccess(T o);

    public abstract void onError(String msg);


    @Override
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(T o) {
        onSuccess(o);
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof HttpException) {
            //   HTTP错误
            onError(BAD_NETWORK_MSG);
        } else if (e instanceof ConnectException
                || e instanceof UnknownHostException) {
            //   连接错误
            onError(CONNECT_ERROR_MSG);
        } else if (e instanceof InterruptedIOException) {
            //  连接超时
            onError(CONNECT_TIMEOUT_MSG);
        } else if (e instanceof JSONException || e instanceof ParseException) {
            //  解析错误
            onError(PARSE_ERROR_MSG);
        } else {
            onError(OTHER_MSG);
        }
    }

    @Override
    public void onComplete() {

    }

}
