package com.playlist_shop.service;

import com.playlist_shop.domain.Song;
import com.playlist_shop.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SongService {

    private final SongRepository songRepository;

    @Transactional
    public List<Song> findAllSongs() {
        return songRepository.findAll();
    }
}
