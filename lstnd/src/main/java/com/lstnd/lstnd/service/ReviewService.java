package com.lstnd.lstnd.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lstnd.lstnd.DTO.NewReviewDTO;
import com.lstnd.lstnd.exception.EmptyStringException;
import com.lstnd.lstnd.model.Review;
import com.lstnd.lstnd.repository.ReviewRepository;

@Service
public class ReviewService {
    ReviewRepository repository;

    public ReviewService(ReviewRepository repository) {
        this.repository = repository;
    }

    public Integer getAverageBySpotifyId(String spotifyId) throws EmptyStringException {
        validateId(spotifyId);
        return repository.getAverageScoreBySpotifyId(spotifyId);
    }

    public List<Review> getAllReviews(String spotifyId) throws EmptyStringException {
        validateId(spotifyId);
        return repository.findAllBySpotifyId(spotifyId);
    }

    public void createReview(String spotifyId, NewReviewDTO dto) throws EmptyStringException {
        validateId(spotifyId);
        Review newReview = Review.builder()
                .spotifyId(spotifyId)
                .userName(dto.userName())
                .review(dto.review())
                .score(dto.score())
                .build();
        repository.save(newReview);
    }

    private void validateId(String id) throws EmptyStringException {
        if (id == null || id.isBlank()) {
            throw new EmptyStringException("ID inválido.");
        }
        return;
    }

}
