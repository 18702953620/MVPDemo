package com.h.cheng.mvpdemo.test;

/**
 * 作者： ch
 * 时间： 2018/6/2 0002-下午 3:54
 * 描述：
 * 来源：
 */


public class AC_Login extends AC_Base {

    @Override
    protected BVHelper createView() {
        return new V_Login(this);
    }

}
