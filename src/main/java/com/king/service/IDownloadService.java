package com.king.service;
/* 
    author：king
    date：2018/6/7  
*/

public interface IDownloadService {
    public void getDownload(String url, String path, String size, int tryTimes);
}
