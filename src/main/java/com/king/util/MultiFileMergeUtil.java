package com.king.util;
/* 
    author：king
    date：2018/6/8  
*/

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public final class MultiFileMergeUtil {
    public static void mergeFiles(String path, String newFileName){
        if (StringUtils.isBlank(newFileName)){
            newFileName = path + "/merge.txt";
        }else {
            newFileName = path +"/"+ newFileName + ".txt";
        }
        File[] files = new File(path).listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".txt");
            }
        });
        Arrays.sort(files, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                String olIndex = o1.getName().split("-")[0];
                String o2Index = o2.getName().split("-")[0];
                if (Integer.parseInt(olIndex) > Integer.parseInt(o2Index)){
                    return 1;
                }else if (Integer.parseInt(olIndex) == Integer.parseInt(o2Index)){
                    return 0;
                }else {
                    return -1;
                }
            }
        });
        PrintWriter out = null;
        try {
            out = new PrintWriter(newFileName);
            for (File file: files){
                BufferedReader buf = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                String line;
                while ((line = buf.readLine()) != null){
                    out.write(line);
                }
                buf.close();
                file.delete();
            }
        } catch (Exception e) {
            System.out.println("合并失败");
        }
        out.close();
        System.out.println("合并完成");
    }
}
