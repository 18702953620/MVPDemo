package com.h.cheng.mvpdemo.test_json;

public class WatchRecordModel {

    /**
     * id : 29
     * user_id : 113942
     * type : 2
     * details_id : 1
     * create_time : 1543828321
     * title : 产品1
     * cover_image : ./public/attachment/201802/100651/201802080318479867.png
     * duration : 40
     * play_count : 0
     */

    private String id;
    private String user_id;
    private String type;
    private String details_id;
    private String create_time;
    private String title;
    private String cover_image;
    private String duration;
    private String play_count;
    private String pid;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDetails_id() {
        return details_id;
    }

    public void setDetails_id(String details_id) {
        this.details_id = details_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover_image() {
        return cover_image;
    }

    public void setCover_image(String cover_image) {
        this.cover_image = cover_image;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPlay_count() {
        return play_count;
    }

    public void setPlay_count(String play_count) {
        this.play_count = play_count;
    }
}
