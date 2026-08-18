package com.lstnd.lstnd.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.lstnd.lstnd.DTO.NewReviewDTO;
import com.lstnd.lstnd.model.Review;
import com.lstnd.lstnd.service.ReviewService;

import tools.jackson.databind.ObjectMapper;

@WebMvcTest(ReviewController.class)
public class ReviewControllerTest {
    @MockitoBean
    private ReviewService service;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper om;

    @Test
    void shouldReturn200Test() throws Exception {
        Review review1 = Review.builder().id(1L).userName("Peter").review("Muito bom").score(5).build();
        Review review2 = Review.builder().id(2L).userName("Retep").review("Muito ruim").score(1).build();
        when(service.getAllReviews("abc123")).thenReturn(List.of(review1, review2));
        mockMvc.perform(get("/reviews").param("spotifyId", "abc123")).andExpect(status().isOk());
        verify(service).getAllReviews("abc123");
    }

    @Test
    void shouldReturn200AndEmptyListTest() throws Exception {
        when(service.getAllReviews("abc123")).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/reviews").param("spotifyId", "abc123")).andExpect(status().isOk())
                .andExpect(content().json("[]"));
        verify(service).getAllReviews("abc123");
    }

    @Test
    void shouldReturn200PostTest() throws Exception {
        NewReviewDTO review = NewReviewDTO.builder()
                .userName("Peter")
                .review("Muito bom")
                .score(5)
                .build();
        String json = om.writeValueAsString(review);
        mockMvc.perform(post("/reviews/abc123").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());

        verify(service).createReview("abc123", review);
    }
}
