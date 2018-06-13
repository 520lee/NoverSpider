package com.king.util;
/* 
    author：king
    date：2018/6/7  
*/

import com.king.common.NovelSiteEnum;
import com.king.pojo.Chapter;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class HttpHelperUtil {
    public static String getOkHttp(String url){
        OkHttpClient httpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = httpClient.newCall(request).execute();
            if (response.body() != null){
                String html = new String(response.body().bytes(), "GBK");
                Document document = Jsoup.parse(html);
                Elements elements = document.select("#list dd a");
                for (Element element: elements){
                    System.out.println(element);
                }
            }
        } catch (IOException e) {
            System.out.println("请求响应超时");
        }
        return null;
    }

    public static String getHttpClient(String url){
        Map<String, String> rule = XmlReadUtil.getConfig(NovelSiteEnum.getEnumByUrl(url));
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.79 Safari/537.36");
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String html = EntityUtils.toString(entity, rule.get("charset"));
            return html;
        } catch (IOException e) {
            throw new RuntimeException("请求响应超时");
        }
    }
}
