package com.playlist_shop.repository;

import com.playlist_shop.domain.CartSong;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartSongRepository extends JpaRepository<CartSong, Long> {
}
