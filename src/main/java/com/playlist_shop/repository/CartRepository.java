package com.playlist_shop.repository;

import com.playlist_shop.domain.Cart;
import com.playlist_shop.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUser(User user);
}
