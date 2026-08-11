package com.lstnd.lstnd.model;

import lombok.Builder;

@Builder
public record Album(String id, String capeUrl, String author, String title, String releaseDate, Integer score) {

}
