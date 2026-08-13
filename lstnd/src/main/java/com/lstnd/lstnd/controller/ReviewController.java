package com.lstnd.lstnd.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lstnd.lstnd.DTO.NewReviewDTO;
import com.lstnd.lstnd.model.Review;
import com.lstnd.lstnd.service.ReviewService;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    ReviewService service;

    public ReviewController(ReviewService service) {
        this.service = service;
    }

    @GetMapping
    public List<Review> getAllReviews(@RequestParam String spotifyId) throws IllegalArgumentException {
        return service.getAllReviews(spotifyId).reversed();
    }

    @PostMapping("/{spotifyId}")
    public void createReview(@PathVariable String spotifyId, @RequestBody NewReviewDTO dto) throws IllegalArgumentException {
        service.createReview(spotifyId, dto);
    }
}
