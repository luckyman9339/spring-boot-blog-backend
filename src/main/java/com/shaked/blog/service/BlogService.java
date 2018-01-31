package com.shaked.blog.service;

import com.shaked.blog.model.Blog;

/**
 * Created by USER1 on 1/30/2018.
 */
public interface BlogService {
    Blog save(Blog blog);

    Blog update(Blog blog, String id);

    Blog get(String id);

    void delete(String id);

    Iterable<Blog> getMostRecentPosts();

    Iterable<Blog> getTopPosts();
}
