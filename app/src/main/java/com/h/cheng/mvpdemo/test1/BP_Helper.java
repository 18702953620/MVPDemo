package com.h.cheng.mvpdemo.test1;

import com.h.cheng.mvpdemo.api.ApiRetrofit;
import com.h.cheng.mvpdemo.api.ApiServer;
import com.h.cheng.mvpdemo.test.BOHelper;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者： ch
 * 时间： 2018/6/2 0002-下午 3:57
 * 描述：
 * 来源：
 */


public class BP_Helper<V extends BV_Helper> {

    private CompositeDisposable compositeDisposable;
    public V helper;


    protected ApiServer apiServer = ApiRetrofit.getInstance().getApiService();

    public BP_Helper(V helper) {
        this.helper = helper;
    }

    /**
     * 解除绑定
     */
    public void detachView() {
        removeDisposable();
    }


    public void addDisposable(Flowable<?> observable, BOHelper observer) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer));


    }

    public void removeDisposable() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }
}
