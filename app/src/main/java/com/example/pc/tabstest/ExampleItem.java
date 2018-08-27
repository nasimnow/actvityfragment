package com.example.pc.tabstest;

public class ExampleItem {
    private String mImageUrl;
    private String mCreator;
    private String mCat;
    public ExampleItem(String imageurl, String creator, String cat){
        mImageUrl = imageurl;
        mCreator = creator;
        mCat = cat;
    }
    public String getmImageUrl(){
        return mImageUrl;
    }
    public String getmCreator(){
        return mCreator;
    }
    public String getmCat(){
        return mCat;
    }
}
