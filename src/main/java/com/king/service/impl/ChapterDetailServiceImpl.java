package com.king.service.impl;
/* 
    author：king
    date：2018/6/7  
*/

import com.king.common.NovelSiteEnum;
import com.king.pojo.ChapterDetail;
import com.king.service.IChapterDetailService;
import com.king.util.HttpHelperUtil;
import com.king.util.XmlReadUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("iChapterDetailService")
public class ChapterDetailServiceImpl implements IChapterDetailService {
    @Override
    public ChapterDetail getChapterDetail(String url) {
        Map<String, String> rule = XmlReadUtil.getConfig(NovelSiteEnum.getEnumByUrl(url));
        String html = HttpHelperUtil.getHttpClient(url);
        html = html.replace("&nbsp;", " ").replace("<br/>", "${line}").replace("<br />", "${line}");
        Document document = Jsoup.parse(html);
        document.setBaseUri(url);
        ChapterDetail chapterDetail = new ChapterDetail();
        chapterDetail.setTitle(document.select(rule.get("chapter-title-selector")).text());
        chapterDetail.setContent(document.select(rule.get("chapter-content-selector")).text().replace("${line}", "<br/>"));
        String[] splits = rule.get("chapter-prev-selector").split(",");
        splits = parseSelector(splits);
        chapterDetail.setPrev(document.select(splits[0]).get(Integer.parseInt(splits[1])).absUrl("href"));
        splits = rule.get("chapter-next-selector").split(",");
        splits = parseSelector(splits);
        chapterDetail.setNext(document.select(splits[0]).get(Integer.parseInt(splits[1])).absUrl("href"));
        return chapterDetail;
    }

    private String[] parseSelector(String[] splits){
        String[] newsplits = new String[2];
        if (splits.length == 1){
            newsplits[0] = splits[0];
            newsplits[1] = "0";
            return newsplits;
        }else {
            return splits;
        }
    }
}
