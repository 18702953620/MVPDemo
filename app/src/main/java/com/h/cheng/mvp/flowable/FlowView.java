package com.h.cheng.mvp.flowable;


import com.h.cheng.mvp.base.BaseView;
import com.h.cheng.mvp.model.ArticleModel;

import java.util.List;

/**
 * 作者： ch
 * 时间： 2019/11/19 17:57
 * 描述：
 * 来源：
 */
public interface FlowView extends BaseView {
    void onGetListSucc(List<ArticleModel> o);
}
