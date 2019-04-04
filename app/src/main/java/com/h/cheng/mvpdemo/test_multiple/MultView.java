package com.h.cheng.mvpdemo.test_multiple;

import com.h.cheng.mvpdemo.base.BaseView;
import com.h.cheng.mvpdemo.test_json.ShareModel;

import java.util.List;

/**
 * 作者： ch
 * 时间： 2019/4/4 0004-下午 5:00
 * 描述：
 * 来源：
 */

public interface MultView extends BaseView {
    void onGetShareListSucc(List<ShareModel> o);
}
