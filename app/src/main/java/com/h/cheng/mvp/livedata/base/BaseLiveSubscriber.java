package com.h.cheng.mvp.livedata.base;


import com.h.cheng.mvp.api.BaseException;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.ParseException;

import io.reactivex.subscribers.DisposableSubscriber;
import retrofit2.HttpException;

/**
 * @author ch
 * 时间： 2019/11/21 14:05
 * 描述：
 * 来源：
 */
public abstract class BaseLiveSubscriber<T> extends DisposableSubscriber<T> {


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        BaseException be;

        if (e != null) {

            if (e instanceof BaseException) {
                be = (BaseException) e;

                //回调到view层 处理 或者根据项目情况处理

            } else {
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    //   HTTP错误
                    int code = httpException.code();
                    String content = "";
                    if (code == 401) {
                        content = "401:登录已过期,或您的帐号在别处登录,请您重新登录!";
                    } else if (code == 403) {
                        content = "403:服务器请求异常,请稍候再试!";
                    } else if (code == 404) {
                        content = "404:服务器请求异常,请稍候再试!";
                    } else if (code == 500) {
                        content = "500:服务器请求异常,请稍候再试!";
                    } else {
                        content = BaseException.BAD_NETWORK_MSG;
                    }
                    be = new BaseException(content, e, BaseException.BAD_NETWORK);
                } else if (e instanceof ConnectException
                        || e instanceof UnknownHostException) {
                    //   连接错误
                    be = new BaseException(BaseException.CONNECT_ERROR_MSG, e, BaseException.CONNECT_ERROR);
                } else if (e instanceof InterruptedIOException) {
                    //  连接超时
                    be = new BaseException(BaseException.CONNECT_TIMEOUT_MSG, e, BaseException.CONNECT_TIMEOUT);
                } else if (e instanceof JSONException
                        || e instanceof ParseException) {
                    //  解析错误
                    be = new BaseException(BaseException.PARSE_ERROR_MSG, e, BaseException.PARSE_ERROR);
                } else {
                    be = new BaseException(BaseException.OTHER_MSG, e, BaseException.OTHER);
                }
            }
        } else {
            be = new BaseException(BaseException.OTHER_MSG, e, BaseException.OTHER);
        }

        onError(be.getErrorMsg());
    }

    @Override
    public void onComplete() {
    }

    /**
     * 成功
     *
     * @param o o
     */
    public abstract void onSuccess(T o);

    /**
     * 失败
     *
     * @param msg msg
     */
    public abstract void onError(String msg);
}
