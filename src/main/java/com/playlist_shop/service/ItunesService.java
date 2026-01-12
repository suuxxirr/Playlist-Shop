package com.playlist_shop.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.playlist_shop.dto.ItunesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItunesService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<ItunesResponse.ItunesResult> searchSongs(String keyword) {
        try {
            String url = "https://itunes.apple.com/search?term=" + keyword + "&entity=song&limit=50";

            String responseString = restTemplate.getForObject(url, String.class);
            ItunesResponse response = objectMapper.readValue(responseString, ItunesResponse.class);

            if (response != null && response.getResults() != null) {
                // 앨범아트 픽셀 변경
                for (ItunesResponse.ItunesResult result : response.getResults()) {
                    if (result.getArtworkUrl100() != null) {
                        String highResUrl = result.getArtworkUrl100().replace("100x100bb", "600x600bb");
                        result.setArtworkUrl100(highResUrl);
                    }
                }
                return response.getResults();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }
}
