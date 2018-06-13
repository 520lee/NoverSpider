package com.king.common;
/* 
    author：king
    date：2018/6/7  
*/

public enum NovelSiteEnum {
    LIANZAI(10, "连载"),
    WANJIE(11, "完结"),
    WEIZHI(12, "未知"),
    XINBIQUGE(1, "xxbiquge.com"),
    DINGDIAN(2, "x23us.com"),
    DUYIDU(3, "du1du.la"),
    BIQUGE(4, "ybdu.com"),
    SANQIWENXUE(5, "37zw.net"),
    QUANXIAOSHUO(6, "qxs.la");
    private int id;
    private String value;

    NovelSiteEnum(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public static NovelSiteEnum getEnumById(int id){
        switch (id){
            case 1:
                return XINBIQUGE;
            case 2:
                return DINGDIAN;
            case 3:
                return DUYIDU;
            case 4:
                return BIQUGE;
            case 5:
                return SANQIWENXUE;
            case 6:
                return QUANXIAOSHUO;
            default:
                throw new RuntimeException("此id不存在");
        }
    }

    public static NovelSiteEnum getEnumByUrl(String url){
        for (NovelSiteEnum novelSiteEnum: values()){
            if (url.contains(novelSiteEnum.value)){
                return novelSiteEnum;
            }
        }
        throw new RuntimeException("不支持该网站");
    }

    public static int getStatusByStr(String str){
        for (NovelSiteEnum novelSiteEnum: values()){
            if (str.contains(novelSiteEnum.value)){
                return novelSiteEnum.id;
            }
        }
        return -1;
    }

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }
}
