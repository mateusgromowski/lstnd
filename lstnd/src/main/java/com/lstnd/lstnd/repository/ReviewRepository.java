package com.lstnd.lstnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lstnd.lstnd.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Review findBySpotifyId(String spotifyId);
}
