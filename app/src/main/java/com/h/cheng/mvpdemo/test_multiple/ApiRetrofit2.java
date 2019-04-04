package com.h.cheng.mvpdemo.test_multiple;

import android.util.Log;

import com.h.cheng.mvpdemo.api.ApiServer;
import com.h.cheng.mvpdemo.base.gson.BaseConverterFactory;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * 作者： ch
 * 时间： 2016/12/27.13:56
 * 描述：
 * 来源：
 */
public class ApiRetrofit2 {

    public final String BASE_SERVER_URL = "http://47.96.30.3/";
    public final String BASE_SERVER_URL2 = "http://www.baidu.com/";


    private static ApiRetrofit2 apiRetrofit;
    private Retrofit retrofit;
    private Retrofit retrofit2;
    private OkHttpClient client;
    private ApiServer apiServer;
    private ApiServer apiServer2;

    private String TAG = "ApiRetrofit2";

    /**
     * 请求访问quest
     * response拦截器
     */
    private Interceptor interceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            Log.e(TAG, "----------Request Start----------------");
            Log.e(TAG, "| OldUrl=" + request.url().toString());
            List<String> mark = request.headers("url_mark");

            HttpUrl newUrl = null;
            if (mark != null && mark.size() > 0) {
                Log.e(TAG, "| Head=" + mark.get(0));
                if (mark.get(0).equals("1")) {
                    newUrl = HttpUrl.parse("http://www.baidu.com/");
                } else if (mark.get(0).equals("2")) {
                    newUrl = HttpUrl.parse("http://www.github.com/");
                } else {
                    newUrl = request.url();
                }
                request = request.newBuilder().url(newUrl).build();
            }


            Log.e(TAG, "| NewUrl=" + request.url().toString());

            long startTime = System.currentTimeMillis();
            Response response = chain.proceed(request);
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            MediaType mediaType = response.body().contentType();
            String content = response.body().string();


            Log.e(TAG, "| " + request.toString());
            Log.e(TAG, "| Response:" + content);
            Log.e(TAG, "----------Request End:" + duration + "毫秒----------");
            return response.newBuilder()
                    .body(ResponseBody.create(mediaType, content))
                    .build();
        }
    };


    public ApiRetrofit2() {
        client = new OkHttpClient.Builder()
                //添加log拦截器
                .addInterceptor(interceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_SERVER_URL)
                .addConverterFactory(BaseConverterFactory.create())
                //支持RxJava2
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();

        retrofit2 = new Retrofit.Builder()
                .baseUrl(BASE_SERVER_URL2)
                .addConverterFactory(BaseConverterFactory.create())
                //支持RxJava2
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();

        apiServer = retrofit.create(ApiServer.class);

        apiServer2 = retrofit2.create(ApiServer.class);
    }

    public static ApiRetrofit2 getInstance() {
        if (apiRetrofit == null) {
            synchronized (Object.class) {
                if (apiRetrofit == null) {
                    apiRetrofit = new ApiRetrofit2();
                }
            }
        }
        return apiRetrofit;
    }

    public ApiServer getApiService() {
        return apiServer;
    }

    public ApiServer getApiService2() {
        return apiServer2;
    }

}
