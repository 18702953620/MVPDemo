package com.h.cheng.mvp.livedata.base;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;


import com.h.cheng.mvp.api.ApiRetrofit;
import com.h.cheng.mvp.api.ApiServer;

import java.util.HashMap;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author ch
 * @date 2020/5/25-17:22
 * @desc
 */
public class BaseViewModel extends AndroidViewModel {

    protected ApiServer apiServer = ApiRetrofit.getInstance().getApiService();

    private LiveDataHolder holder = new LiveDataHolder();

    private CompositeDisposable compositeDisposable;

    public BaseViewModel(@NonNull Application application) {
        super(application);
    }


    protected void addDisposable(Flowable<?> flowable, BaseLiveSubscriber subscriber) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(subscriber));

    }

    protected void removeDisposable() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        removeDisposable();
        holder.clear();
    }

    /**
     * 通过要传递的数据类型获取一个 LiveData 对象
     *
     * @param key key
     * @param <T> 数据类型
     * @return 数据
     */
    public <T> MutableLiveData<T> getObservable(String key) {
        return holder.getLiveData(key);
    }

    /**
     * 存值
     *
     * @param values 数据
     * @param key    key
     * @param <T>    数据类型
     */
    public <T> void post(T values, String key) {
        MutableLiveData<T> liveData = getObservable(key);
        if (liveData != null) {
            liveData.postValue(values);
        } else {
            MutableLiveData<T> event = new MutableLiveData<T>();
            event.postValue(values);
            holder.post(event, key);
        }
    }

    /**
     * 成功
     *
     * @param values values
     * @param key    key
     * @param <T>    数据类型
     */
    public <T> void postSucc(T values, String key) {
        BaseModel baseModel = new BaseModel<T>(0, "请求成功", values);
        post(baseModel, key);
    }

    /**
     * 成功
     *
     * @param values values
     * @param key    key
     * @param msg    msg
     * @param <T>    数据类型
     */
    public <T> void postSucc(T values, String key, String msg) {
        BaseModel baseModel = new BaseModel<T>(0, msg, values);
        post(baseModel, key);
    }

    /**
     * 失败
     *
     * @param key key
     * @param <T> 数据类型
     */
    public <T> void postFail(String key) {
        BaseModel baseModel = new BaseModel<T>(1, "请求失败", null);
        post(baseModel, key);
    }

    /**
     * 失败
     *
     * @param key key
     * @param msg msg
     * @param <T> 数据类型
     */
    public <T> void postFail(String key, String msg) {
        BaseModel baseModel = new BaseModel<T>(1, msg, null);
        post(baseModel, key);
    }

    /**
     * 失败
     *
     * @param key  key
     * @param code code
     * @param msg  msg
     * @param <T>  数据类型
     */
    public <T> void postFail(String key, int code, String msg) {
        BaseModel baseModel = new BaseModel<T>(code, msg, null);
        post(baseModel, key);
    }


    private static class LiveDataHolder {

        private HashMap<String, MutableLiveData> map = new HashMap<>();

        /**
         * 获取 MutableLiveData
         *
         * @param key key
         * @return MutableLiveData
         */
        private MutableLiveData getLiveData(String key) {
            MutableLiveData liveData = map.get(key);
            if (liveData == null) {
                liveData = new MutableLiveData<>();
                map.put(key, liveData);
            }
            return liveData;
        }

        /**
         * 存入 MutableLiveData
         *
         * @param event event
         * @param key   key
         */
        private void post(MutableLiveData event, String key) {
            map.put(key, event);
        }

        /**
         * 清除
         */
        private void clear() {
            map.clear();
        }
    }


}
