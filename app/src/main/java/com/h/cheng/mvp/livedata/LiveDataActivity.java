package com.h.cheng.mvp.livedata;

import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.google.gson.Gson;
import com.h.cheng.mvp.R;
import com.h.cheng.mvp.livedata.base.BaseLiveActivity;
import com.h.cheng.mvp.livedata.base.BaseModel;
import com.h.cheng.mvp.livedata.base.BaseVmObserver;
import com.h.cheng.mvp.model.ArticleModel;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： ch
 * 时间： 2019/11/20 16:00
 * 描述：
 * 来源：
 */
public class LiveDataActivity extends BaseLiveActivity<HomeViewModel> {
    @BindView(R.id.btn_get)
    Button btnGet;
    @BindView(R.id.tv_data)
    TextView tvData;

    @Override
    protected int getLayoutId() {
        return R.layout.ac_live_data;
    }

    @Override
    protected void initView() {
        Log.e("cheng", "initView: vm=" + vm.toString());

    }

    @Override
    protected void getData() {
        MutableLiveData<BaseModel<List<ArticleModel>>> json = vm.getObservable("json");

        json.observe(this, new BaseVmObserver<BaseModel<List<ArticleModel>>>(this) {
            @Override
            protected void onSucc(BaseModel<List<ArticleModel>> o) {
                tvData.setText(new Gson().toJson(o));
            }
        });
    }


    @OnClick(R.id.btn_get)
    public void onViewClicked() {
        vm.getData();
    }

}
