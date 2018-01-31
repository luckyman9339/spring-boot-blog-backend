package com.shaked.blog.service;

import com.shaked.blog.exception.ServiceBadRquestException;
import com.shaked.blog.model.Blog;
import com.shaked.blog.model.Vote;
import com.shaked.blog.model.VoteRequest;
import com.shaked.blog.repository.BlogDAO;
import com.shaked.blog.repository.VoteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

/**
 * Created by USER1 on 1/28/2018.
 */
@Service
public class VoteServiceImpl implements VoteService {
    private VoteDAO voteDAO;
    private BlogDAO blogDAO;

    @Autowired
    public void setVoteDAO(VoteDAO voteDAO) {
        this.voteDAO = voteDAO;
    }

    @Autowired
    public void setBlogDAO(BlogDAO blogDAO) {
        this.blogDAO = blogDAO;
    }

    @Override
    @CacheEvict(value = "blog", key = "#p0.blogId", beforeInvocation = true)
    public Blog saveVote(VoteRequest voteRequest, String user) {
        if (voteDAO.voteCount() != 0 && voteDAO.find(user, voteRequest.getBlogId()).isPresent()) {
            throw new ServiceBadRquestException();
        }
        Blog blog = blogDAO.get(voteRequest.getBlogId());
        if (blog == null || blog.getId() == null) {
            throw new ServiceBadRquestException();
        }
        voteDAO.save(new Vote(user, voteRequest.getBlogId()));

        updateVoteCount(voteRequest, blog);
        return blogDAO.update(blog);
    }

    private void updateVoteCount(VoteRequest v, Blog blog1) {
        blog1.setVoteCount(blog1.getVoteCount() + (v.isUpVote() ? 1 : -1));
    }

}
