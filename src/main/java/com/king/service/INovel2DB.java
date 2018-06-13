package com.king.service;
/*
    author: king
    date: 2018/6/13
*/

import com.king.pojo.Novel;

import java.util.List;

public interface INovel2DB {
    public String insert(List<List<Novel>> novels);
    public String create(List<Novel> novels);
    public List<Novel> search(String key);
}
