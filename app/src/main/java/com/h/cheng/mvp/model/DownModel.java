package com.h.cheng.mvp.model;


/**
 * 作者： ch
 * 时间： 2019/10/31 17:11
 * 描述：
 * 来源：
 */
public class DownModel {

    public static final int DOWN_DEFAULT = 0;
    public static final int DOWN_FAIL = 1;
    public static final int DOWN_FINISH = 2;

    //模型id
    private String tag;
    private long downSize;
    private long totalSize;
    //模型类型
    private String type;
    //链接
    private String url;
    //文件夹
    private String destDir;
    //解压后的目录
    private String zipDir;
    //文件名称
    private String name;
    //状态
    private int state;
    //文件大小
    private String fileSize;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public long getDownSize() {
        return downSize;
    }

    public void setDownSize(long downSize) {
        this.downSize = downSize;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDestDir() {
        return destDir;
    }

    public void setDestDir(String destDir) {
        this.destDir = destDir;
    }

    public String getZipDir() {
        return zipDir;
    }

    public void setZipDir(String zipDir) {
        this.zipDir = zipDir;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }
}
