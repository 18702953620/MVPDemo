package com.h.cheng.mvpdemo.base.gson;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.h.cheng.mvpdemo.base.BaseException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * 作者： ch
 * 时间： 2019/2/15 0015-上午 11:01
 * 描述：
 * 来源：
 */

public class BaseResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    BaseResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {

        String jsonString = value.string();
        try {
            JSONObject object = new JSONObject(jsonString);

            int code = object.getInt("status");
            if (code != 1) {
                String msg = object.getString("msg");
                if (TextUtils.isEmpty(msg)) {
                    msg = object.getString("error");
                }
                //异常处理
                throw new BaseException(msg, code);
            }

            return adapter.fromJson(object.getString("data"));

        } catch (JSONException e) {
            e.printStackTrace();
            //数据解析异常
            throw new BaseException(BaseException.PARSE_ERROR_MSG, BaseException.PARSE_ERROR);
        } finally {
            value.close();
        }
    }
}
