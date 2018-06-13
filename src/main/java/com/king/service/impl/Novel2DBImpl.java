package com.king.service.impl;
/*
    author: king
    date: 2018/6/13
*/

import com.king.dao.NovelMapper;
import com.king.pojo.Novel;
import com.king.service.INovel2DB;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("iNovel2DB")
public class Novel2DBImpl implements INovel2DB {

    @Autowired
    NovelMapper novelMapper;

    @Override
    public String insert(List<List<Novel>> novels) {
        int i = 1;
        novelMapper.truncateAll();
        for (List<Novel> novelList: novels){
            for (Novel novel: novelList){
                if (StringUtils.isNotBlank(novel.getName())){
                    novelMapper.insert(novel);
                    System.out.println(i++ + "条");
                }
            }
        }
        int count = novelMapper.selectAll();
        System.out.println("一共插入" + count + "条");
        return "success";
    }

    @Override
    public String create(List<Novel> novels) {
        for (Novel novel: novels){
            if (StringUtils.isNotBlank(novel.getName())){
                novelMapper.insert(novel);
            }
        }
        return "success";
    }

    @Override
    public List<Novel> search(String key) {
        List<Novel> novels = novelMapper.selectByKey(key);
        return novels;
    }
}
