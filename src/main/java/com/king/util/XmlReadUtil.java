package com.king.util;
/* 
    author：king
    date：2018/6/7  
*/

import com.king.common.NovelSiteEnum;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class XmlReadUtil {
    private static Map<NovelSiteEnum, Map<String, String>> rules = new HashMap<>();
    static {
        init();
    }
    private static void init(){
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(XmlReadUtil.class.getResourceAsStream("/Spider-Rule.xml"));
            Element root = document.getRootElement();
            List<Element> sites = root.elements("site");
            for (Element site: sites){
                Map<String, String> rule = new HashMap<>();
                List<Element> subs = site.elements();
                for (Element sub: subs){
                    String name = sub.getName();
                    String text = sub.getText();
                    rule.put(name, text);
                }
                rules.put(NovelSiteEnum.getEnumByUrl(rule.get("url")), rule);
            }
        } catch (DocumentException e) {
            System.out.println("读取配置文件失败");
        }
    }

    public static Map<String, String> getConfig(NovelSiteEnum novelSiteEnum ){
        return rules.get(novelSiteEnum);
    }
}
