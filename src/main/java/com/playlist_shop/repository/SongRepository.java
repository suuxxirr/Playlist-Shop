package com.playlist_shop.repository;

import com.playlist_shop.domain.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {

    List<Song> findByTitleContainingOrArtistContainingIgnoreCase(String title, String artist);

    boolean existsByTitleAndArtistIgnoreCase(String title, String artist);
}
