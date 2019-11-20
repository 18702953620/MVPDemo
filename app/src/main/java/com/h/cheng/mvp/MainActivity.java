package com.h.cheng.mvp;

import android.content.Intent;
import android.view.View;
import android.widget.Button;


import com.h.cheng.mvp.base.BaseActivity;
import com.h.cheng.mvp.base.BasePresenter;
import com.h.cheng.mvp.down.DownActivity;
import com.h.cheng.mvp.flowable.FlowActivity;
import com.h.cheng.mvp.livedata.LiveDataActivity;
import com.h.cheng.mvp.mvp.MVPActivity;
import com.h.cheng.mvp.upload.UploadActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： ch
 * 时间： 2019/11/19 0015-上午 11:09
 * 描述：
 * 来源：
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.btn_mvp)
    Button btnMvp;
    @BindView(R.id.btn_down)
    Button btnDown;
    @BindView(R.id.btn_upload)
    Button btnUpload;
    @BindView(R.id.btn_data)
    Button btnData;
    @BindView(R.id.btn_url)
    Button btnUrl;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void initView() {

    }


    @OnClick({R.id.btn_mvp, R.id.btn_flow, R.id.btn_down, R.id.btn_upload, R.id.btn_data, R.id.btn_url})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_mvp:
                startActivity(new Intent(context, MVPActivity.class));
                break;
            case R.id.btn_flow:
                startActivity(new Intent(context, FlowActivity.class));
                break;
            case R.id.btn_down:
                startActivity(new Intent(context, DownActivity.class));
                break;
            //上传
            case R.id.btn_upload:
                startActivity(new Intent(context, UploadActivity.class));

                break;
            case R.id.btn_data:
                startActivity(new Intent(context, LiveDataActivity.class));
                break;
            case R.id.btn_url:
                break;
            default:
                break;
        }
    }
}
