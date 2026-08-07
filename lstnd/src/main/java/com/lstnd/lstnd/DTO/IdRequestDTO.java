package com.lstnd.lstnd.DTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record IdRequestDTO(
        String id,
        List<ImageDTO> images,
        String name,
        @JsonProperty("release_date") String releaseDate,
        List<ArtistsDTO> artists) {

}