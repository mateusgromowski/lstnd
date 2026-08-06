package com.lstnd.lstnd.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public record NewReviewDTO(
        @JsonProperty("user_name") String userName,
        String review,
        int score) {
}
