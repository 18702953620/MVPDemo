package com.h.cheng.mvpdemo.baseimpl;

import android.support.v7.app.AppCompatActivity;

import com.h.cheng.mvpdemo.base.BaseModel;

/**
 * 作者： ch
 * 时间： 2018/9/28 0028-上午 10:10
 * 描述： 实现业务层的所有接口，具体的业务层 就可以挑选必须的接口实现，非必须就可以不实现，简化base层和业务层
 * 来源：
 */

public class AC_Base_Impl extends AppCompatActivity implements BVImpl {


    @Override
    public void showLoading() {

    }

    @Override
    public void showLoadingFileDialog() {

    }

    @Override
    public void hideLoadingFileDialog() {

    }

    @Override
    public void onProgress(long totalSize, long downSize) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void onErrorCode(BaseModel model) {

    }
}
