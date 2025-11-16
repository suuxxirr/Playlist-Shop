package com.playlist_shop.service;

import com.playlist_shop.domain.User;
import com.playlist_shop.dto.UserJoinRequestDto;
import com.playlist_shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long join(UserJoinRequestDto requestDto) {
        User user = User.builder()
                .nickname(requestDto.getNickname())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .mail(requestDto.getMail())
                .build();

        User savedUser = userRepository.save(user);
        return savedUser.getId();
    }
}
