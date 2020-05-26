package com.h.cheng.mvp.livedata.base;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.lang.reflect.ParameterizedType;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author ch
 * @date 2020/5/25-16:53
 * @desc
 */
public abstract class BaseLiveActivity<M extends BaseViewModel> extends AppCompatActivity implements BaseViewHelper {


    protected M vm;

    /**
     * context
     */
    public Context context;

    /**
     * 布局
     *
     * @return id
     */
    protected abstract int getLayoutId();

    /**
     * 初始化
     */
    protected abstract void initView();

    /**
     * 获取数据
     */
    protected abstract void getData();

    protected Unbinder unbinder;

    protected ViewModelProvider providers;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        providers = new ViewModelProvider(this,
                new ViewModelProvider.AndroidViewModelFactory(getApplication()));

        vm = createViewModel();

        initView();

        getData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void onErrorCode(int code, String msg) {
        showtoast(msg);
    }


    /**
     * @param s s
     */
    public void showtoast(String s) {
        Toast.makeText(context, s, Toast.LENGTH_LONG).show();
    }

    /**
     * Initialize view model. Override this method to add your own implementation.
     *
     * @return the view model will be used.
     */
    protected M createViewModel() {
        Class<M> vmClass = ((Class) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
        return providers.get(vmClass);
    }
}
