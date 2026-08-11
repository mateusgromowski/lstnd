package com.lstnd.lstnd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lstnd.lstnd.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Review findBySpotifyId(String spotifyId);

    List<Review> findAllBySpotifyId(String spotifyId);

    @Query("SELECT COALESCE(AVG(r.score)) FROM Review r WHERE r.spotifyId = :spotifyId")
    Integer getAverageScoreBySpotifyId(@Param("spotifyId") String spotifyId);
}
