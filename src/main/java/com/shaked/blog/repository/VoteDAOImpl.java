package com.shaked.blog.repository;

import com.arangodb.springframework.core.ArangoOperations;
import com.shaked.blog.model.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by USER1 on 1/28/2018.
 */
@Component
public class VoteDAOImpl implements VoteDAO {

    private VoteRepository voteRepository;
    private ArangoOperations arangoOperations;

    @Autowired
    public void setVoteRepository(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @Autowired
    public void setArangoOperations(ArangoOperations arangoOperations) {
        this.arangoOperations = arangoOperations;
    }

    @Override
    public Optional<Vote> find(String userId, String blogId) {
        return voteRepository.findByUserAndBlog(userId, blogId);
    }

    @Override
    public Optional<Vote> find(Vote v) {
        return voteRepository.findByUserAndBlog(v.getUser(), v.getBlog());
    }

    @Override
    public Vote save(Vote v) {
        return voteRepository.save(v);
    }

    @Override
    public long voteCount() {
        return arangoOperations.collection(Vote.class).count();
    }
}
