package com.shaked.blog.repository;

import com.shaked.blog.model.Vote;

import java.util.Optional;

/**
 * Created by USER1 on 1/28/2018.
 */
public interface VoteDAO {
    Optional<Vote> find(String userId, String blogId);

    Optional<Vote> find(Vote v);

    Vote save(Vote v);

    long voteCount();
}
