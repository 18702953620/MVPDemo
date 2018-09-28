package com.h.cheng.mvpdemo.baseimpl;

import android.content.Context;
import android.os.Bundle;

import com.h.cheng.mvpdemo.base.BasePresenter;

/**
 * 作者： ch
 * 时间： 2018/9/28 0028-上午 10:11
 * 描述：
 * 来源：
 */

public abstract class AC_Base<P extends BasePresenter> extends AC_Base_Impl {

    public Context context;
    protected P presenter;

    protected abstract P createPresenter();

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void addListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(getLayoutId());
        presenter = createPresenter();
        initView();
        addListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }
    }

}
