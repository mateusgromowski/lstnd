package com.lstnd.lstnd.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthDTO(
        @JsonProperty("access_token") String accessToken,

        @JsonProperty("token_type") String tokenType,

        @JsonProperty("expires_in") int expiresIn) {
}
