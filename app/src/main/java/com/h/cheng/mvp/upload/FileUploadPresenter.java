package com.h.cheng.mvp.upload;


import com.h.cheng.mvp.base.BaseObserver;
import com.h.cheng.mvp.base.BasePresenter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 作者： ch
 * 时间： 2019/11/20 11:36
 * 描述：
 * 来源：
 */
public class FileUploadPresenter extends BasePresenter<FileView> {
    public FileUploadPresenter(FileView baseView) {
        super(baseView);
    }

    /**
     * 上传文件
     *
     * @param phone
     * @param path
     */
    public void upload(String phone, String path) {
        File file = new File(path);
        RequestBody requestFile = RequestBody.create(MediaType.parse("*/*"), file);
        //  手机号参数
        RequestBody phoneBody = RequestBody.create(MediaType.parse("multipart/form-data"), phone);

        MultipartBody.Part body = MultipartBody.Part.createFormData("Fils", file.getName(), requestFile);

        addDisposable(apiServer.upload(phoneBody, body), new BaseObserver(baseView) {
            @Override
            public void onSuccess(Object o) {
                baseView.onUploadSucc();
            }

            @Override
            public void onError(String msg) {
                baseView.showError(msg);
            }
        });
    }


    public void upload2(List<String> paths) {
        List<MultipartBody.Part> parts = new ArrayList<>();
        for (String m : paths) {
            File file = new File(m);
            RequestBody requestFile = RequestBody.create(MediaType.parse("*/*"), file);
            MultipartBody.Part filePart = MultipartBody.Part.createFormData("Files", file.getName(), requestFile);
            parts.add(filePart);
        }
        addDisposable(apiServer.upload2(parts), new BaseObserver(baseView) {
            @Override
            public void onSuccess(Object o) {
                baseView.onUploadSucc();
            }

            @Override
            public void onError(String msg) {
                baseView.showError(msg);
            }
        });
    }
}
