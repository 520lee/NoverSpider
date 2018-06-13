package com.king.util;
/*
    author: king
    date：2018/6/10
*/

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;

public final class DateUtil {
    private static String defaultFormat = "yyyy-MM-dd HH:mm";

    public static Date string2Date(String str){
        Date date = null;
        SimpleDateFormat format = new SimpleDateFormat(defaultFormat);
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            System.out.println("时间转换失败");
        }
        return date;
    }
    public static Date string2Date(String str, String format){
        Date date = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            date = dateFormat.parse(str);
        } catch (ParseException e) {
            System.out.println("时间转换失败");
        }
        return date;
    }

    public static String date2String(Date date){
        SimpleDateFormat format = new SimpleDateFormat(defaultFormat);
        String str = format.format(date);
        return str;
    }

    public static String date2String(Date date, String format){
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        String str = dateFormat.format(date);
        return str;
    }
}
