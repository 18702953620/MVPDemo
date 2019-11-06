package com.h.cheng.mvpdemo.livedata.model;

import java.io.Serializable;

/**
 * 作者： ch
 * 时间： 2019/11/1 17:36
 * 描述：
 * 来源：
 */
public class BannerModel implements Serializable {
    private int id;
    private String title;
    private String desc;
    private int type;
    private String url;
    private String imagePath;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
