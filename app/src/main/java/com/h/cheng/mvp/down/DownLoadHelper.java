package com.h.cheng.mvp.down;

import android.text.TextUtils;


import com.h.cheng.mvp.api.ApiServer;
import com.h.cheng.mvp.model.DownModel;
import com.h.cheng.mvp.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;


/**
 * 作者： ch
 * 时间： 2019/10/23 10:52
 * 描述： 下载
 * 来源：
 */
public class DownLoadHelper {
    // 超时15s
    private static final int DEFAULT_TIMEOUT = 15;
    // 网络工具retrofit
    private Retrofit retrofit;
    // 下载进度、完成、失败等的回调事件
    private List<DownloadListener> mDownloadListeners;

    private static DownLoadHelper loadHelper;


    public static DownLoadHelper getInstance() {
        synchronized (Object.class) {
            if (loadHelper == null) {
                loadHelper = new DownLoadHelper();
            }
        }
        return loadHelper;
    }

    public void addDownLoadListener(DownloadListener listener) {
        if (null == mDownloadListeners) {
            mDownloadListeners = new ArrayList<>();
        }
        if (!mDownloadListeners.contains(listener)) {
            mDownloadListeners.add(listener);
        }
    }

    public void removeDownLoadListener(DownloadListener listener) {
        if (null != mDownloadListeners && mDownloadListeners.size() > 0) {
            mDownloadListeners.remove(listener);
        }
    }


    private void initClient(final DownModel downModel) {
        //创建客户端
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {

                        Request request = chain.request();
                        if (downModel.getDownSize() != 0 && downModel.getTotalSize() != 0) {
                            request = request.newBuilder()
                                    .addHeader("RANGE", "bytes=" + downModel.getDownSize() + "-" + downModel.getTotalSize()).build();
                        }
                        Response response = chain.proceed(request);
                        return response.newBuilder().body(new DownloadResponseBody(response.body(),
                                new DownloadResponseBody.ProgressListener() {
                                    @Override
                                    public void onProgress(long totalSize, long downSize) {

                                        long currentDown = downSize + downModel.getTotalSize() - totalSize;
                                        downModel.setDownSize(currentDown);
                                        if (downModel.getTotalSize() == 0) {
                                            downModel.setTotalSize(totalSize);
                                        }

                                        if (null != mDownloadListeners && mDownloadListeners.size() > 0) {
                                            for (DownloadListener listener : mDownloadListeners) {
                                                listener.onProgress(downModel.getTag(), (int) (currentDown * 100 / totalSize));
                                            }
                                        }

                                    }
                                })).build();
                    }
                })
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://www.baidu.com/")
                .client(httpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

    }

    /**
     * @param url      文件网络地址
     * @param destDir  目标目录
     * @param fileName 文件名
     */
    public void downLoadFile(String url, String destDir, String fileName) {
        if (TextUtils.isEmpty(url)
                || TextUtils.isEmpty(destDir) || TextUtils.isEmpty(fileName)) {
            return;
        }
        DownModel downModel = new DownModel();
        downModel.setUrl(url);
        downModel.setName(fileName);
        downModel.setDestDir(destDir);
        //保存
//        downModel.save();

        downLoadFile(downModel);
    }


    /**
     * @param downModel 下载实体类
     */
    public void downLoadFile(final DownModel downModel) {

        initClient(downModel);

        if (null != mDownloadListeners && mDownloadListeners.size() > 0) {
            for (DownloadListener listener : mDownloadListeners) {
                listener.onStartDownload(downModel.getUrl());
            }
        }
        retrofit.create(ApiServer.class)
                .downloadFile(downModel.getUrl())

                .map(new Function<ResponseBody, File>() {
                    @Override
                    public File apply(ResponseBody responseBody) throws Exception {
                        if (downModel.getDownSize() > 0) {
                            //断点下载
                            return FileUtils.saveFile(responseBody.byteStream(), downModel.getDownSize(), downModel.getDestDir(), downModel.getName());
                        }
                        return FileUtils.saveFile(responseBody.byteStream(), downModel.getDestDir(), downModel.getName());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new FileObserver() {
                    @Override
                    public void onSuccess(File file) {
                        downModel.setState(DownModel.DOWN_FINISH);
                        downModel.setFileSize(FileUtils.getFormatSize(file.length()));
                        //保存
//                        downModel.save();

                        if (null != mDownloadListeners && mDownloadListeners.size() > 0) {
                            for (DownloadListener listener : mDownloadListeners) {
                                listener.onFinishDownload(downModel.getUrl(), file);
                            }
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        downModel.setState(DownModel.DOWN_FAIL);
                        //保存
//                        downModel.save();

                        if (null != mDownloadListeners && mDownloadListeners.size() > 0) {
                            for (DownloadListener listener : mDownloadListeners) {
                                listener.onFail(downModel.getUrl(), msg);
                            }
                        }
                    }
                });
    }


}
