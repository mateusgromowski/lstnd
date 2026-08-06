package com.lstnd.lstnd.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lstnd.lstnd.model.Review;
import com.lstnd.lstnd.service.ReviewService;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    ReviewService service;

    public ReviewController(ReviewService service) {
        this.service = service;
    }

    @GetMapping("/{spotifyId}")
    public Review getReview(@PathVariable String spotifyId) {
        return service.getReview(spotifyId);
    }
}
