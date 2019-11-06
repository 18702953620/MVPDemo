package com.h.cheng.mvpdemo.livedata.base;


import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;

import com.h.cheng.mvpdemo.base.BaseObserver;
import com.h.cheng.mvpdemo.base.BaseView;

import io.reactivex.Observable;

/**
 * 作者： ch
 * 时间： 2016/12/27.13:56
 * 描述：
 * 来源：
 */

public class BaseLivePresenter<T> {

    protected LifecycleOwner owner;

    public static final int ERRORCODE = 10000;

    protected LiveApiServer apiServer = LiveApiRetrofit.getInstance().getApiService();

    public MutableLiveData<T> modelList = new MutableLiveData<>();

    public BaseLivePresenter(LifecycleOwner owner) {
        this.owner = owner;
    }


}
