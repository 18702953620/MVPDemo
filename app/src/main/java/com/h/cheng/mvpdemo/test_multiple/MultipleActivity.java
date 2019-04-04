package com.h.cheng.mvpdemo.test_multiple;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.h.cheng.mvpdemo.R;
import com.h.cheng.mvpdemo.base.BaseActivity;
import com.h.cheng.mvpdemo.test_json.ShareModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 多url 演示
 */
public class MultipleActivity extends BaseActivity<MultPresenter> implements MultView {

    @BindView(R.id.btn_url1)
    Button btnUrl1;
    @BindView(R.id.btn_url2)
    Button btnUrl2;

    @Override
    protected MultPresenter createPresenter() {
        return new MultPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_multiple;
    }

    @Override
    public void initView() {

    }

    @Override
    public void onGetShareListSucc(List<ShareModel> o) {

    }

    @OnClick({R.id.btn_url1, R.id.btn_url2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_url1:
//                presenter.getShareList();
                presenter.getShareList3();
                break;
            case R.id.btn_url2:
//                presenter.getShareList2();
                presenter.getShareList4();
                break;
        }
    }
}
