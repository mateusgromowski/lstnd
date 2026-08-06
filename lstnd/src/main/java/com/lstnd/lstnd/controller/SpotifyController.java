package com.lstnd.lstnd.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lstnd.lstnd.exception.EmptyNameException;
import com.lstnd.lstnd.model.Album;
import com.lstnd.lstnd.model.Review;
import com.lstnd.lstnd.service.ReviewService;
import com.lstnd.lstnd.service.SpotifyService;

@RestController
@RequestMapping("/albuns")
public class SpotifyController {
    SpotifyService spotifyService;
    ReviewService reviewService;

    public SpotifyController(SpotifyService spotifyService, ReviewService reviewService) {
        this.spotifyService = spotifyService;
        this.reviewService = reviewService;
    }

    @GetMapping("/list")
    public List<Album> findAlbumByName(@RequestParam String name) throws EmptyNameException {
        return spotifyService.findAlbumByName(name);
    }

    @PostMapping("/list")
    public Review getReview(@RequestParam String spotifyId) {
        return reviewService.getReview(spotifyId);
    }
}
