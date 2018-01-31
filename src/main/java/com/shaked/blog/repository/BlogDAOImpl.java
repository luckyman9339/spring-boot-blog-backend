package com.shaked.blog.repository;

import com.shaked.blog.model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

/**
 * Created by USER1 on 1/27/2018.
 */
@Component
public class BlogDAOImpl implements BlogDAO {
    private BlogRepository blogRepository;

    @Autowired
    public void setBlogRepository(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    @Cacheable(value = "trending-blog", key = "#p0", unless = "#result==null")
    public Iterable<Blog> getTopXPost(int x) {
        return blogRepository.getTopXBlogs(x);
    }

    @Override
    public Iterable<Blog> getFirstXPostByField(int x, String field) {
        return blogRepository.findAll(new PageRequest(0, x, new Sort(Sort.Direction.DESC, field))).getContent();
    }

    @Override
    @CacheEvict(value = "top-post", allEntries = true)
    @CachePut(value = "blog", key = "#result.id", unless = "#result==null")
    public Blog save(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "top-post", allEntries = true),
            @CacheEvict(value = "blog", key = "#p0.id")
    }
    )
    @CachePut(value = "blog", key = "#result.id", unless = "#result==null")
    public Blog update(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    @CacheEvict(value = "blog", key = "#p0")
    public void delete(String id) {
        blogRepository.delete(id);
    }

    @Override
    @Cacheable(value = "blog", key = "#p0", unless = "#result==null")
    public Blog get(String id) {
        return blogRepository.findOne(id);
    }

}
