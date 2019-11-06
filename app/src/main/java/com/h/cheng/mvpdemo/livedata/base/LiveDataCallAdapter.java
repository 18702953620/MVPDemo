package com.h.cheng.mvpdemo.livedata.base;

import android.arch.lifecycle.LiveData;

import com.h.cheng.mvpdemo.livedata.base.BaseModel;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicBoolean;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 作者： ch
 * 时间： 2019/11/1 18:01
 * 描述：
 * 来源：
 */
public class LiveDataCallAdapter<R> implements CallAdapter<R, LiveData<BaseModel>> {

    private final Type responseType;

    public LiveDataCallAdapter(Type responseType) {
        this.responseType = responseType;
    }

    @Override
    public Type responseType() {
        return responseType;
    }

    @Override
    public LiveData<BaseModel> adapt(final Call<R> call) {
        return new LiveData<BaseModel>() {
            AtomicBoolean started = new AtomicBoolean(false);

            @Override
            protected void onActive() {
                super.onActive();
                if (started.compareAndSet(false, true)) {
                    call.enqueue(new Callback<R>() {
                        @Override
                        public void onResponse(Call<R> call, Response<R> response) {
                            BaseModel responseData = new BaseModel<>();
                            if (response.isSuccessful()) {
                                responseData = (BaseModel) response.body();
                            } else {
                                try {
                                    responseData.setErrorMsg(response.errorBody().string());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                responseData.setData(null);
                                responseData.setErrorCode(response.code());
                            }
                            postValue(responseData);
                        }

                        @Override
                        public void onFailure(Call<R> call, Throwable throwable) {
                            BaseModel responseData = new BaseModel<>();
                            responseData.setErrorCode(500);
                            responseData.setData(null);
                            responseData.setErrorMsg(throwable.getMessage());
                            postValue(responseData);
                        }
                    });
                }
            }
        };
    }
}
