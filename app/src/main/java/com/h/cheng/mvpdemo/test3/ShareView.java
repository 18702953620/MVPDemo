package com.h.cheng.mvpdemo.test3;

import com.h.cheng.mvpdemo.base.BaseView;

import java.util.List;

/**
 * 作者： ch
 * 时间： 2019/2/15 0015-上午 11:46
 * 描述：
 * 来源：
 */

public interface ShareView extends BaseView {
    void onGetShareListSucc(List<ShareModel> o);

    void onWatchRecord(List<WatchRecordModel> o);
}
