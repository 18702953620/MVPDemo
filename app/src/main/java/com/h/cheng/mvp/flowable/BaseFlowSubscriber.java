package com.h.cheng.mvp.flowable;

import com.google.gson.JsonParseException;
import com.h.cheng.mvp.api.BaseException;
import com.h.cheng.mvp.base.BaseView;

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
 * 时间： 2019/11/19 16:27
 * 描述：
 * 来源：
 */
public abstract class BaseFlowSubscriber<T> implements FlowableSubscriber<T> {

    protected BaseView view;

    private boolean isShowDialog;

    public BaseFlowSubscriber(BaseView view) {
        this.view = view;
    }

    public BaseFlowSubscriber(BaseView view, boolean isShowDialog) {
        this.view = view;
        this.isShowDialog = isShowDialog;
    }

    @Override
    public void onSubscribe(Subscription s) {
        if (view != null && isShowDialog) {
            view.showLoading();
        }
        s.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        if (view != null && isShowDialog) {
            view.hideLoading();
        }
        BaseException be = null;

        if (e != null) {

            if (e instanceof BaseException) {
                be = (BaseException) e;

                //回调到view层 处理 或者根据项目情况处理
                if (view != null) {
                    view.onErrorCode(be.getErrorCode(), be.getErrorMsg());
                } else {
                    onError(be.getErrorMsg());
                }

            } else {
                if (e instanceof HttpException) {
                    //   HTTP错误
                    be = new BaseException(BaseException.BAD_NETWORK_MSG, e, BaseException.BAD_NETWORK);
                } else if (e instanceof ConnectException
                        || e instanceof UnknownHostException) {
                    //   连接错误
                    be = new BaseException(BaseException.CONNECT_ERROR_MSG, e, BaseException.CONNECT_ERROR);
                } else if (e instanceof InterruptedIOException) {
                    //  连接超时
                    be = new BaseException(BaseException.CONNECT_TIMEOUT_MSG, e, BaseException.CONNECT_TIMEOUT);
                } else if (e instanceof JsonParseException
                        || e instanceof JSONException
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
        if (view != null && isShowDialog) {
            view.hideLoading();
        }
    }


    public abstract void onSuccess(T o);

    public abstract void onError(String msg);
}
