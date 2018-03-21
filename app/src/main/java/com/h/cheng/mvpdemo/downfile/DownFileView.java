package com.h.cheng.mvpdemo.downfile;


import com.h.cheng.mvpdemo.base.BaseView;

import java.io.File;

/**
 * 作者： ch
 * 时间： 2018/3/20 0020-下午 2:42
 * 描述：
 * 来源：
 */


public interface DownFileView extends BaseView {

    void onSuccess(File file);

    void onError(String msg);

}
