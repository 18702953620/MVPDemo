package com.h.cheng.mvpdemo.livedata;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.h.cheng.mvpdemo.R;
import com.h.cheng.mvpdemo.livedata.base.BaseLiveActivity;
import com.h.cheng.mvpdemo.livedata.base.BaseModel;
import com.h.cheng.mvpdemo.livedata.base.LiveApiRetrofit;
import com.h.cheng.mvpdemo.livedata.base.LiveApiServer;
import com.h.cheng.mvpdemo.livedata.model.BannerModel;


public class LiveDemoActivity extends BaseLiveActivity<LiveDataPresenter> {

    @Override
    protected LiveDataPresenter createPresenter() {
        return new LiveDataPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_live_demo;
    }

    @Override
    protected void initView() {
        providers.get(BannerViewModel.class).loadBanner();
        presenter.getBannerList();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
