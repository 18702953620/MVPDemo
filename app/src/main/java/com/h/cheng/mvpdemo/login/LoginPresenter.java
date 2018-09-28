package com.h.cheng.mvpdemo.login;

import com.h.cheng.mvpdemo.base.BaseObserver;
import com.h.cheng.mvpdemo.base.BasePresenter;

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


}
