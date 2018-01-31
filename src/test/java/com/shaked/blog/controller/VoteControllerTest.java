package com.shaked.blog.controller;

import com.shaked.blog.service.VoteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by USER1 on 1/30/2018.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(VoteController.class)
public class VoteControllerTest {

    @MockBean
    VoteService voteService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void whenUserHeaderIsMissingReturnStatusForbidden() throws Exception {
        mockMvc.perform(
                post("/vote")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andDo(print())
                .andExpect(status().isForbidden());
    }
}