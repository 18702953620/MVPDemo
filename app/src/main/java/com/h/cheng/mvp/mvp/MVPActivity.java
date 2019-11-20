package com.h.cheng.mvp.mvp;

import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.h.cheng.mvp.R;
import com.h.cheng.mvp.base.BaseActivity;
import com.h.cheng.mvp.model.ArticleModel;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： ch
 * 时间： 2019/11/19  15:09
 * 描述： mvp 演示
 * 来源：
 */
public class MVPActivity extends BaseActivity<MvpPresenter> implements MvpView {

    @BindView(R.id.btn_getdata)
    Button btnGetdata;
    @BindView(R.id.tv_json)
    TextView tvJson;

    @Override
    protected MvpPresenter createPresenter() {
        return new MvpPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mvp;
    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void initView() {

    }

    @Override
    public void onGetListSucc(List<ArticleModel> o) {
        tvJson.setText(new Gson().toJson(o));
    }


    @OnClick(R.id.btn_getdata)
    public void onViewClicked() {
        presenter.getWxArticleList();
    }
}
