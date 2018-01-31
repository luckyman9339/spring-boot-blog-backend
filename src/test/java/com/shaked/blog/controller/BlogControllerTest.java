package com.shaked.blog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.shaked.blog.model.Blog;
import com.shaked.blog.service.BlogService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by USER1 on 1/30/2018.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(BlogController.class)
public class BlogControllerTest {

    public static final String TEST_BLOG_TITLE = "test-blog-title";
    public static final String TEST_BLOG_BODY = "test-blog-body";
    public static final String TEST_BLOG_AUTHOR = "shaked";
    public static final String EMPTY_JSON_OBJECT = "{}";
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BlogService blogService;

    @Before
    public void setUp() throws Exception {
        objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    }

    @Test
    public void whenFileCreatedSuccesfullyExcpetedOutputReturns() throws Exception {

        Blog blog = new Blog();
        blog.setTitle(TEST_BLOG_TITLE);
        blog.setBody(TEST_BLOG_BODY);
        blog.setAuthor(TEST_BLOG_AUTHOR);
        when(blogService.save(any())).thenReturn(blog);
        Map<String, String> requestContent = new HashMap<>();
        requestContent.put("title", TEST_BLOG_TITLE);
        requestContent.put("body", TEST_BLOG_BODY);
        requestContent.put("author", TEST_BLOG_AUTHOR);
        mockMvc.perform(
                post("/blog")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsBytes(requestContent))
        ).andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.title", is(TEST_BLOG_TITLE)))
                .andExpect(jsonPath("$.body", is(TEST_BLOG_BODY)))
                .andExpect(jsonPath("$.author", is(TEST_BLOG_AUTHOR)));
    }

    @Test
    public void whenRequestIsInvalidReturnsBadRequest() throws Exception {
        mockMvc.perform(
                post("/blog")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsBytes(EMPTY_JSON_OBJECT))
        ).andDo(print())
                .andExpect(status().isBadRequest());
    }
}