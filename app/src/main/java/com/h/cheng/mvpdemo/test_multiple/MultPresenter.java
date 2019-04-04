package com.h.cheng.mvpdemo.test_multiple;

import android.support.v4.util.ArrayMap;

import com.h.cheng.mvpdemo.api.ApiServer;
import com.h.cheng.mvpdemo.base.BaseObserver;
import com.h.cheng.mvpdemo.base.BasePresenter;
import com.h.cheng.mvpdemo.test_json.ShareModel;

import java.util.List;

/**
 * 作者： ch
 * 时间： 2019/4/4 0004-下午 4:59
 * 描述：
 * 来源：
 */

public class MultPresenter extends BasePresenter<MultView> {

    private ApiServer apiServer = ApiRetrofit2.getInstance().getApiService();

    public MultPresenter(MultView baseView) {
        super(baseView);
    }

    /**
     * 获取微分享列表
     */
    public void getShareList() {
        ArrayMap<String, String> map = new ArrayMap<>();
        map.put("ctl", "wshare");
        map.put("act", "wshareList");
        map.put("page", "1");

        ApiServer apiServer = ApiRetrofit2.getInstance().getApiService();


        addDisposable(apiServer.getShareList(map), new BaseObserver<List<ShareModel>>(baseView) {
            @Override
            public void onSuccess(List<ShareModel> o) {
                baseView.onGetShareListSucc(o);
            }

            @Override
            public void onError(String msg) {
                baseView.showError(msg);
            }
        });
    }

    /**
     * 获取微分享列表
     */
    public void getShareList2() {
        ArrayMap<String, String> map = new ArrayMap<>();
        map.put("ctl", "wshare");
        map.put("act", "wshareList");
        map.put("page", "1");

        ApiServer apiServer = ApiRetrofit2.getInstance().getApiService2();


        addDisposable(apiServer.getShareList(map), new BaseObserver<List<ShareModel>>(baseView) {
            @Override
            public void onSuccess(List<ShareModel> o) {
                baseView.onGetShareListSucc(o);
            }

            @Override
            public void onError(String msg) {
                baseView.showError(msg);
            }
        });
    }

    /**
     * 获取微分享列表
     */
    public void getShareList3() {
        ArrayMap<String, String> map = new ArrayMap<>();
        map.put("ctl", "wshare");
        map.put("act", "wshareList");
        map.put("page", "1");

        addDisposable(apiServer.getShareList2(map), new BaseObserver<List<ShareModel>>(baseView) {
            @Override
            public void onSuccess(List<ShareModel> o) {
                baseView.onGetShareListSucc(o);
            }

            @Override
            public void onError(String msg) {
                baseView.showError(msg);
            }
        });
    }

    /**
     * 获取微分享列表
     */
    public void getShareList4() {
        ArrayMap<String, String> map = new ArrayMap<>();
        map.put("ctl", "wshare");
        map.put("act", "wshareList");
        map.put("page", "1");

        addDisposable(apiServer.getShareList3(map), new BaseObserver<List<ShareModel>>(baseView) {
            @Override
            public void onSuccess(List<ShareModel> o) {
                baseView.onGetShareListSucc(o);
            }

            @Override
            public void onError(String msg) {
                baseView.showError(msg);
            }
        });
    }
}
