package com.h.cheng.mvp.livedata.base;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.h.cheng.mvp.api.ApiServer;


/**
 * 作者： ch
 * 时间： 2019/11/5 10:30
 * 描述：
 * 来源：
 */
public class BaseViewModel<T> extends ViewModel {


    public MutableLiveData<T> modelList = new MutableLiveData<>();

    public static final int ERRORCODE = 10000;

    protected ApiServer apiServer = LiveApiRetrofit.getInstance().getApiService();


}
