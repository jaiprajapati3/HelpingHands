package com.example.hetal13.afinal;

import com.amulyakhare.textdrawable.TextDrawable;

/**
 * Created by Hetal13 on 22-12-2016.
 */

public class ServicePojo {
    public ServicePojo(String id, String name, String desc,int thumbnail) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.thumbnail=thumbnail;
    }

    private String id,name,desc;
    private int thumbnail;

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    private int rating;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
