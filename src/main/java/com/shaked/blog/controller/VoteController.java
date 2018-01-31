package com.shaked.blog.controller;

import com.shaked.blog.model.Blog;
import com.shaked.blog.model.VoteRequest;
import com.shaked.blog.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * Created by USER1 on 1/28/2018.
 */
@RestController
public class VoteController {

    private VoteService voteService;

    @Autowired
    public void setVoteService(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping(value = "/vote", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Blog vote(@RequestBody @NotNull VoteRequest voteRequest, @RequestAttribute String user) {
        return voteService.saveVote(voteRequest, user);
    }
}
