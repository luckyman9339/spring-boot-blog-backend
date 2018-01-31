package com.shaked.blog.service;

import com.shaked.blog.model.Blog;
import com.shaked.blog.repository.BlogDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by USER1 on 1/27/2018.
 */
@Service
public class BlogServiceImpl implements BlogService {
    private BlogDAO blogDAO;

    @Autowired
    public void setBlogDAO(BlogDAO blogDAO) {
        this.blogDAO = blogDAO;
    }

    @Override
    public Blog save(Blog blog) {
        return blogDAO.save(blog);
    }

    @Override
    public Blog update(Blog blog, String id) {
        Blog blogFromDb = get(id);
        blogFromDb.setAuthor(blog.getAuthor());
        blogFromDb.setBody(blog.getBody());
        blogFromDb.setTitle(blog.getTitle());
        return save(blogFromDb);
    }

    @Override
    public Blog get(String id) {
        return blogDAO.get(id);
    }

    @Override
    public void delete(String id) {
        blogDAO.delete(id);
    }

    @Override
    public Iterable<Blog> getMostRecentPosts() {
        return blogDAO.getFirstXPostByField(5, "created");
    }

    @Override
    public Iterable<Blog> getTopPosts() {
        return blogDAO.getTopXPost(10);
    }
}
