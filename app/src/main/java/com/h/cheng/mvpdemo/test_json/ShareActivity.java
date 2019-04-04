package com.h.cheng.mvpdemo.test_json;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.h.cheng.mvpdemo.R;
import com.h.cheng.mvpdemo.base.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ShareActivity extends BaseActivity<SharePresenter> implements ShareView {

    @BindView(R.id.btn_normal)
    Button btnNormal;
    @BindView(R.id.btn_error)
    Button btnError;
    @BindView(R.id.tv_share)
    TextView tvShare;

    @Override
    protected SharePresenter createPresenter() {
        return new SharePresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_share;
    }


    @OnClick({R.id.btn_normal, R.id.btn_error})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_normal:
                presenter.getShareList();
                break;
            case R.id.btn_error:
                presenter.getWatchHistory();
                break;
        }
    }

    @Override
    public void onGetShareListSucc(List<ShareModel> o) {
        showtoast("获取成功");
        tvShare.setText(new Gson().toJson(o));
    }

    @Override
    public void onWatchRecord(List<WatchRecordModel> o) {

    }
}
