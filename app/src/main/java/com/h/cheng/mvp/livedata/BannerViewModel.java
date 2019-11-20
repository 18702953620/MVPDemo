package com.h.cheng.mvp.livedata;


import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.h.cheng.mvp.livedata.base.BaseModel;
import com.h.cheng.mvp.livedata.base.BaseViewModel;
import com.h.cheng.mvp.model.BannerModel;


/**
 * 作者： ch
 * 时间： 2019/11/4 14:25
 * 描述：
 * 来源：
 */
public class BannerViewModel extends BaseViewModel<BaseModel<BannerModel>> {



    protected BannerViewModel loadBanner(LifecycleOwner owner) {
        apiServer.getBannerList().observe(owner, new Observer<BaseModel<BannerModel>>() {
            @Override
            public void onChanged(BaseModel<BannerModel> o) {
                modelList.postValue(o);
            }
        });
        return this;
    }


}
