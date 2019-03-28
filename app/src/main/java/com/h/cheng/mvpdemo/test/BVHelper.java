package com.h.cheng.mvpdemo.test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.h.cheng.mvpdemo.base.BaseModel;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者： ch
 * 时间： 2018/6/2 0002-下午 3:48
 * 描述：
 * 来源：
 */


public abstract class BVHelper<P extends BPHelper> {

    protected int layRes;
    protected Activity activity;
    protected View view;
    private Unbinder unbinder;
    private P presenter;

    protected abstract void initView();

    protected abstract void initData();

    protected abstract P createPresenter();


    public int getLayRes() {
        return layRes;
    }

    public View getView() {
        return view;
    }


    public BVHelper(@LayoutRes int layRes, @NonNull Activity activity) {
        this.layRes = layRes;
        this.activity = activity;
        presenter = createPresenter();
        init();
        initView();
        initData();
    }

    private void init() {
        view = LayoutInflater.from(activity).inflate(layRes, null);
        unbinder = ButterKnife.bind(this, view);
    }

    private void showToast(String str) {
        Toast.makeText(activity, str, Toast.LENGTH_SHORT).show();
    }


    void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        activity = null;

        if (presenter != null) {
            presenter.detachView();
        }
    }

    public void showError(String msg) {
        showToast(msg);
    }

    public void onResume() {

    }

    public void onPause() {
    }

    public void showLoading() {
    }

    public void onErrorCode(BaseModel model) {
    }

    public void hideLoading() {
    }

    /**
     * 跳转界面
     *
     * @param cls
     */
    public void startAC(@NonNull Class<?> cls) {
        if (cls == null) {
            return;
        }
        Intent intent = new Intent(activity, cls);
        activity.startActivity(intent);
    }

    /**
     * 关闭
     */
    public void finish() {
        if (activity != null) {
            activity.finish();
        }
    }

    /**
     * 设置 文字
     *
     * @param tv
     * @param text
     */
    public void setText(TextView tv, String text) {
        if (tv == null) {
            return;
        }
        if (TextUtils.isEmpty(text)) {
            tv.setText("");
        } else {
            tv.setText(text);
        }
    }

    /**
     * 获取文字
     *
     * @param tv
     * @return
     */
    public String getText(TextView tv) {
        if (tv == null) {
            return null;
        } else {
            return tv.getText().toString();
        }
    }

    /**
     * 设置图片
     *
     * @param img
     * @param path
     */
    public void setImg(ImageView img, String path) {
        if (img == null) {
            return;
        }
        //
    }

    /**
     * 设置图片
     *
     * @param img
     * @param path
     */
    public void setImgNoEmpty(ImageView img, String path) {
        if (img == null) {
            return;
        }
        if (TextUtils.isEmpty(path)) {
            return;
        }
    }


}
