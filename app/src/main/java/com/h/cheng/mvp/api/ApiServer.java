package com.h.cheng.mvp.api;


import androidx.lifecycle.LiveData;

import com.h.cheng.mvp.livedata.base.BaseModel;
import com.h.cheng.mvp.model.ArticleModel;
import com.h.cheng.mvp.model.BannerModel;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * 作者： ch
 * 时间： 2016/12/27.13:56
 * 描述：
 * 来源：
 */

public interface ApiServer {

    /**
     * 获取分享列表
     *
     * @return
     */
    @GET("wxarticle/chapters/json")
    Observable<List<ArticleModel>> getWxArticleList();

    /**
     * 获取分享列表
     *
     * @return
     */
    @GET("wxarticle/chapters/json")
    Flowable<ArticleModel> getFWxArticleList();

    /**
     * 文件上传
     *
     * @param phone
     * @param image
     * @return
     */
    @Multipart
    @POST("user/register.do")
    Observable<String> upload(@Part("phone") RequestBody phone, @Part MultipartBody.Part image);

    /**
     * 文件上传2
     *
     * @param images
     * @return
     */
    @Multipart
    @POST("user/register.do")
    Observable<String> upload2(@Part List<MultipartBody.Part> images);

    @Streaming
    @GET
    /**
     * 大文件官方建议用 @Streaming 来进行注解，不然会出现IO异常，小文件可以忽略不注入
     */
    Observable<ResponseBody> downloadFile(@Url String fileUrl);


    /**
     * @return
     */
    @GET("https://www.wanandroid.com/banner/json")
    LiveData<BaseModel<BannerModel>> getBannerList();
}
