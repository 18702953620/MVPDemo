package com.h.cheng.mvpdemo.livedata.base;

import android.arch.lifecycle.LiveData;

import com.h.cheng.mvpdemo.livedata.model.BannerModel;

import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * 作者： ch
 * 时间： 2019/11/1 17:34
 * 描述：
 * 来源：
 */
public interface LiveApiServer {
    /**
     * @return
     */
    @GET("https://www.wanandroid.com/banner/json")
    LiveData<BaseModel<BannerModel>> getBannerList();

    /**
     * @return
     */
    @GET("https://www.wanandroid.com/banner/json1")
    Flowable<BaseModel<BannerModel>> getBannerList1();

}
