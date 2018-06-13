package com.king.pojo;
/* 
    author：king
    date：2018/6/7  
*/

public class Chapter {
    private String title;
    private String url;

    public Chapter(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public Chapter() {
        super();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
