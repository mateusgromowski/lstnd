package com.lstnd.lstnd.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ImageDTO(@JsonProperty("url") String capeUrl, int height, int width) {

}
