package com.lstnd.lstnd.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.lstnd.lstnd.DTO.NewReviewDTO;
import com.lstnd.lstnd.model.Review;
import com.lstnd.lstnd.repository.ReviewRepository;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {
    @Mock
    ReviewRepository repository;
    @Mock
    SpotifyService spotifyService;
    @InjectMocks
    ReviewService service;
    @Captor
    ArgumentCaptor<Review> captor;

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

    @Test
    void shouldCreateReviewTest() {
        NewReviewDTO dto = NewReviewDTO.builder()
                .userName("peter")
                .review("muito bom")
                .score(5)
                .build();
        service.createReview("abc123", dto);
        verify(repository).save(captor.capture());
        Review review = captor.getValue();
        assertEquals("peter", review.getUserName());
        assertEquals("muito bom", review.getReview());
        assertEquals(5, review.getScore());
        assertEquals("abc123", review.getSpotifyId());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenIdIsNullTest() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> service.createReview(null, null));
        assertEquals("ID inválido.", ex.getMessage());
        verify(repository, never()).save(any());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenUserNameIsNullTest() {
        NewReviewDTO dto = NewReviewDTO.builder()
                .userName(null)
                .review("muito bom")
                .score(5)
                .build();
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> service.createReview("abc123", dto));
        assertEquals("Nome de usuário inválido.", ex.getMessage());
        verify(repository, never()).save(any());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenReviewIsNullTest() {
        NewReviewDTO dto = NewReviewDTO.builder()
                .userName("retep")
                .review(null)
                .score(5)
                .build();
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> service.createReview("abc123", dto));
        assertEquals("Review inválida.", ex.getMessage());
        verify(repository, never()).save(any());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenScoreIsLessThanZeroTest() {
        NewReviewDTO dto = NewReviewDTO.builder()
                .userName("retep")
                .review("muito bom")
                .score(-1)
                .build();
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> service.createReview("abc123", dto));
        assertEquals("Nota inválida.", ex.getMessage());
        verify(repository, never()).save(any());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenScoreIsHigherThanFiveTest() {
        NewReviewDTO dto = NewReviewDTO.builder()
                .userName("retep")
                .review("muito bom")
                .score(6)
                .build();
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> service.createReview("abc123", dto));
        assertEquals("Nota inválida.", ex.getMessage());
        verify(repository, never()).save(any());
    }

}
