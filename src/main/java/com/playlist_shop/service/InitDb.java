package com.playlist_shop.service;

import com.playlist_shop.dto.ItunesResponse;
import com.playlist_shop.repository.SongRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final ItunesService itunesService;
    private final SongService songService;
    private final SongRepository songRepository;

    @PostConstruct
    public void init() {
        if (songRepository.count() > 0) {
            System.out.println("이미 데이터가 존재합니다. 초기화를 건너뜁니다.");
            return;
        }

        String[] keywords = {"pop", "kpop"};

        for (String keyword: keywords) {
            List<ItunesResponse.ItunesResult> songs = itunesService.searchSongs(keyword);

            for (ItunesResponse.ItunesResult s : songs) {
                // 제목이나 가수가 없는 데이터는 제외
                if (s.getTrackName() != null && s.getArtistName() != null) {
                    songService.createSong(
                            s.getTrackName(),
                            s.getArtistName(),
                            s.getCollectionName(),
                            s.getArtworkUrl100()
                    );
                }
            }
        }
    }

}
