package com.king.pojo;

import java.util.Date;

public class Novel {
    private String url;

    private String name;

    private String author;

    private String lastUpdateChapter;

    private String lastUpdateChapterUrl;

    private Date updateDate;

    private Integer status;

    private Integer platformId;

    private Date addDate;

    public Novel(String url, String name, String author, String lastUpdateChapter, String lastUpdateChapterUrl, Date updateDate, Integer status, Integer platformId, Date addDate) {
        this.url = url;
        this.name = name;
        this.author = author;
        this.lastUpdateChapter = lastUpdateChapter;
        this.lastUpdateChapterUrl = lastUpdateChapterUrl;
        this.updateDate = updateDate;
        this.status = status;
        this.platformId = platformId;
        this.addDate = addDate;
    }

    public Novel() {
        super();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getLastUpdateChapter() {
        return lastUpdateChapter;
    }

    public void setLastUpdateChapter(String lastUpdateChapter) {
        this.lastUpdateChapter = lastUpdateChapter == null ? null : lastUpdateChapter.trim();
    }

    public String getLastUpdateChapterUrl() {
        return lastUpdateChapterUrl;
    }

    public void setLastUpdateChapterUrl(String lastUpdateChapterUrl) {
        this.lastUpdateChapterUrl = lastUpdateChapterUrl == null ? null : lastUpdateChapterUrl.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }
}