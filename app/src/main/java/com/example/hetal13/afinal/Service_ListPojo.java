package com.example.hetal13.afinal;

/**
 * Created by Hetal13 on 25-02-2017.
 */

public class Service_ListPojo {
    private int thumbnail;
    private String catname;
    private int id;
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Service_ListPojo(){

    }
    public Service_ListPojo(int thumbnail,String catname,int id,int count){
        this.thumbnail=thumbnail;
        this.catname=catname;
        this.id=id;
        this.count=count;

    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getCatname() {
        return catname;
    }

    public void setCatname(String catname) {
        this.catname = catname;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
