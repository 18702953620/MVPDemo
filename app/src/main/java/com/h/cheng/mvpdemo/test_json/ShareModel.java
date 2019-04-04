package com.h.cheng.mvpdemo.test_json;

import java.io.Serializable;
import java.util.List;

/**
 * 作者： ch
 * 时间： 2018/11/22 0022-上午 10:26
 * 描述：
 * 来源：
 */

public class ShareModel implements Serializable {

    /**
     * id : 11
     * wshare_name : 精选微分享
     * wshare_head : /public/images/weixin.png
     * wshare_content : 生活，或许一地鸡毛，但仍要踏歌而行，一念沧海，一念桑田，我们只需做一个从容坦然的人，每一步里都种上花开，每一眸里都种上云朵，以一颗琉璃心欣赏众生万物，让光阴的记事本上留下细细碎碎的小美好。</span>
     * wshare_image : [{"art_img":"/public/attachment/201811/19/15/origin/5bf2630e9bfc4.jpg","thumb_img":"/public/attachment/201811/19/15/origin/thumb_5bf2630e9bfc4.jpg"},{"art_img":"/public/attachment/201811/19/15/origin/5bf2630ea40ae.jpg","thumb_img":"/public/attachment/201811/19/15/origin/thumb_5bf2630ea40ae.jpg"},{"art_img":"/public/attachment/201811/19/15/origin/5bf2630ea40ae.jpg","thumb_img":"/public/attachment/201811/19/15/origin/thumb_5bf2630ea40ae.jpg"},{"art_img":"/public/attachment/201811/19/15/origin/5bf2630eda002.jpg","thumb_img":"/public/attachment/201811/19/15/origin/thumb_5bf2630eda002.jpg"},{"art_img":"/public/attachment/201811/19/15/origin/5bf2630edf9dc.jpg","thumb_img":"/public/attachment/201811/19/15/origin/thumb_5bf2630edf9dc.jpg"},{"art_img":"/public/attachment/201811/19/15/origin/5bf2630edee24.jpg","thumb_img":"/public/attachment/201811/19/15/origin/thumb_5bf2630edee24.jpg"},{"art_img":"/public/attachment/201811/19/15/origin/5bf2630f0cecc.jpg","thumb_img":"/public/attachment/201811/19/15/origin/thumb_5bf2630f0cecc.jpg"},{"art_img":"/public/attachment/201811/19/15/origin/5bf2630f0bb43.jpg","thumb_img":"/public/attachment/201811/19/15/origin/thumb_5bf2630f0bb43.jpg"},{"art_img":"/public/attachment/201811/19/15/origin/5bf2630f23631.jpg","thumb_img":"/public/attachment/201811/19/15/origin/thumb_5bf2630f23631.jpg"}]
     * create_time : 1542591349
     * share_count : 0
     * status : 1
     * tag_name : 帅锅和黄金粉
     * video_url :
     * product_id : 2
     */

    private String id;
    private String wshare_name;
    private String wshare_head;
    private String wshare_content;
    private String create_time;
    private String share_count;
    private String status;
    private String tag_name;
    private String video_url;
    private String product_id;
    private String video_cover;
    private String width;
    private String height;
    private String duration;
    private boolean is_collect;

    private List<WshareImageBean> wshare_image;

    public boolean isIs_collect() {
        return is_collect;
    }

    public void setIs_collect(boolean is_collect) {
        this.is_collect = is_collect;
    }

    public String getVideo_cover() {
        return video_cover;
    }

    public void setVideo_cover(String video_cover) {
        this.video_cover = video_cover;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWshare_name() {
        return wshare_name;
    }

    public void setWshare_name(String wshare_name) {
        this.wshare_name = wshare_name;
    }

    public String getWshare_head() {
        return wshare_head;
    }

    public void setWshare_head(String wshare_head) {
        this.wshare_head = wshare_head;
    }

    public String getWshare_content() {
        return wshare_content;
    }

    public void setWshare_content(String wshare_content) {
        this.wshare_content = wshare_content;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getShare_count() {
        return share_count;
    }

    public void setShare_count(String share_count) {
        this.share_count = share_count;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public List<WshareImageBean> getWshare_image() {
        return wshare_image;
    }

    public void setWshare_image(List<WshareImageBean> wshare_image) {
        this.wshare_image = wshare_image;
    }

    public static class WshareImageBean implements Serializable {
        /**
         * art_img : /public/attachment/201811/19/15/origin/5bf2630e9bfc4.jpg
         * thumb_img : /public/attachment/201811/19/15/origin/thumb_5bf2630e9bfc4.jpg
         */

        private String art_img;
        private String thumb_img;

        public String getArt_img() {
            return art_img;
        }

        public void setArt_img(String art_img) {
            this.art_img = art_img;
        }

        public String getThumb_img() {
            return thumb_img;
        }

        public void setThumb_img(String thumb_img) {
            this.thumb_img = thumb_img;
        }
    }
}
