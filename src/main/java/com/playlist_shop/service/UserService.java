package com.playlist_shop.service;

import com.playlist_shop.domain.Cart;
import com.playlist_shop.domain.User;
import com.playlist_shop.dto.UserJoinRequestDto;
import com.playlist_shop.repository.CartRepository;
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
    private final CartRepository cartRepository;

    @Transactional
    public Long join(UserJoinRequestDto requestDto) {
        User user = User.builder()
                .nickname(requestDto.getNickname())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .mail(requestDto.getMail())
                .build();



        User savedUser = userRepository.save(user);

        // 카트 생성해서 할당
        Cart cart = Cart.builder()
                .user(savedUser)
                .build();

        cartRepository.save(cart);

        return savedUser.getId();
    }

}
