package com.king.service;
/*
    author: king
    date：2018/6/10
*/

import com.king.pojo.Novel;

import java.util.List;

public interface INovelService {
    public List<Novel> getNovels(String url, int tryTimes);
}
