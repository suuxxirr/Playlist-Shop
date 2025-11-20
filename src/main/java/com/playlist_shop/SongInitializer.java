package com.playlist_shop;

import com.playlist_shop.domain.Song;
import com.playlist_shop.repository.SongRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SongInitializer {

    private final SongRepository songRepository;


    // 샘플 데이터 삽입
    @PostConstruct
    public void initSongData() {
        if (songRepository.count() > 0) {
            return;
        }

        List<Song> songs = new ArrayList<>();


        songs.add(Song.builder()
                .title("Hype Boy")
                .artist("NewJeans")
                .price(4900)
                .album("New Jeans 1st EP")
                .releaseDate(LocalDate.of(2022, 8, 1))
                .albumartUrl("https://cdn-images.dzcdn.net/images/cover/a87d8380756567e6bc1f65d43ac2b46d/500x500-000000-80-0-0.jpg")
                .build());

        songs.add(Song.builder()
                .title("Seven (feat. Latto)")
                .artist("Jung Kook")
                .price(6000)
                .album("Seven")
                .releaseDate(LocalDate.of(2023, 7, 14))
                .albumartUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/d/d9/Seven_by_Jungkook_cover_art.jpeg/960px-Seven_by_Jungkook_cover_art.jpeg")
                .build());

        songs.add(Song.builder()
                .title("Love Lee")
                .artist("AKMU")
                .price(5900)
                .album("Love Lee")
                .releaseDate(LocalDate.of(2023, 8, 21))
                .albumartUrl("https://cdn-images.dzcdn.net/images/cover/275e04117f2327489cae2b7df014e112/500x500-000000-80-0-0.jpg")
                .build());

        songs.add(Song.builder()
                .title("luther")
                .artist("Kendrick Lamar")
                .price(19900)
                .album("GNX")
                .releaseDate(LocalDate.of(2024, 11, 22))
                .albumartUrl("https://cdn-images.dzcdn.net/images/cover/82db4c0f8e9412cafb1cd765b076d58c/1900x1900-000000-80-0-0.jpg")
                .build());

        songRepository.saveAll(songs);


    }

}
