package com.h.cheng.mvp.livedata;

import android.app.Application;

import androidx.annotation.NonNull;

import com.h.cheng.mvp.livedata.base.BaseLiveSubscriber;
import com.h.cheng.mvp.livedata.base.BaseViewModel;
import com.h.cheng.mvp.model.ArticleModel;

import java.util.List;

/**
 * @author ch
 * @date 2020/5/25-17:35
 * @desc
 */
public class HomeViewModel extends BaseViewModel {
    public HomeViewModel(@NonNull Application application) {
        super(application);
    }


    public void getData() {
        addDisposable(apiServer.getFWxArticleList(), new BaseLiveSubscriber<List<ArticleModel>>() {

            @Override
            public void onSuccess(List<ArticleModel> modelList) {
                post(modelList, "json");
            }

            @Override
            public void onError(String msg) {
                postFail("json", msg);
            }
        });
    }
}
