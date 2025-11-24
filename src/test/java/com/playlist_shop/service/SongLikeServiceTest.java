package com.playlist_shop.service;

import com.playlist_shop.domain.Song;
import com.playlist_shop.domain.User;
import com.playlist_shop.dto.UserJoinRequestDto;
import com.playlist_shop.repository.SongLikeRepository;
import com.playlist_shop.repository.SongRepository;
import com.playlist_shop.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class SongLikeServiceTest {
    @Autowired
    UserService userService;
    @Autowired
    SongRepository songRepository;
    @Autowired
    SongLikeService songLikeService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SongLikeRepository songLikeRepository;

    @Test
    @DisplayName("종아요 누르기 & 취소하기 성공")
    void like_success() {
        // given
        User user = makeAndSaveUser();
        Song song = makeAndSaveSong();

        // when 1 (좋아요 누르기)
        songLikeService.actionLike(user, song.getId());
        // then 1
        assertThat(songLikeRepository.findByUserAndSong(user, song)).isPresent();

        // when 2 (좋아요 다시 누르기 = 취소하기)
        songLikeService.actionLike(user, song.getId());
        // then 2
        assertThat(songLikeRepository.findByUserAndSong(user, song)).isEmpty();


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