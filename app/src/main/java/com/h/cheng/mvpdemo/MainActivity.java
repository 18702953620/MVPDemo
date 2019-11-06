package com.h.cheng.mvpdemo;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.h.cheng.mvpdemo.base.BaseActivity;
import com.h.cheng.mvpdemo.downfile.FilePresenter;
import com.h.cheng.mvpdemo.livedata.LiveDemoActivity;
import com.h.cheng.mvpdemo.login.LoginActivity;
import com.h.cheng.mvpdemo.test_json.ShareActivity;
import com.h.cheng.mvpdemo.test_multiple.MultipleActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.btn_down)
    Button btnDown;
    @BindView(R.id.btn_apk)
    Button btnApk;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_share)
    Button btnShare;
    @BindView(R.id.btn_url)
    Button btnUrl;
    private Button btn_down;


    @Override
    protected FilePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @OnClick({R.id.btn_down, R.id.btn_apk, R.id.btn_login, R.id.btn_share, R.id.btn_url, R.id.btn_live})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //下载 断点下载
            case R.id.btn_down:
                startActivity(new Intent(context, DownActivity.class));
                break;
            //打开apk
            case R.id.btn_apk:
                startActivity(new Intent(context, APKActivity.class));
                break;
            //mvp 演示
            case R.id.btn_login:
                startActivity(new Intent(context, LoginActivity.class));
                break;
            //json 数据预处理
            case R.id.btn_share:
                startActivity(new Intent(context, ShareActivity.class));
                break;
            //多url
            case R.id.btn_url:
                startActivity(new Intent(context, MultipleActivity.class));
                break;
            //live data
            case R.id.btn_live:
                startActivity(new Intent(context, LiveDemoActivity.class));
                break;
        }
    }
}
