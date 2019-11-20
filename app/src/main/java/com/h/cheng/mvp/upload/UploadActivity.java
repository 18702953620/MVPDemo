package com.h.cheng.mvp.upload;

import android.view.View;
import android.widget.Button;


import com.h.cheng.mvp.R;
import com.h.cheng.mvp.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： ch
 * 时间： 2019/11/20 11:43
 * 描述： 文件上传
 * 来源：
 */
public class UploadActivity extends BaseActivity<FileUploadPresenter> implements FileView {
    @BindView(R.id.btn_up1)
    Button btnUp1;
    @BindView(R.id.btn_up2)
    Button btnUp2;

    @Override
    protected FileUploadPresenter createPresenter() {
        return new FileUploadPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ac_upload;
    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void initView() {

    }

    @Override
    public void onUploadSucc() {

    }


    @OnClick({R.id.btn_up1, R.id.btn_up2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_up1:
                //示例代码
                presenter.upload("187029xxxxx", "xxx");
                break;
            case R.id.btn_up2:
                //示例代码
                List<String> paths = new ArrayList<>();
                presenter.upload2(paths);
                break;
            default:
        }
    }
}
