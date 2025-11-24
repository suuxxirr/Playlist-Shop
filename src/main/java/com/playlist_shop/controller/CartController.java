package com.playlist_shop.controller;

import com.playlist_shop.domain.User;
import com.playlist_shop.repository.UserRepository;
import com.playlist_shop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final UserRepository userRepository;

    @GetMapping("/cart/add/{songId}")
    public String addCart(@PathVariable Long songId,
                       @AuthenticationPrincipal UserDetails userDetails) {

        if (userDetails == null) {
            return "redirect:/login";
        }

        User user = userRepository.findByNickname(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다"));

        cartService.addCart(user, songId);

        return "redirect:/";
    }
}
