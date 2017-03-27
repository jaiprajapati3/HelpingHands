package com.example.hetal13.afinal;

/**
 * Created by Hetal13 on 09-01-2017.
 */

public class ServicePojo_comment {
    private String name_user,feed_user,phone,customerId,email;


    public ServicePojo_comment(){

    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public ServicePojo_comment(String name_user, String feed_user, String phone, String customerId,String email) {
        this.name_user = name_user;
        this.phone=phone;
        this.customerId=customerId;
        this.feed_user = feed_user;
        this.email=email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
     public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName_user() {

        return name_user;
    }
    public void setName_user(String name_user) {
        this.name_user = name_user;
    }

    public String getFeed_user() {
        return feed_user;
    }

    public void setFeed_user(String feed_user) {
        this.feed_user = feed_user;
    }
}
