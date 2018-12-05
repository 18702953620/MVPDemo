package com.h.cheng.mvpdemo;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.h.cheng.mvpdemo.base.BaseActivity;
import com.h.cheng.mvpdemo.downfile.FilePresenter;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button btn_down;


    @Override
    protected FilePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    public void initView() {
        btn_down = findViewById(R.id.btn_down);
        btn_down.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_down:
                //MVP
//                startActivity(new Intent(context, LoginActivity.class));
                //下载
                startActivity(new Intent(context, DownActivity.class));
                //apk安装
//                startActivity(new Intent(context, APKActivity.class));
                break;
        }
    }

}
