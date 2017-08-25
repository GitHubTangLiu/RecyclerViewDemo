package com.example.admin.recyclerviewdemo;

/**
 * Created by tangliu on 2017/8/11.
 * desc:
 */

public class Data {

    private String title;
    private int imageurl;

    public Data(String title, int imageurl) {
        this.title = title;
        this.imageurl = imageurl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageurl() {
        return imageurl;
    }

    public void setImageurl(int imageurl) {
        this.imageurl = imageurl;
    }
}
