package com.h.cheng.mvp.livedata.base;

/**
 * @author ch
 * 时间： 2016/12/27.13:56
 * 描述：
 * 来源：
 */

public interface BaseViewHelper {
    /**
     * 显示dialog
     */
    void showLoading();


    /**
     * 隐藏 dialog
     */

    void hideLoading();

    /**
     * 显示错误信息
     *
     * @param msg msg
     */
    void showError(String msg);

    /**
     * 错误码
     *
     * @param code code
     * @param msg  msg
     */
    void onErrorCode(int code, String msg);


}
