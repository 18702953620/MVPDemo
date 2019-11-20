package com.h.cheng.mvp.down;

import io.reactivex.observers.DefaultObserver;

/**
 * 作者： ch
 * 时间： 2019/10/23 11:16
 * 描述： 下载回调
 * 来源：
 */
public abstract class FileDownLoadObserver<T> extends DefaultObserver<T> {


    @Override
    public void onNext(T t) {
        onDownLoadSuccess(t);
    }

    @Override
    public void onError(Throwable throwable) {
        onDownLoadFail(throwable);
    }

    @Override
    public void onComplete() {

    }


    //下载成功的回调
    public abstract void onDownLoadSuccess(T t);

    //下载失败回调
    public abstract void onDownLoadFail(Throwable throwable);

}
