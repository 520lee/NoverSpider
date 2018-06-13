package com.king.service;
/* 
    author：king
    date：2018/6/7  
*/

import com.king.pojo.Chapter;

import java.util.List;

public interface IChapterService {
    public List<Chapter> getChapters(String url);
}
