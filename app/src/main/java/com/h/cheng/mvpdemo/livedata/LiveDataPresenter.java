package com.h.cheng.mvpdemo.livedata;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.h.cheng.mvpdemo.base.BaseView;
import com.h.cheng.mvpdemo.livedata.base.BaseLivePresenter;
import com.h.cheng.mvpdemo.livedata.base.BaseModel;
import com.h.cheng.mvpdemo.livedata.base.BaseObserver;
import com.h.cheng.mvpdemo.livedata.model.BannerModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者： ch
 * 时间： 2019/11/4 14:22
 * 描述：
 * 来源：
 */
public class LiveDataPresenter extends BaseLivePresenter<BaseModel<BannerModel>> {


    public LiveDataPresenter(LifecycleOwner owner) {
        super(owner);
    }

    public void getBannerList() {
        apiServer.getBannerList1()
                .subscribeOn(Schedulers.io())
                //事件回调线程为主线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseModel<BannerModel>>() {
                    @Override
                    public void onSuccess(BaseModel<BannerModel> o) {
                        modelList.postValue(o);
                    }

                    @Override
                    public void onError(String msg) {
                        modelList.postValue(new BaseModel<BannerModel>(ERRORCODE, msg));
                    }
                });


    }
}
