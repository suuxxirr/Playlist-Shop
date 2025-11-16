package com.playlist_shop.service;

import com.playlist_shop.domain.User;
import com.playlist_shop.dto.UserJoinRequestDto;
import com.playlist_shop.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("회원가입 성공적으로 수행")
    void join_success() {
        //Given
        UserJoinRequestDto requestDto = new UserJoinRequestDto();
        requestDto.setNickname("testUser");
        requestDto.setPassword("test123!");
        requestDto.setMail("test@example.com");

        //When
        Long savedUserId = userService.join(requestDto);

        //Then
        User savedUser = userRepository.findById(savedUserId).orElseThrow();
        assertThat(savedUser.getNickname()).isEqualTo("testUser");
        assertThat(savedUser.getPassword()).isNotEqualTo("test123!");
        assertThat(passwordEncoder.matches("test123!", savedUser.getPassword())).isTrue();
    }

}