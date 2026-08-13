package com.lstnd.lstnd.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lstnd.lstnd.DTO.NewReviewDTO;
import com.lstnd.lstnd.model.Review;
import com.lstnd.lstnd.repository.ReviewRepository;

@Service
public class ReviewService {
    ReviewRepository repository;

    public ReviewService(ReviewRepository repository) {
        this.repository = repository;
    }

    public Integer getAverageBySpotifyId(String spotifyId) throws IllegalArgumentException {
        validateId(spotifyId);
        return repository.getAverageScoreBySpotifyId(spotifyId);
    }

    public List<Review> getAllReviews(String spotifyId) throws IllegalArgumentException {
        validateId(spotifyId);
        return repository.findAllBySpotifyId(spotifyId);
    }

    public void createReview(String spotifyId, NewReviewDTO dto) throws IllegalArgumentException {
        validateId(spotifyId);
        validateUserName(dto.userName());
        validateReview(dto.review());
        validateScore(dto.score());
        Review newReview = Review.builder()
                .spotifyId(spotifyId)
                .userName(dto.userName())
                .review(dto.review())
                .score(dto.score())
                .build();
        repository.save(newReview);
    }

    private void validateScore(Integer score) throws IllegalArgumentException {
        if (score == null) {
            throw new IllegalArgumentException("Nota inválida.");
        }
        return;
    }

    private void validateReview(String review) throws IllegalArgumentException {
        if (review == null || review.isBlank()) {
            throw new IllegalArgumentException("Review inválida.");
        }
        return;
    }

    private void validateUserName(String userName) throws IllegalArgumentException {
        if (userName == null || userName.isBlank()) {
            throw new IllegalArgumentException("Nome de usuário inválido.");
        }
        return;
    }

    private void validateId(String id) throws IllegalArgumentException {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID inválido.");
        }
        return;
    }

}
