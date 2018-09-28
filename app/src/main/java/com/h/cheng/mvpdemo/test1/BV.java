package com.h.cheng.mvpdemo.test1;

import com.h.cheng.mvpdemo.base.BaseModel;

/**
 * 作者： ch
 * 时间： 2016/12/27.13:56
 * 描述：
 * 来源：
 */

public interface BV {
    /**
     * 显示dialog
     */
    void showLoading();

    /**
     * 显示下载文件dialog
     */

    void showLoadingFileDialog();

    /**
     * 隐藏下载文件dialog
     */

    void hideLoadingFileDialog();

    /**
     * 下载进度
     *
     * @param totalSize
     * @param downSize
     */

    void onProgress(long totalSize, long downSize);

    /**
     * 隐藏 dialog
     */

    void hideLoading();

    /**
     * 显示错误信息
     *
     * @param msg
     */
    void showError(String msg);

    /**
     * 错误码
     */
    void onErrorCode(BaseModel model);


}
