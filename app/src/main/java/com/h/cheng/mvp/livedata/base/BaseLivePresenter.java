package com.h.cheng.mvp.livedata.base;


import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;

import com.h.cheng.mvp.api.ApiServer;


/**
 * 作者： ch
 * 时间： 2016/12/27.13:56
 * 描述：
 * 来源：
 */

public class BaseLivePresenter<T> {

    protected LifecycleOwner owner;

    public static final int ERRORCODE = 10000;

    protected ApiServer apiServer = LiveApiRetrofit.getInstance().getApiService();

    public MutableLiveData<T> modelList = new MutableLiveData<>();

    public BaseLivePresenter(LifecycleOwner owner) {
        this.owner = owner;
    }


}
