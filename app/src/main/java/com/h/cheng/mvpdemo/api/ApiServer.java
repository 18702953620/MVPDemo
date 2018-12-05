package com.h.cheng.mvpdemo.api;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * 作者： ch
 * 时间： 2016/12/27.13:56
 * 描述：
 * 来源：
 */

public interface ApiServer {

    @POST("shopping_login.htm")
    Observable<String> LoginByRx(@Field("username") String username, @Field("password") String password);

    @POST("shopping_login.htm")
    Observable<String> regex(@Field("tel") String tel);


    @Multipart
    @POST("user/register.do")
    Observable<String> register(@Part("phone") RequestBody phone, @Part("password") RequestBody password, @Part MultipartBody.Part image);

    @Multipart
    @POST("http://192.168.10.221:8080/SpringMvc/file/uploadspring")
    Observable<String> upload(@Part MultipartBody.Part image);

    @GET("http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.billboard.billList&type=1&size=10&offset=%25")
    Observable<String> test();

    @Streaming
    @GET
    /**
     * 大文件官方建议用 @Streaming 来进行注解，不然会出现IO异常，小文件可以忽略不注入
     */
    Observable<ResponseBody> downloadFile(@Url String fileUrl);


    @GET("http://api.csslcloud.net/api/room/create{param}")
    Observable<String> createRoom(@Path("param") String param);

}
