package com.h.cheng.mvpdemo.base;

import java.util.List;

/**
 * 作者： ch
 * 时间： 2019/2/15 0015-下午 2:47
 * 描述：
 * 来源：
 */

public class ShareModel {
    /**
     * status : 1
     * msg : 获取成功
     * data : [{"id":"25","wshare_name":"精选微分享","wshare_head":"./public/attachment/201812/28/10/origin/5c258b5423d23.png","wshare_content":""}]
     */

    private int status;
    private String msg;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 25
         * wshare_name : 精选微分享
         * wshare_head : ./public/attachment/201812/28/10/origin/5c258b5423d23.png
         * wshare_content :
         */

        private String id;
        private String wshare_name;
        private String wshare_head;
        private String wshare_content;

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
    }
}
