package com.h.cheng.mvpdemo.base;

import java.io.IOException;

/**
 * 作者： ch
 * 时间： 2019/2/15 0015-上午 10:18
 * 描述：
 * 来源：
 */

public class BaseException extends IOException {

    /**
     * 解析数据失败
     */
    public static final int PARSE_ERROR = 1001;
    public static final String PARSE_ERROR_MSG = "解析数据失败";

    /**
     * 网络问题
     */
    public static final int BAD_NETWORK = 1002;
    public static final String BAD_NETWORK_MSG = "网络问题";
    /**
     * 连接错误
     */
    public static final int CONNECT_ERROR = 1003;
    public static final String CONNECT_ERROR_MSG = "连接错误";
    /**
     * 连接超时
     */
    public static final int CONNECT_TIMEOUT = 1004;
    public static final String CONNECT_TIMEOUT_MSG = "连接超时";
    /**
     * 未知错误
     */
    public static final int OTHER = 1005;
    public static final String OTHER_MSG = "未知错误";


    private String errorMsg;
    private int errorCode;


    public String getErrorMsg() {
        return errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public BaseException(String errorMsg, Throwable cause) {
        super(errorMsg, cause);
        this.errorMsg = errorMsg;
    }

    public BaseException(String message, Throwable cause, int errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
        this.errorMsg = message;
    }

    public BaseException(String message, int errorCode) {
        this.errorCode = errorCode;
        this.errorMsg = message;
    }
}
