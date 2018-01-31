package com.shaked.blog.repository;

import com.shaked.blog.model.Blog;

/**
 * Created by USER1 on 1/27/2018.
 */
public interface BlogDAO {
    Iterable<Blog> getTopXPost(int x);

    Iterable<Blog> getFirstXPostByField(int x, String field);

    Blog save(Blog blog);

    Blog update(Blog blog);

    void delete(String id);

    Blog get(String id);

}
