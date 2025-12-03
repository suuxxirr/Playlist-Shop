package com.playlist_shop.service;

import com.playlist_shop.domain.Song;
import com.playlist_shop.domain.SongLike;
import com.playlist_shop.domain.User;
import com.playlist_shop.repository.SongLikeRepository;
import com.playlist_shop.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SongLikeService {

    private final SongLikeRepository songLikeRepository;
    private final SongRepository songRepository;

    @Transactional
    public void actionLike(User user, Long songId) {

        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new IllegalArgumentException("해당 노래를 찾을 수 없습니다."));

        Optional<SongLike> songLikeOptional = songLikeRepository.findByUserAndSong(user, song);

        if (songLikeOptional.isPresent()) {
            songLikeRepository.delete(songLikeOptional.get());
        } else {
            SongLike newSongLike = SongLike.builder()
                    .song(song)
                    .user(user)
                    .build();

            songLikeRepository.save(newSongLike);
        }

    }
}
