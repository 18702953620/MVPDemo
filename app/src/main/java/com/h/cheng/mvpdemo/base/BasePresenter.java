package com.h.cheng.mvpdemo.base;


import com.h.cheng.mvpdemo.api.ApiRetrofit;
import com.h.cheng.mvpdemo.api.ApiServer;

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

    private CompositeDisposable compositeDisposable;

    public BasePresenter(V baseView) {
        attachView(baseView);
    }

    public V baseView;

    protected ApiServer apiServer = ApiRetrofit.getInstance().getApiService();

    /**
     * 绑定 view
     *
     * @param view
     */
    public void attachView(V view) {
        this.baseView = view;
    }

    /**
     * 解除绑定
     */
    public void detachView() {
        baseView = null;
        removeDisposable();
    }

    /**
     * 判断是否绑定
     *
     * @return
     */
    public boolean isAttachView() {
        return baseView != null;
    }

    /**
     * 返回 view
     *
     * @return
     */
    public V getBaseView() {
        return baseView;
    }


    public void addDisposable(Observable<?> observable, BaseObserver observer) {
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

    /**
     * 检查是否绑定
     */
    public void checkViewAttach() {
        if (!isAttachView()) {
            throw new MvpViewNotAttachedException();
        }
    }

    /**
     * 自定义异常
     */
    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("请求数据前请先调用 attachView(View) 方法与View建立连接");
        }
    }

}
