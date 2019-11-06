package com.h.cheng.mvpdemo.livedata;

import com.h.cheng.mvpdemo.livedata.base.BaseModel;
import com.h.cheng.mvpdemo.livedata.base.BaseObserver;
import com.h.cheng.mvpdemo.livedata.base.BaseViewModel;
import com.h.cheng.mvpdemo.livedata.model.BannerModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者： ch
 * 时间： 2019/11/4 14:25
 * 描述：
 * 来源：
 */
public class BannerViewModel extends BaseViewModel<BaseModel<BannerModel>> {


    protected BannerViewModel loadBanner() {
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
        return this;
    }


}
