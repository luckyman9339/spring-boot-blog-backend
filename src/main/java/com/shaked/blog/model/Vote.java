package com.shaked.blog.model;

import com.arangodb.springframework.annotation.Document;

/**
 * Created by USER1 on 1/28/2018.
 */
@Document
public class Vote {

    public Vote() {
    }

    public Vote(String user, String blog) {
        this.user = user;
        this.blog = blog;
    }

    private String user;
    private String blog;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }
}
