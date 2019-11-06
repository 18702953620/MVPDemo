package com.h.cheng.mvpdemo.api;

import com.h.cheng.mvpdemo.test_json.ShareModel;
import com.h.cheng.mvpdemo.test_json.WatchRecordModel;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
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
    Flowable<String> LoginByRx(@Field("username") String username, @Field("password") String password);

    @POST("shopping_login.htm")
    Flowable<String> regex(@Field("tel") String tel);


    @Multipart
    @POST("user/register.do")
    Flowable<String> register(@Part("phone") RequestBody phone, @Part("password") RequestBody password, @Part MultipartBody.Part image);

    @Multipart
    @POST("http://sfybim.com/upload/uploadfile")
    Flowable<String> upload(@Part MultipartBody.Part image);

    @GET("http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.billboard.billList&type=1&size=10&offset=%25")
    Flowable<String> test();

    @Streaming
    @GET
    /**
     * 大文件官方建议用 @Streaming 来进行注解，不然会出现IO异常，小文件可以忽略不注入
     */
    Flowable<ResponseBody> downloadFile(@Url String fileUrl);


    @GET("http://api.csslcloud.net/api/room/create{param}")
    Flowable<String> createRoom(@Path("param") String param);

    /**
     * 获取分享列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("module/index.php?")
    Flowable<List<ShareModel>> getShareList(@FieldMap Map<String, String> map);

    /**
     * 获取分享列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("module/index.php?")
    @Headers({"url_mark:1"})
    Flowable<List<ShareModel>> getShareList2(@FieldMap Map<String, String> map);

    /**
     * 获取分享列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("module/index.php?")
    @Headers({"url_mark:2"})
    Flowable<List<ShareModel>> getShareList3(@FieldMap Map<String, String> map);


    /**
     * 观看历史
     *
     * @return
     */
    @FormUrlEncoded
    @POST("module/index.php?ctl=user&act=watchHistory")
    Flowable<List<WatchRecordModel>> watchHistory(@FieldMap Map<String, Object> params);


}
