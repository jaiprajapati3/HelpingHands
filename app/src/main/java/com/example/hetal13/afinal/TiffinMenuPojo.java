package com.example.hetal13.afinal;

/**
 * Created by Hetal13 on 18-03-2017.
 */

public class TiffinMenuPojo {
    private String category,menu;

    public TiffinMenuPojo(String category, String menu) {
        this.category = category;
        this.menu=menu;
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
