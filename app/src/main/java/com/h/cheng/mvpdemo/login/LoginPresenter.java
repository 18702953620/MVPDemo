package com.h.cheng.mvpdemo.login;

import com.h.cheng.mvpdemo.base.BaseObserver;
import com.h.cheng.mvpdemo.base.BasePresenter;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

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

        File file = new File(path);
        //  图片参数
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("Files", file.getName(), requestFile);
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
