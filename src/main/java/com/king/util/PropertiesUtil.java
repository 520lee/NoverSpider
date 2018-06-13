package com.king.util;
/* 
    author：king
    date：2018/6/7  
*/

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public final class PropertiesUtil {
    private static Properties properties = new Properties();
    static {
        init();
    }

    private static void init(){
        try {
            properties.load(new InputStreamReader(Properties.class.getResourceAsStream("/download.properties")));
        } catch (IOException e) {
            System.out.println("读取配置文件失败");
        }
    }

    public static String getProperty(String key){
        String value = properties.getProperty(StringUtils.trim(key));
        if (StringUtils.isBlank(value)){
            return null;
        }else {
            return StringUtils.trim(value);
        }
    }

    public static String getProperty(String key, String defaultValue){
        String value = properties.getProperty(StringUtils.trim(key));
        if (StringUtils.isBlank(value)){
            return defaultValue;
        }else {
            return StringUtils.trim(value);
        }
    }
}
