package com.example.hetal13.afinal;
/**
 * Created by Hetal13 on 01-04-2017.
 */
public class offerSPPojo {
    String date,desc;
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public  offerSPPojo(String desc,String date){
        this.desc=desc;
        this.date=date;
    }
}
