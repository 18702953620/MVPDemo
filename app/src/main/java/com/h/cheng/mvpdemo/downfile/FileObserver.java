package com.h.cheng.mvpdemo.downfile;

import com.h.cheng.mvpdemo.base.BaseObserver;
import com.h.cheng.mvpdemo.base.BaseView;
import com.h.cheng.mvpdemo.utils.FileUtil;

import java.io.File;

import okhttp3.ResponseBody;

/**
 * 作者： ch
 * 时间： 2016/12/27.13:56
 * 描述：
 * 来源：
 */


public abstract class FileObserver extends BaseObserver<String> {


    public FileObserver(BaseView view) {
        super(view);
    }

    @Override
    protected void onStart() {
        if (view != null) {
            view.showLoadingFileDialog();
        }
    }

    @Override
    public void onComplete() {
        if (view != null) {
            view.hideLoadingFileDialog();
        }
    }

    @Override
    public void onNext(String path) {
        File file = new File(path);
        if (file != null && file.exists()) {
            onSuccess(file);
        } else {
            onErrorMsg("file is null or a file does not exist");
        }
    }

    @Override
    public void onSuccess(String o) {

    }


    public abstract void onSuccess(File file);

    public abstract void onErrorMsg(String msg);

}
