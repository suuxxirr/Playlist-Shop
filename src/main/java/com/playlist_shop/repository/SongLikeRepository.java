package com.playlist_shop.repository;

import com.playlist_shop.domain.Song;
import com.playlist_shop.domain.SongLike;
import com.playlist_shop.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SongLikeRepository extends JpaRepository<SongLike, Long> {
    Optional<SongLike> findByUserAndSong(User user, Song song);

}
