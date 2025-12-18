package com.playlist_shop.service;

import com.playlist_shop.domain.*;
import com.playlist_shop.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final SongRepository songRepository;
    private final CartRepository cartRepository;
    private final CartSongRepository cartSongRepository;
    private final PlaylistRepository playlistRepository;

    @Transactional
    public void addCart(User user, Long songId) {
        Cart cart = cartRepository.findByUser(user);

        Song song = songRepository.findById(songId)
                        .orElseThrow(() -> new IllegalArgumentException("해당 노래를 찾을 수 없습니다."));

        if (cartSongRepository.findByCartAndSong(cart, song).isPresent()) { // 이미 장바구니에 담겨있는 경우
            System.out.println("이미 담겨있는 노래입니다.");
            return;
        }

        // 담겨있지 않으면 장바구니에 추가
        CartSong cartSong = CartSong.builder()
                    .cart(cart)
                    .song(song)
                    .build();

        cartSongRepository.save(cartSong);

    }

    @Transactional
    public List<CartSong> userCartView(User user) {
        Cart cart = cartRepository.findByUser(user);

        return cartSongRepository.findByCart(cart);
    }

    @Transactional
    public void deleteCartSong(Long cartSongId) {
        cartSongRepository.deleteById(cartSongId);
    }

    // 플레이리스트에 담긴 곡 장바구니로 가져오기
    @Transactional
    public void addPlaylistToCart(User user, Long playlistId) {
        Cart cart = cartRepository.findByUser(user);
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new IllegalArgumentException("플레이리스트가 없습니다."));

        for (PlaylistSong ps : playlist.getPlaylistSongs()) {
            Song song = ps.getSong();
            CartSong cartSong = CartSong.builder()
                    .cart(cart)
                    .song(song)
                    .build();
            cartSongRepository.save(cartSong);
        }

    }

}
