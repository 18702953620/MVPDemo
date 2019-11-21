package com.h.cheng.mvp.base;


import com.h.cheng.mvp.api.ApiRetrofit;
import com.h.cheng.mvp.api.ApiServer;
import com.h.cheng.mvp.flowable.BaseSubscriber;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者： ch
 * 时间： 2016/12/27.13:56
 * 描述：
 * 来源：
 */

public class BasePresenter<V extends BaseView> {

    public CompositeDisposable compositeDisposable;


    public V baseView;

    protected ApiServer apiServer = ApiRetrofit.getInstance().getApiService();

    public BasePresenter(V baseView) {
        this.baseView = baseView;
    }

    /**
     * 解除绑定
     */
    public void detachView() {
        baseView = null;
        removeDisposable();
    }

    public void addDisposable(Observable<?> flowable, BaseObserver observer) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(observer));

    }

    public void addDisposable(Flowable<?> flowable, BaseSubscriber subscriber) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(subscriber));

    }

    public void removeDisposable() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }
}
