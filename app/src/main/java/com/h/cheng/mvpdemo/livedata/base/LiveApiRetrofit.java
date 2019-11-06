package com.h.cheng.mvpdemo.livedata.base;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者： ch
 * 时间： 2016/12/27.13:56
 * 描述：
 * 来源：
 */
public class LiveApiRetrofit {

    public final String BASE_SERVER_URL = "http://47.96.30.3/";

    private static LiveApiRetrofit apiRetrofit;
    private Retrofit retrofit;
    private OkHttpClient client;
    private LiveApiServer apiServer;

    private String TAG = "ApiRetrofit2";

    /**
     * 请求访问quest
     * response拦截器
     */
    private Interceptor interceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long startTime = System.currentTimeMillis();
            Response response = chain.proceed(chain.request());
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            MediaType mediaType = response.body().contentType();
            String content = response.body().string();
            Log.e(TAG, "----------Request Start----------------");
            Log.e(TAG, "| " + request.toString() + request.headers().toString());
            Log.e(TAG, "| Response:" + content);
            Log.e(TAG, "----------Request End:" + duration + "毫秒----------");
            return response.newBuilder()
                    .body(ResponseBody.create(mediaType, content))
                    .build();
        }
    };


    public LiveApiRetrofit() {
        client = new OkHttpClient.Builder()
                //添加log拦截器
                .addInterceptor(interceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_SERVER_URL)
                .addCallAdapterFactory(LiveDataConverterFactory.create())
                //支持RxJava2
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        apiServer = retrofit.create(LiveApiServer.class);
    }

    public static LiveApiRetrofit getInstance() {
        if (apiRetrofit == null) {
            synchronized (Object.class) {
                if (apiRetrofit == null) {
                    apiRetrofit = new LiveApiRetrofit();
                }
            }
        }
        return apiRetrofit;
    }

    public LiveApiServer getApiService() {
        return apiServer;
    }

}
