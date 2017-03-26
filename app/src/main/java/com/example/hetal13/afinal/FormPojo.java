package com.example.hetal13.afinal;

/**
 * Created by Hetal13 on 26-03-2017.
 */

public class FormPojo {
    String title,subtitle;

    public FormPojo(String title, String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
}
