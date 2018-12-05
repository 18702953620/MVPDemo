package com.h.cheng.mvpdemo.downfile;

/**
 * 作者： ch
 * 时间： 2018/12/5 0005-上午 11:23
 * 描述：
 * 来源：
 */

public class DownModel {

    private String url;
    private String path;
    private String title;
    private String cover;
    private long totalSize;
    private long currentTotalSize;
    private long downSize;
    private boolean isExists;
    private boolean isFinish;
    private boolean isPause;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

    public long getCurrentTotalSize() {
        return currentTotalSize;
    }

    public void setCurrentTotalSize(long currentTotalSize) {
        this.currentTotalSize = currentTotalSize;
    }

    public long getDownSize() {
        return downSize;
    }

    public void setDownSize(long downSize) {
        this.downSize = downSize;
    }

    public boolean isExists() {
        return isExists;
    }

    public void setExists(boolean exists) {
        isExists = exists;
    }

    public boolean isFinish() {
        return isFinish;
    }

    public void setFinish(boolean finish) {
        isFinish = finish;
    }

    public boolean isPause() {
        return isPause;
    }

    public void setPause(boolean pause) {
        isPause = pause;
    }
}
