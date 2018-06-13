package com.king;
/*
    author：king
    date：2018/6/7
*/

import com.king.pojo.Chapter;
import com.king.pojo.ChapterDetail;
import com.king.pojo.Novel;
import com.king.service.*;
import com.king.service.impl.*;
import com.king.util.NovelThreadUtil;
import com.king.util.PropertiesUtil;
import org.junit.Test;

import java.util.List;

public class TestCase {
    @Test
    public void test1(){
        IChapterService service = new ChapterServiceImpl();
        List<Chapter> chapters = service.getChapters("https://qxs.la/226886/");
        for (Chapter chapter: chapters){
            System.out.println(chapter);
        }
//        https://www.xxbiquge.com/1_1413/
//        https://www.x23us.com/html/69/69123/
//        https://www.du1du.la/xs226986/
//        https://www.ybdu.com/xiaoshuo/21/21716/
    }

    @Test
    public void test2(){
        IChapterDetailService service = new ChapterDetailServiceImpl();
//        ChapterDetail chapterDetail = service.getChapterDetail("https://www.ybdu.com/xiaoshuo/21/21716/10515516.html");
//        ChapterDetail chapterDetail = service.getChapterDetail("https://www.xxbiquge.com/1_1413/1049417.html");
//        ChapterDetail chapterDetail = service.getChapterDetail("https://www.x23us.com/html/69/69123/31147765.html");
//        ChapterDetail chapterDetail = service.getChapterDetail("https://www.du1du.la/xs226986/42304444.htm");
        ChapterDetail chapterDetail = service.getChapterDetail("https://qxs.la/226886/44288050/");
        System.out.println(chapterDetail);
        System.out.println(chapterDetail.getContent());
    }
    @Test
    public void test3(){
        IDownloadService service = new DownloadServiceImpl();
        service.getDownload("https://www.du1du.la/xs226986/", PropertiesUtil.getProperty("path"), PropertiesUtil.getProperty("size"), Integer.parseInt(PropertiesUtil.getProperty("tryTimes","6")));
    }
    @Test
    public void test4(){
        INovelService service = new NovelServiceImpl();
//        List<Novel> novels = service.getNovels("https://qxs.la/last1.htm", Integer.parseInt(PropertiesUtil.getProperty("tryTimes")));
//        List<Novel> novels = service.getNovels("https://www.du1du.la/last1.htm", Integer.parseInt(PropertiesUtil.getProperty("tryTimes")));
//        List<Novel> novels = service.getNovels("https://www.x23us.com/class/10_1.html", Integer.parseInt(PropertiesUtil.getProperty("tryTimes")));
//        List<Novel> novels = service.getNovels("https://www.37zw.net/xiaoshuodaquan/", Integer.parseInt(PropertiesUtil.getProperty("tryTimes")));
        List<Novel> novels = service.getNovels("https://www.du1du.la/last36.htm", Integer.parseInt(PropertiesUtil.getProperty("tryTimes")));
        System.out.println(novels);
    }
    @Test
    public void test5(){
//        List<List<Novel>> novels = NovelThreadUtil.novelThread("https://www.du1du.la/last1.htm");
//        for (List<Novel> novelss: novels){
//            for (Novel novel: novelss){
//                System.out.println(novel);
//            }
//        }
        List<List<Novel>> novelList = NovelThreadUtil.novelThread("https://www.x23us.com/class/10_1.html");
        for (List<Novel> novelLists: novelList){
            for (Novel novel: novelLists){
                System.out.println(novel);
            }
        }
    }
    @Test
    public void test6(){
        List<List<Novel>> novels = NovelThreadUtil.novelThread("https://www.du1du.la/last1.htm");
        INovel2DB iNovel2DB = new Novel2DBImpl();
        iNovel2DB.insert(novels);
    }
}
