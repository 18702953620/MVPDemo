package com.h.cheng.mvp.livedata;

import android.widget.Button;
import android.widget.TextView;

import androidx.lifecycle.Observer;

import com.google.gson.Gson;
import com.h.cheng.mvp.R;
import com.h.cheng.mvp.livedata.base.BaseLiveActivity;
import com.h.cheng.mvp.livedata.base.BaseModel;
import com.h.cheng.mvp.model.BannerModel;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： ch
 * 时间： 2019/11/20 16:00
 * 描述：
 * 来源：
 */
public class LiveDataActivity extends BaseLiveActivity<LiveDataPresenter> {
    @BindView(R.id.btn_get)
    Button btnGet;
    @BindView(R.id.tv_data)
    TextView tvData;

    @Override
    protected LiveDataPresenter createPresenter() {
        return new LiveDataPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ac_live_data;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
    }

    @OnClick(R.id.btn_get)
    public void onViewClicked() {
        providers.get(BannerViewModel.class).loadBanner(this).modelList.observe(this, new Observer<BaseModel<BannerModel>>() {
            @Override
            public void onChanged(BaseModel<BannerModel> o) {
                tvData.setText(new Gson().toJson(o));
            }
        });
    }
}
