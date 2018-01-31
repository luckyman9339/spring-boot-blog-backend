package com.shaked.blog.service;

import com.shaked.blog.model.Blog;
import com.shaked.blog.model.VoteRequest;

/**
 * Created by USER1 on 1/30/2018.
 */
public interface VoteService {
    Blog saveVote(VoteRequest voteRequest, String user);
}
