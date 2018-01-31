package com.shaked.blog.controller;

import com.shaked.blog.model.Blog;
import com.shaked.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by USER1 on 1/26/2018.
 */
@RestController
public class BlogController {

    BlogService blogService;

    @Autowired
    public void setBlogService(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping(value = "/blog", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    Blog create(@RequestBody Blog blog) {
        Blog savedObject = blogService.save(blog);
        return savedObject;
    }

    @PutMapping(value = "/blog/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    Blog update(@RequestBody Blog blog, @PathVariable String id) {
        return blogService.update(blog, id);
    }

    @GetMapping(value = "/blog/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    Blog read(@PathVariable String id) {
        return blogService.get(id);
    }

    @DeleteMapping(value = "/blog/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    void delete(@PathVariable String id) {
        blogService.delete(id);
    }

    @GetMapping(value = "/blog/recent", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    Iterable<Blog> recentPosts() {
        return blogService.getMostRecentPosts();
    }

    @GetMapping(value = "/blog/top", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    Iterable<Blog> topPosts() {
        return blogService.getTopPosts();
    }

}
