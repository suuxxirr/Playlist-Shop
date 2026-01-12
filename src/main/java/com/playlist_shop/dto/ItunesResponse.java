package com.playlist_shop.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItunesResponse {
    private int resultCount;
    private List<ItunesResult> results;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ItunesResult {
        private String trackName;
        private String artistName;
        private String collectionName;
        private String artworkUrl100;
        private String previewUrl;  // 30초 미리듣기 URL

    }
}
