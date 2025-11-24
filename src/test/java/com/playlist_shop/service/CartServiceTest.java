package com.playlist_shop.service;

import com.playlist_shop.domain.Cart;
import com.playlist_shop.domain.Song;
import com.playlist_shop.domain.User;
import com.playlist_shop.dto.UserJoinRequestDto;
import com.playlist_shop.repository.CartRepository;
import com.playlist_shop.repository.CartSongRepository;
import com.playlist_shop.repository.SongRepository;
import com.playlist_shop.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CartServiceTest {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CartService cartService;
    @Autowired
    SongRepository songRepository;
    @Autowired
    CartSongRepository cartSongRepository;
    @Autowired
    CartRepository cartRepository;

    @Test
    @DisplayName("장바구니에 노래 담기 성공")
    void addCart_success() {
        // given
        User user = makeAndSaveUser();
        Song song = makeAndSaveSong();
        Cart cart = cartRepository.findByUser(user);

        // when
        cartService.addCart(user, song.getId());
        cartService.addCart(user, song.getId()); // 출력 확인

        // then
        Assertions.assertThat(cartSongRepository.findByCartAndSong(cart, song)).isPresent();

    }

    private User makeAndSaveUser() {
        UserJoinRequestDto requestDto = new UserJoinRequestDto();
        requestDto.setNickname("testUser");
        requestDto.setPassword("test123!");
        requestDto.setMail("test@example.com");

        Long savedUserId = userService.join(requestDto);
        return userRepository.findById(savedUserId).orElseThrow();

    }

    private Song makeAndSaveSong() {
        Song song = Song.builder()
                .title("Test Song")
                .artist("Test Artist")
                .price(4900)
                .album("Test EP")
                .releaseDate(LocalDate.of(2022, 8, 1))
                .albumartUrl("Test.jpg")
                .build();
        songRepository.save(song);
        return song;
    }

}