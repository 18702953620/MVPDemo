package com.h.cheng.mvpdemo.livedata.base;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * 作者： ch
 * 时间： 2019/11/5 10:30
 * 描述：
 * 来源：
 */
public class BaseViewModel<T> extends ViewModel {

    public MutableLiveData<T> modelList = new MutableLiveData<>();

    public static final int ERRORCODE = 10000;

    protected LiveApiServer apiServer = LiveApiRetrofit.getInstance().getApiService();


}
