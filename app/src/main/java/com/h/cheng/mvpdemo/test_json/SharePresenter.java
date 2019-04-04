package com.h.cheng.mvpdemo.test_json;

import android.support.v4.util.ArrayMap;

import com.h.cheng.mvpdemo.base.BaseObserver;
import com.h.cheng.mvpdemo.base.BasePresenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者： ch
 * 时间： 2019/2/15 0015-上午 11:46
 * 描述：
 * 来源：
 */

public class SharePresenter extends BasePresenter<ShareView> {
    public SharePresenter(ShareView baseView) {
        super(baseView);
    }


    /**
     * 观看历史
     */
    public void getWatchHistory() {
        Map<String, Object> params = new HashMap<>();
        addDisposable(apiServer.watchHistory(params), new BaseObserver<List<WatchRecordModel>>(baseView) {
            @Override
            public void onSuccess(List<WatchRecordModel> o) {
                baseView.onWatchRecord(o);
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
    public void getShareList() {
        ArrayMap<String, String> map = new ArrayMap<>();
        map.put("ctl", "wshare");
        map.put("act", "wshareList");
        map.put("page", "1");

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
}
