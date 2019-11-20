package com.h.cheng.mvp.down;

import android.os.Environment;
import android.widget.Button;
import android.widget.ProgressBar;

import com.h.cheng.mvp.R;
import com.h.cheng.mvp.base.BaseActivity;
import com.h.cheng.mvp.base.BasePresenter;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： ch
 * 时间： 2019/11/20 15:17
 * 描述： 下载
 * 来源：
 */
public class DownActivity extends BaseActivity {
    @BindView(R.id.btn_down)
    Button btnDown;
    @BindView(R.id.pb_down)
    ProgressBar pbDown;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ac_down;
    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void initView() {

        AndPermission.with(this)
                .runtime()
                .permission(Permission.Group.STORAGE)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        //成功

                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        //失败

                    }
                })
                .start();

        DownLoadHelper.getInstance().addDownLoadListener(new DownloadListener() {
            @Override
            public void onStartDownload(String tag) {

            }

            @Override
            public void onProgress(String tag, int progress) {
                pbDown.setProgress(progress);
            }

            @Override
            public void onFinishDownload(String tag, File file) {
                showtoast("下载完成");
            }

            @Override
            public void onFail(String tag, String msg) {

            }
        });

    }


    @OnClick(R.id.btn_down)
    public void onViewClicked() {
        if (AndPermission.hasPermissions(context, Permission.Group.STORAGE)) {
            String tmppath = Environment.getExternalStorageDirectory() + "/mvp/down/";
            File tmpFile = new File(tmppath);
            if (!tmpFile.exists()) {
                tmpFile.mkdirs();
            }
            DownLoadHelper.getInstance().downLoadFile("http://download.sdk.mob.com/apkbus.apk", tmppath, "down.apk");

        }
    }
}
