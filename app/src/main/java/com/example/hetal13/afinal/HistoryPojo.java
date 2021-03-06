package com.example.hetal13.afinal;

import com.amulyakhare.textdrawable.TextDrawable;

/**
 * Created by Hetal13 on 01-11-2016.
 */

public class HistoryPojo  {

    private String email,mobile,name,date,time;
    private TextDrawable img_id;
    private int flag;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public HistoryPojo(TextDrawable img_id,String name, String email, String mobile,   int flag,String date, String time) {
        this.email = email;
        this.mobile = mobile;
        this.name = name;
        this.date = date;
        this.time = time;
        this.img_id = img_id;
        this.flag = flag;
    }

    public void setTime(String time) {
        this.time = time;
    }

//    public HistoryPojo(TextDrawable img_id, String name, String email, String mobile, int flag)
//    {
//        this.setFlag(flag);
//        this.setImg_id(img_id);
//        this.setName(name);
//        this.setEmail(email);
//        this.setMobile(mobile);
//
//    }
    public int getFlag() {
        return flag;}


    public String getEmail() {
        return email;
    }

    public TextDrawable getImg_id() {
        return img_id;
    }

    public void setImg_id(TextDrawable img_id) {
        this.img_id = img_id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setFlag(int flag){this.flag=flag;}
}
