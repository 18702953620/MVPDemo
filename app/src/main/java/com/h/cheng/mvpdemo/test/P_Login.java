package com.h.cheng.mvpdemo.test;

/**
 * 作者： ch
 * 时间： 2018/6/4 0004-上午 11:29
 * 描述：
 * 来源：
 */


public class P_Login extends BPHelper<V_Login> {


    public P_Login(V_Login helper) {
        super(helper);
    }

    public void login(String name, String pwd) {
        addDisposable(apiServer.test(), new BOHelper(helper) {
            @Override
            public void onSuccess(Object o) {
                helper.loginSucc();

            }

            @Override
            public void onError(String msg) {
                helper.showError(msg);
            }
        });
    }
}
