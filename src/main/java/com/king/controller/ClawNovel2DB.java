package com.king.controller;
/*
    author: king
    date: 2018/6/12
*/

import com.king.common.ServerResponse;
import com.king.pojo.Chapter;
import com.king.pojo.ChapterDetail;
import com.king.pojo.Novel;
import com.king.service.IChapterDetailService;
import com.king.service.IChapterService;
import com.king.service.INovel2DB;
import com.king.util.NovelThreadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
public class ClawNovel2DB {

    @Autowired
    private INovel2DB iNovel2DB;

    @Autowired
    private IChapterService iChapterService;

    @Autowired
    private IChapterDetailService iChapterDetailService;

    @RequestMapping("claw.do")
    @ResponseBody
    public String novel2DB(){
        List<List<Novel>> novels = NovelThreadUtil.novelThread("https://www.du1du.la/last1.htm");
        iNovel2DB.insert(novels);
        return "success";
    }

    @RequestMapping("search.do")
    @ResponseBody
    public ServerResponse<List<Novel>> search(String key){
        List<Novel> novels = iNovel2DB.search(key);
        return ServerResponse.createBySuccess(novels);
    }

    @RequestMapping("chapters.do")
    @ResponseBody
    public ServerResponse<List<Chapter>> chapters(String url){
        List<Chapter> chapters = iChapterService.getChapters(url);
        return ServerResponse.createBySuccess(chapters);
    }

    @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse<ChapterDetail> detail(String url){
        ChapterDetail chapterDetail = iChapterDetailService.getChapterDetail(url);
        return ServerResponse.createBySuccess(chapterDetail);
    }
}
