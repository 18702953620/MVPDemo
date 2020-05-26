package com.h.cheng.mvp.livedata.base;

import androidx.lifecycle.Observer;

/**
 * @author ch
 * @date 2020/5/26-14:23
 * @desc
 */
public abstract class BaseVmObserver<T> implements Observer<T> {
    private BaseViewHelper helper;

    /**
     * 成功回调
     *
     * @param o o
     */
    protected abstract void onSucc(T o);

    public BaseVmObserver(BaseViewHelper helper) {
        this.helper = helper;
    }

    @Override
    public void onChanged(T o) {
        if (o != null) {
            if (o instanceof BaseModel) {
                BaseModel model = (BaseModel) o;
                if (model.getErrorCode() != 0) {
                    helper.onErrorCode(model.getErrorCode(), model.getErrorMsg());
                } else {
                    onSucc(o);
                }
            }
        }
    }
}
