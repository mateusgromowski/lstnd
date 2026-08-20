package com.lstnd.lstnd.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.lstnd.lstnd.model.Review;
import com.lstnd.lstnd.repository.ReviewRepository;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {
    @Mock
    ReviewRepository repository;

    @InjectMocks
    ReviewService service;

    @Test
    void shouldReturnAllReviewsTest() {
        List<Review> reviews = List.of(
                Review.builder().id(1L).userName("peter").review("muito bom").score(5).build(),
                Review.builder().id(1L).userName("retep").review("muito ruim").score(0).build());
        when(repository.findAllBySpotifyId("abc123")).thenReturn(reviews);
        List<Review> serviceReviews = service.getAllReviews("abc123");
        assertEquals(2, serviceReviews.size());
        assertEquals(reviews.get(0), serviceReviews.get(0));
        assertEquals(reviews.get(1), serviceReviews.get(1));
        verify(repository).findAllBySpotifyId("abc123");
    }

    @Test
    void shouldReturnEmptyListTest() {
        when(repository.findAllBySpotifyId("abc123")).thenReturn(Collections.emptyList());
        List<Review> reviews = repository.findAllBySpotifyId("abc123");
        assertEquals(Collections.emptyList(), reviews);
        verify(repository).findAllBySpotifyId("abc123");
    }
}
