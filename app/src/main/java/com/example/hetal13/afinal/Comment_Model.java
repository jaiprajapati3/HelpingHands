package com.example.hetal13.afinal;

/**
 * Created by Lenovo on 1/28/2017.
 */

public class Comment_Model {
    private String Name;
    private String Comment;

    Comment_Model()
    {

    }
    Comment_Model(String name,String comment)
    {
        this.Name=name;
        this.Comment=comment;
    }
    public void setName(String name)
    {
        this.Name=name;
    }
    public String getName()
    {
        return Name;
    }
    public void setComment(String comment)
    {
        this.Name=comment;
    }
    public String getComment()
    {
        return Comment;
    }



}
