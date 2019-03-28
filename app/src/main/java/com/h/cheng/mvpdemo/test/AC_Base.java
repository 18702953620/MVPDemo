package com.h.cheng.mvpdemo.test;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.h.cheng.mvpdemo.base.BaseModel;
import com.h.cheng.mvpdemo.base.BasePresenter;
import com.h.cheng.mvpdemo.base.BaseView;

import butterknife.ButterKnife;

public abstract class AC_Base<V extends BVHelper> extends AppCompatActivity implements BaseView {
    public Context context;
    private ProgressDialog dialog;
    public Toast toast;
    protected V v;

    protected abstract V createView();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        v = createView();
        setContentView(v.getView());

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (v != null) {
            v.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (v != null) {
            v.onPause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (v != null) {
            v.onDestroy();
        }
    }

    /**
     * @param s
     */
    public void showtoast(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }


    public void showFileDialog() {
        dialog = new ProgressDialog(context);
        dialog.setMessage("正在下载中,请稍后");
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setMax(100);
        dialog.show();
    }

    public void hideFileDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }


    private void closeLoadingDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }


    private void showLoadingDialog() {

        if (dialog == null) {
            dialog = new ProgressDialog(context);
        }
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    public void showLoading() {
        showLoadingDialog();
    }


    @Override
    public void hideLoading() {
        closeLoadingDialog();
    }


    @Override
    public void showError(String msg) {
        showtoast(msg);
    }

    @Override
    public void onErrorCode(BaseModel model) {
    }

    @Override
    public void showLoadingFileDialog() {
        showFileDialog();
    }

    @Override
    public void hideLoadingFileDialog() {
        hideFileDialog();
    }

    @Override
    public void onProgress(long totalSize, long downSize) {
        if (dialog != null) {
            dialog.setProgress((int) (downSize * 100 / totalSize));
        }
    }


}
