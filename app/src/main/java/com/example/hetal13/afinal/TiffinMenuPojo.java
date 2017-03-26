package com.example.hetal13.afinal;

import java.util.Date;

/**
 * Created by Hetal13 on 18-03-2017.
 */

public class TiffinMenuPojo {
    private String category,menu;
    public String date;

    public TiffinMenuPojo(String category, String menu,String date) {
        this.category = category;
        this.date=date;
        this.menu=menu;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

}
