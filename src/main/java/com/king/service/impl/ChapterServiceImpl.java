package com.king.service.impl;
/* 
    author：king
    date：2018/6/7  
*/

import com.king.common.NovelSiteEnum;
import com.king.pojo.Chapter;
import com.king.service.IChapterService;
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

@Service("iChapterService")
public class ChapterServiceImpl implements IChapterService {
    @Override
    public List<Chapter> getChapters(String url) {
        List<Chapter> chapters = new ArrayList<>();
        Map<String, String> rule = XmlReadUtil.getConfig(NovelSiteEnum.getEnumByUrl(url));
        String html = HttpHelperUtil.getHttpClient(url);
        Document document = Jsoup.parse(html);
        document.setBaseUri(url);
        Elements elements = document.select(rule.get("chapter-list-selector"));
        for (Element element: elements){
            Chapter chapter = new Chapter();
            String title = element.text();
            String href = element.absUrl("href");
            chapter.setTitle(title);
            chapter.setUrl(href);
            chapters.add(chapter);
        }
        return chapters;
    }
}
