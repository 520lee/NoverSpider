package com.king.pojo;
/* 
    author：king
    date：2018/6/7  
*/

import org.apache.commons.lang3.StringUtils;

public class ChapterDetail {
    private String title;
    private String content;
    private String prev;
    private String next;

    public ChapterDetail(String title, String content, String prev, String next) {
        this.title = title;
        this.content = content;
        this.prev = prev;
        this.next = next;
    }

    public ChapterDetail() {
        super();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPrev() {
        return prev;
    }

    public void setPrev(String prev) {
        this.prev = prev;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "ChapterDetail{" +
                "title='" + title + '\'' +
                ", content='" + StringUtils.abbreviate(content, 30) + '\'' +
                ", prev='" + prev + '\'' +
                ", next='" + next + '\'' +
                '}';
    }
}
