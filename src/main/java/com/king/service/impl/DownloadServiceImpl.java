package com.king.service.impl;
/* 
    author：king
    date：2018/6/7  
*/

import com.king.pojo.Chapter;
import com.king.pojo.ChapterDetail;
import com.king.service.IChapterDetailService;
import com.king.service.IChapterService;
import com.king.service.IDownloadService;
import com.king.util.MultiFileMergeUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.*;

@Service("iDownloadService")
public class DownloadServiceImpl implements IDownloadService {
    @Override
    public void getDownload(String url, String path, String size, int tryTimes) {
        int sizes = Integer.parseInt(size);
        IChapterService chapterService = new ChapterServiceImpl();
        List<Chapter> chapters = chapterService.getChapters(url);
        int total = chapters.size();
        int threadNum = (int) Math.ceil(total*1.0 / sizes);
        ExecutorService service = Executors.newFixedThreadPool(threadNum);
        int fromIndex;
        int toIndex;
        Map<String, List<Chapter>> downloadTasks = new HashMap<>();
        for (int i = 0; i < threadNum; i++){
            fromIndex = i * sizes;
            toIndex = (i == threadNum - 1 ? total : fromIndex + sizes);
            downloadTasks.put(fromIndex + "-" + (toIndex - 1), chapters.subList(fromIndex, toIndex));
        }
        List<Future<String>> tasks = new ArrayList<>();
        Set<String> keySet = downloadTasks.keySet();
        for (String key: keySet){
            List<Chapter> task = downloadTasks.get(key);
            tasks.add(service.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    String filePath = path + "/" + key + ".txt";
                    PrintWriter out = new PrintWriter(filePath);
                    for (Chapter chapter: task){
                        IChapterDetailService service = new ChapterDetailServiceImpl();
                        ChapterDetail detail;
                        for (int i = 0; i < tryTimes; i++){
                            detail = service.getChapterDetail(chapter.getUrl());
                            out.write(detail.getTitle());
                            out.write(detail.getContent());
                            if (StringUtils.isNotBlank(detail.toString())){
                                break;
                            }else {
                                System.out.println("正在尝试第"+(i+1)+"下载");
                            }
                        }
                    }
                    out.close();
                    return filePath;
                }
            }));
        }
        service.shutdown();
        for (Future<String> task: tasks){
            try {
                System.out.println(task.get()+"下载完成");
            } catch (Exception e) {
                System.out.println("下载失败");
            }
        }
        MultiFileMergeUtil.mergeFiles(path, "元尊");
    }
}
