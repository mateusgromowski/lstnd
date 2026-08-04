package com.lstnd.lstnd.DTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ItemDTO(String id, List<ImageDTO> images, @JsonProperty("name") String title, List<ArtistsDTO> artists,
        @JsonProperty("release_date") String releaseDate) {

}
