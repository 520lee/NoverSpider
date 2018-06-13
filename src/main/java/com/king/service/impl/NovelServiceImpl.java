package com.king.service.impl;
/*
    author: king
    date：2018/6/10
*/

import com.king.common.NovelSiteEnum;
import com.king.pojo.Novel;
import com.king.service.INovelService;
import com.king.util.DateUtil;
import com.king.util.HttpHelperUtil;
import com.king.util.XmlReadUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("iNovelService")
public class NovelServiceImpl implements INovelService {
    @Override
    public List<Novel> getNovels(String url, int tryTimes) {
        List<Novel> novels = new ArrayList<>();
        NovelSiteEnum novelSiteEnum = NovelSiteEnum.getEnumByUrl(url);
        Map<String, String> rule = XmlReadUtil.getConfig(novelSiteEnum);
        String html = HttpHelperUtil.getHttpClient(url);
        Document document = Jsoup.parse(html);
        document.setBaseUri(url);
        Elements elements = document.select(rule.get("novel-selector"));
        for (Element element: elements){
            Novel novel = new Novel();
            switch (novelSiteEnum){
                case DUYIDU:
                case QUANXIAOSHUO:
                    Elements lis = element.getElementsByTag("li");
                    novel.setName(lis.get(0).child(0).text());
                    novel.setUrl(lis.get(0).child(0).absUrl("href"));
                    novel.setAuthor(lis.get(2).child(0).text());
                    novel.setLastUpdateChapter(lis.get(1).child(0).text());
                    novel.setLastUpdateChapterUrl(lis.get(1).child(0).absUrl("href"));
                    novel.setUpdateDate(DateUtil.string2Date(lis.get(3).text()));
                    novel.setStatus(10);
                    novel.setPlatformId(novelSiteEnum.getId());
                    novels.add(novel);
                    break;
                case DINGDIAN:
                    if (!element.equals(elements.first())){
                        Elements tds = element.getElementsByTag("td");
                        novel.setName(tds.get(0).child(1).text());
                        novel.setUrl(tds.get(0).child(1).absUrl("href"));
                        novel.setAuthor(tds.get(2).text());
                        novel.setLastUpdateChapter(tds.get(1).child(0).text());
                        novel.setLastUpdateChapterUrl(tds.get(1).child(0).absUrl("href"));
                        novel.setUpdateDate(DateUtil.string2Date(tds.get(4).text(), "yy-MM-dd"));
                        novel.setStatus(NovelSiteEnum.getStatusByStr(tds.get(5).text()));
                        novel.setPlatformId(novelSiteEnum.getId());
                        novels.add(novel);
                        break;
                    }
                case SANQIWENXUE:
                    novel.setName(element.child(0).text());
                    novel.setUrl(element.child(0).absUrl("href"));
                    novel.setAuthor(element.text().split("/")[0]);
                    novel.setPlatformId(novelSiteEnum.getId());
                    novels.add(novel);
                    break;
                default:
                    System.out.println("不支持");
            }
        }
        return novels;
    }
}
