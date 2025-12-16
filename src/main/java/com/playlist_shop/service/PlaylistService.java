package com.playlist_shop.service;

import com.playlist_shop.domain.*;
import com.playlist_shop.repository.CartRepository;
import com.playlist_shop.repository.PlaylistRepository;
import com.playlist_shop.repository.PlaylistSongRepository;
import com.playlist_shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaylistService {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final PlaylistRepository playlistRepository;
    private final PlaylistSongRepository playlistSongRepository;


    // 장바구니에 있는 노래들로 플레이리스트 만들기
    @Transactional
    public Long createPlaylistFromCart(String username, String title, String description) {
        User user = userRepository.findByNickname(username)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다."));

        Cart cart = cartRepository.findByUser(user);

        if (cart == null) { // cartrepository에서 optional로 안해놔서 수동으로 체크
            throw new IllegalArgumentException("장바구니가 없습니다.");
        }

        if (cart.getCartSongs().isEmpty()) {
            throw new IllegalStateException("장바구니에 담긴 곡이 없습니다. 곡을 먼저 담아주세요.");
        }

        Playlist playlist = Playlist.builder()
                .user(user)
                .title(title)
                .description(description)
                .build();

        playlistRepository.save(playlist);

        for (CartSong cartSong : cart.getCartSongs()) {
            PlaylistSong playlistSong = PlaylistSong.builder()
                    .playlist(playlist)
                    .song(cartSong.getSong())
                    .build();

            playlistSongRepository.save(playlistSong);
        }

        return playlist.getId();
    }

    // 전체 플레이리스트 목록 조회
    @Transactional
    public List<Playlist> findAllPlaylists() {
        return playlistRepository.findAll();
    }

    // 특정 플레이리스트 조회
    @Transactional
    public Playlist getPlaylist(Long id) {
        return playlistRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("플레이리스트를 찾을 수 없습니다."));
    }

}
