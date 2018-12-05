package com.h.cheng.mvpdemo;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.h.cheng.mvpdemo.base.BaseActivity;
import com.h.cheng.mvpdemo.downfile.DownFileCallback;
import com.h.cheng.mvpdemo.downfile.DownLoadManager;
import com.h.cheng.mvpdemo.downfile.DownModel;
import com.h.cheng.mvpdemo.downfile.FilePresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 断点续传
 */
public class DownActivity extends BaseActivity {

    @BindView(R.id.sb_down)
    SeekBar sbDown;
    @BindView(R.id.tv_down)
    TextView tvDown;
    @BindView(R.id.btn_start)
    Button btnStart;
    @BindView(R.id.btn_cancl)
    Button btnCancl;


    private DownModel downModel;

    @Override
    protected FilePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_down2;
    }

    @OnClick({R.id.btn_start, R.id.btn_cancl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
//                downloadFile();
                down2();
                break;
            case R.id.btn_cancl:
                DownLoadManager.getInstance().pause(downModel.getUrl());
                break;
        }
    }

    private void down2() {
        String url = "http://download.sdk.mob.com/apkbus.apk";
        if (downModel == null) {
            downModel = new DownModel();
            downModel.setUrl(url);
        }
        DownLoadManager.getInstance().downFile(downModel, new DownFileCallback() {
            @Override
            public void onSuccess(String path) {
                showtoast("下载成功，path=" + path);
            }

            @Override
            public void onFail(String msg) {

            }

            @Override
            public void onProgress(long totalSize, long downSize) {
                Log.e("cheng", "totalSize =" + totalSize + ",downSize=" + downSize);
                if (downModel.getTotalSize() == 0) {
                    downModel.setTotalSize(totalSize);
                }
                downModel.setCurrentTotalSize(totalSize);

                downModel.setDownSize(downSize + downModel.getTotalSize() - downModel.getCurrentTotalSize());

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int progress = (int) (downModel.getDownSize() * 100 / downModel.getTotalSize());
                        tvDown.setText(progress + "%");
                        sbDown.setProgress(progress);
                    }
                });


            }
        });
    }

    private void downloadFile() {
        String url = "http://download.sdk.mob.com/apkbus.apk";

        DownLoadManager.getInstance().downFile(url, new DownFileCallback() {
            @Override
            public void onSuccess(String path) {
                showtoast("下载成功，path=" + path);
            }

            @Override
            public void onFail(String msg) {

            }

            @Override
            public void onProgress(final long totalSize, final long downSize) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int progress = (int) (downSize * 100 / totalSize);
                        tvDown.setText(progress + "%");
                        sbDown.setProgress(progress);
                    }
                });


            }
        });

    }

}
