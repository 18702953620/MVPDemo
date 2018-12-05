package com.h.cheng.mvpdemo.downfile;

/**
 * 作者： ch
 * 时间： 2018/8/29 0029-上午 10:07
 * 描述：
 * 来源：
 */

public interface DownFileCallback {

    void onSuccess(String path);

    void onFail(String msg);

    void onProgress(long totalSize, long downSize);
}
