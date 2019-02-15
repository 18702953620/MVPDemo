package com.h.cheng.mvpdemo.login;

import android.util.Log;

import com.h.cheng.mvpdemo.base.BaseObserver;
import com.h.cheng.mvpdemo.base.BasePresenter;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 作者： ch
 * 时间： 2018/3/21 0021-下午 4:13
 * 描述：
 * 来源：
 */


public class LoginPresenter extends BasePresenter<LoginView> {
    public LoginPresenter(LoginView baseView) {
        super(baseView);
    }

    public void login(String name, String pwd) {

        addDisposable(apiServer.LoginByRx(name, pwd), new BaseObserver(baseView) {
            @Override
            public void onSuccess(Object o) {
                baseView.onLoginSucc();

            }

            @Override
            public void onError(String msg) {
                baseView.showError(msg);

            }
        });
    }

    public void regex(String tel) {

        addDisposable(apiServer.regex(tel), new BaseObserver(baseView) {
            @Override
            public void onSuccess(Object o) {
                baseView.onLoginSucc();

            }

            @Override
            public void onError(String msg) {
                baseView.showError(msg);

            }
        });
    }

    public void createRoom(String path) {

        addDisposable(apiServer.createRoom(path), new BaseObserver(baseView) {
            @Override
            public void onSuccess(Object o) {
                baseView.onLoginSucc();

            }

            @Override
            public void onError(String msg) {
                baseView.showError(msg);

            }
        });
    }

    public void upload(String path) {

        String url = "http://wwww.baidu.com";
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("1111", "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("1111", "onResponse: " + response.body().string());
            }
        });

        File file = new File(path);
        //  图片参数
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("uploadFile", file.getName(), requestFile);
        addDisposable(apiServer.upload(body), new BaseObserver(baseView) {
            @Override
            public void onSuccess(Object o) {
                baseView.onLoginSucc();

            }

            @Override
            public void onError(String msg) {
                baseView.showError(msg);

            }
        });
    }


}
