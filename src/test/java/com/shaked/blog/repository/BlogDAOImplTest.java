package com.shaked.blog.repository;

import com.shaked.blog.model.Blog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by USER1 on 1/30/2018.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {BlogDAOImplTest.TestCacheConfig.class, BlogDAOImpl.class})
public class BlogDAOImplTest {

    @Autowired
    BlogDAO blogDAO;

    @MockBean
    BlogRepository blogRepository;

    @Test
    public void afterSavingBlogReturnsFromCache() throws Exception {
        Blog blog = new Blog();
        blog.setId("id");
        when(blogRepository.save(any(Blog.class))).thenReturn(blog);
        blogDAO.save(blog);
        Blog blogFromDAO = blogDAO.get("id");
        assertThat(blogFromDAO).isEqualTo(blog);
    }


    @Test
    public void getResaultIsCached() throws Exception {
        Blog blog = new Blog();
        blog.setId("id");
        when(blogRepository.findOne(any(String.class))).thenReturn(blog).thenReturn(null);
        blogDAO.get("id");
        Blog blogFromDAO = blogDAO.get("id");
        assertThat(blogFromDAO).isEqualTo(blog);
    }

    @Test
    public void deleteCausesCacheToInvalidate() throws Exception {
        Blog blog = new Blog();
        blog.setId("id");
        when(blogRepository.save(any(Blog.class))).thenReturn(blog);
        blogDAO.save(blog);
        blogDAO.delete("id");
        Blog blogFromDAO = blogDAO.get("id");
        assertThat(blogFromDAO).isNull();
    }

    @EnableCaching
    public static class TestCacheConfig {

        @Bean
        BlogDAOImpl blogDAO(@Autowired BlogRepository blogRepository) {
            BlogDAOImpl blogDAO = new BlogDAOImpl();
            blogDAO.setBlogRepository(blogRepository);
            return blogDAO;
        }

        @Bean
        CacheManager cacheManager() {
            return new ConcurrentMapCacheManager("top-post", "blog", "trending-blog");
        }
    }

}