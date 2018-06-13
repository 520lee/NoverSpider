package com.king.dao;

import com.king.pojo.Novel;

import java.util.List;

public interface NovelMapper {
    int deleteByPrimaryKey(String url);

    int insert(Novel record);

    int insertSelective(Novel record);

    Novel selectByPrimaryKey(String url);

    int updateByPrimaryKeySelective(Novel record);

    int updateByPrimaryKey(Novel record);

    int selectAll();

    int truncateAll();

    List<Novel> selectByKey(String key);
}