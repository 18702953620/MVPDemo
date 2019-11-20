package com.h.cheng.mvp.livedata.base;

import java.util.List;

/**
 * 作者： ch
 * 时间： 2019/11/1 17:37
 * 描述：
 * 来源：
 */
public class BaseModel<T> {

    private int errorCode;
    private String errorMsg;
    private List<T> data;

    public BaseModel() {
    }

    public BaseModel(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BaseModel(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
