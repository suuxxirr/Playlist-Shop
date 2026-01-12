package com.playlist_shop.service;

import com.playlist_shop.domain.Song;
import com.playlist_shop.dto.ItunesResponse;
import com.playlist_shop.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SongService {

    private final SongRepository songRepository;
    private final ItunesService itunesService;

    @Transactional
    public List<Song> findAllSongs() {
        return songRepository.findAll();
    }

    @Transactional
    public Song getSong(Long songId) {
        return songRepository.findById(songId)
                .orElseThrow(() -> new IllegalArgumentException("노래를 찾을 수 없습니다."));
    }

    @Transactional
    public void createSong(String title, String artist, String album, String albumImage) {

        Song song = Song.builder()
                .title(title)
                .artist(artist)
                .album(album)
                .albumartUrl(albumImage)
                .price(1000)
                .build();

        songRepository.save(song);
    }

    // 검색 (Itunes에서 검색 + DB 저장 + 결과 반환)
    @Transactional
    public List<Song> searchSongs(String keyword) {
        List<ItunesResponse.ItunesResult> itunesResults = itunesService.searchSongs(keyword);

        for (ItunesResponse.ItunesResult result : itunesResults) {
            if (result.getTrackName() != null && result.getArtistName() != null) {
                // 중복 체크
                if (!songRepository.existsByTitleAndArtistIgnoreCase(result.getTrackName(), result.getArtistName())) {
                    createSong(
                            result.getTrackName(),
                            result.getArtistName(),
                            result.getCollectionName(),
                            result.getArtworkUrl100()
                    );
                }
            }
        }
        return songRepository.findByTitleContainingOrArtistContainingIgnoreCase(keyword, keyword);
    }
}
