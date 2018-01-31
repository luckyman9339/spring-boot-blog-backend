package com.shaked.blog.model;

/**
 * Created by USER1 on 1/28/2018.
 */
public class VoteRequest {
    private String blogId;
    private boolean upVote;

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }

    public boolean isUpVote() {
        return upVote;
    }

    public void setUpVote(boolean upVote) {
        this.upVote = upVote;
    }
}
