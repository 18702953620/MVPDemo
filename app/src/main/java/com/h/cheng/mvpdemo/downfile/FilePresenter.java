package com.h.cheng.mvpdemo.downfile;


import com.h.cheng.mvpdemo.api.ApiServer;
import com.h.cheng.mvpdemo.base.BaseObserver;
import com.h.cheng.mvpdemo.base.BasePresenter;
import com.h.cheng.mvpdemo.utils.FileUtil;

import java.io.File;
import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * 作者： ch
 * 时间： 2016/12/27.13:56
 * 描述：文件上传、下载
 * 来源：
 */


public class FilePresenter extends BasePresenter<DownFileView> {
    public FilePresenter(DownFileView baseView) {
        super(baseView);
    }

    public void upLoadFile(String picPath, String phone, String password) {
        File file = new File(picPath);
        //  图片参数
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("uploadFile", file.getName(), requestFile);
        //  手机号参数
        RequestBody phoneBody = RequestBody.create(MediaType.parse("multipart/form-data"), phone);
//  密码参数
        RequestBody pswBody = RequestBody.create(MediaType.parse("multipart/form-data"), password);

        addDisposable(apiServer.register(phoneBody, pswBody, body), new BaseObserver<String>(baseView) {
            @Override
            public void onSuccess(String o) {
            }

            @Override
            public void onErrorMsg(String msg) {

            }
        });
    }

    public void downFile(final String url, final String path) {


        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Response response = chain.proceed(chain.request());
                        return response.newBuilder().body(new ProgressResponseBody(response.body(),
                                new ProgressResponseBody.ProgressListener() {
                                    @Override
                                    public void onProgress(long totalSize, long downSize) {
                                        baseView.onProgress(totalSize, downSize);
                                    }
                                })).build();
                    }
                }).build();

        Retrofit retrofit = new Retrofit.Builder().client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://wawa-api.vchangyi.com/").build();

        apiServer = retrofit.create(ApiServer.class);

        apiServer
                .downloadFile(url)
                .map(new Function<ResponseBody, String>() {
                    @Override
                    public String apply(ResponseBody body) throws Exception {
                        File file = FileUtil.saveFile(path, body);
                        return file.getPath();
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new FileObserver(baseView) {
                    @Override
                    public void onSuccess(File file) {
                        baseView.onSuccess(file);
                    }

                    @Override
                    public void onErrorMsg(String msg) {
                        baseView.onError(msg);
                    }
                });


    }


}
