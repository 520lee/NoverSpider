package com.king.util;
/*
    author: king
    date: 2018/6/13
*/

import com.king.pojo.Novel;
import com.king.service.INovelService;
import com.king.service.impl.NovelServiceImpl;

import java.util.*;
import java.util.concurrent.*;

public final class NovelThreadUtil {

    public static List<List<Novel>> novelThread(String url){ //https://www.du1du.la/last1.htm
        List<String> urls = new ArrayList<>();
        List<List<Novel>> novelList = new ArrayList<>();
        String urlPre = url.split("1.h")[0];
        String urlPost = url.split("1.h")[1];
        Map<String, List<String>> tasksMap = new HashMap<>();
        int total = 1000;
        int threadNum = (int) Math.ceil(total*1.0 / 5);
        ExecutorService service = Executors.newFixedThreadPool(threadNum);
        for (int i = 1; i <= total; i++){
            url = urlPre + i + ".h" + urlPost;
            urls.add(url);
        }
        int fromIndex;
        int toIndex;
        for (int i = 0; i < threadNum; i++){
            fromIndex = i * 5;
            toIndex = (i == threadNum - 1 ? total : i * 5 + 5);
            tasksMap.put((fromIndex + 1) +"-"+ toIndex, urls.subList(fromIndex, toIndex));
        }
        List<Future<String>> tasks = new ArrayList<>();
        Set<String> keySet = tasksMap.keySet();
        for (String key: keySet){
            List<String> subUrl = tasksMap.get(key);
            tasks.add(service.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    for (String uri: subUrl){
                        INovelService iNovelService = new NovelServiceImpl();
                        List<Novel> novels = iNovelService.getNovels(uri, 6);
                        for (Novel novel: novels){
                        }
                        novelList.add(novels);
                    }
                    return key;
                }
            }));
        }
        service.shutdown();
        for (Future<String> task: tasks){
            try {
                System.out.println(task.get() + "获取完成");
            } catch (Exception e) {
                System.out.println("获取失败");
            }
        }
        return novelList;
    }
}

