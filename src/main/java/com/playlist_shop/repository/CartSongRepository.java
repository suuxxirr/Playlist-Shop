package com.playlist_shop.repository;

import com.playlist_shop.domain.Cart;
import com.playlist_shop.domain.CartSong;
import com.playlist_shop.domain.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface CartSongRepository extends JpaRepository<CartSong, Long> {
    Optional<CartSong> findByCartAndSong(Cart cart, Song song);

    List<CartSong> findByCart(Cart cart);
}
