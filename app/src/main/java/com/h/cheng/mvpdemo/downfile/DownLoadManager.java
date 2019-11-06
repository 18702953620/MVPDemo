package com.h.cheng.mvpdemo.downfile;

import android.os.Environment;
import android.util.Log;

import com.h.cheng.mvpdemo.api.ApiServer;
import com.h.cheng.mvpdemo.utils.FileUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * 作者： ch
 * 时间： 2018/12/5 0005-上午 11:08
 * 描述：
 * 来源：
 */

public class DownLoadManager {

    private static DownLoadManager loadManager;

    private HashMap<String, FileObserver> hashMap;
    private OkHttpClient client;
    private Retrofit retrofit;
    private ApiServer apiServer;
    private DownFileCallback fileCallback;
    private String currentUrl;
    private String currentPath;

    private long downSize;
    private long totalSize;

    public void setFileCallback(DownFileCallback fileCallback) {
        this.fileCallback = fileCallback;
    }

    public DownLoadManager() {
        hashMap = new HashMap<>();
        client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {

                        Request request = chain.request();
                        if (downSize != 0 && totalSize != 0) {
                            request = request.newBuilder()
                                    .addHeader("RANGE", "bytes=" + downSize + "-" + totalSize).build();
                        }

                        Response response = chain.proceed(request);
                        return response.newBuilder().body(new ProgressResponseBody(response.body(),
                                new ProgressResponseBody.ProgressListener() {
                                    @Override
                                    public void onProgress(long totalSize, long downSize) {
                                        if (fileCallback != null) {
                                            fileCallback.onProgress(totalSize, downSize);
                                        }
                                    }
                                })).build();
                    }
                }).build();
        retrofit = new Retrofit.Builder().client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://wawa-api.vchangyi.com/").build();
        apiServer = retrofit.create(ApiServer.class);
    }

    public static DownLoadManager getInstance() {
        synchronized (Object.class) {
            if (loadManager == null) {
                loadManager = new DownLoadManager();
            }
        }
        return loadManager;
    }

    /**
     * 下载单个文件
     *
     * @param downModel
     * @param
     */
    public void downFile(final DownModel downModel, final DownFileCallback fileCallback) {
        if (downModel == null) {
            return;
        }
        //如果正在下载，则暂停
        final String url = downModel.getUrl();
        if (isDownLoad(url)) {
            pause(url);
            Log.e("cheng", "pause url=" + url);
            return;
        }
        //当前链接
        currentUrl = url;
        //是否是断点下载
        if (downModel.getDownSize() != 0 && downModel.getTotalSize() != 0) {
            totalSize = downModel.getTotalSize();
            downSize = downModel.getDownSize();
            currentPath = downModel.getPath();
        } else {
            totalSize = 0;
            downSize = 0;
            currentPath = getTemporaryName(url);
            downModel.setPath(currentPath);
        }

        this.fileCallback = fileCallback;

        Log.e("cheng", "currentUrl=" + currentUrl);

        Log.e("cheng", "downSize=" + downSize + ",totalSize=" + totalSize + ",currentPath=" + currentPath);

        FileObserver observer = apiServer.downloadFile(url).map(new Function<ResponseBody, String>() {
            @Override
            public String apply(ResponseBody body) throws Exception {

                if (downModel.getDownSize() != 0 && downModel.getTotalSize() != 0) {
                    return FileUtil.saveFile(currentPath, downModel.getDownSize(), body).getPath();
                }
                File file = FileUtil.saveFile(currentPath, body);
                return file.getPath();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new FileObserver<String>() {
                    @Override
                    public void onSuccess(String path) {
                        downModel.setFinish(true);
                        downModel.setPath(path);
                        downModel.setExists(true);


                        if (fileCallback != null) {
                            fileCallback.onSuccess(path);
                        }
                        hashMap.remove(url);

                        currentUrl = null;
                    }

                    @Override
                    public void onError(String msg) {
                        if (fileCallback != null) {
                            fileCallback.onFail(msg);
                        }
                        hashMap.remove(url);
                        currentUrl = null;
                    }
                });
        //保存
        hashMap.put(url, observer);
    }

    /**
     * 下载单个文件
     *
     * @param url
     * @param fileCallback
     */
    public void downFile(final String url, final DownFileCallback fileCallback) {
        //如果正在下载，则暂停
        if (isDownLoad(url)) {
            pause(url);
            return;
        }
        this.fileCallback = fileCallback;
        //存储的文件路径
        final String path = getTemporaryName(url);

        FileObserver observer = apiServer.downloadFile(url).map(new Function<ResponseBody, String>() {
            @Override
            public String apply(ResponseBody body) throws Exception {
                File file = FileUtil.saveFile(path, body);
                return file.getPath();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new FileObserver<String>() {
                    @Override
                    public void onSuccess(String path) {

                        fileCallback.onSuccess(path);
                        hashMap.remove(url);
                    }

                    @Override
                    public void onError(String msg) {
                        fileCallback.onFail(msg);
                        hashMap.remove(url);
                    }
                });
        //保存
        hashMap.put(url, observer);
    }

    /**
     * 暂停/取消任务
     *
     * @param url 完整url
     */
    public void pause(String url) {
        if (hashMap.containsKey(url)) {
            FileObserver observer = hashMap.get(url);
            if (observer != null) {
                observer.dispose();
                hashMap.remove(url);
            }
        }
    }

    /**
     * 获取临时文件名
     *
     * @param url
     * @return
     */
    public static String getTemporaryName(String url) {
        String type = "";
        if (url.contains(".")) {
            type = url.substring(url.lastIndexOf("."));
        }
        String dirName = Environment.getExternalStorageDirectory() + "/mvp/img/";

        File f = new File(dirName);
        //不存在创建
        if (!f.exists()) {
            f.mkdirs();
        }
        return dirName + System.currentTimeMillis() + type;
    }


    /**
     * 是否在下载
     *
     * @param url
     * @return
     */
    public boolean isDownLoad(String url) {
        return hashMap.containsKey(url);
    }


    public abstract class FileObserver<T> extends DisposableSubscriber<T> {

        @Override
        public void onNext(T t) {
            onSuccess(t);
        }

        @Override
        public void onError(Throwable e) {
            onError(e.getMessage());
        }

        @Override
        public void onComplete() {

        }


        public abstract void onSuccess(T o);

        public abstract void onError(String msg);
    }

}
