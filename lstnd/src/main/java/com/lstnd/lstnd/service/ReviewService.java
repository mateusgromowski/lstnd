package com.lstnd.lstnd.service;

import org.springframework.stereotype.Service;

import com.lstnd.lstnd.model.Review;
import com.lstnd.lstnd.repository.ReviewRepository;

@Service
public class ReviewService {
    ReviewRepository repository;

    public ReviewService(ReviewRepository repository) {
        this.repository = repository;
    }

    public Review getReview(String spotifyId) {
        return repository.findBySpotifyId(spotifyId);
    }
}
