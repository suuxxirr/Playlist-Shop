package com.playlist_shop.controller;

import com.playlist_shop.domain.Cart;
import com.playlist_shop.domain.CartSong;
import com.playlist_shop.domain.User;
import com.playlist_shop.repository.UserRepository;
import com.playlist_shop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

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

    @GetMapping("/cart")
    public String cartPage(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return "redirect:/login";
        }

        User user = userRepository.findByNickname(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다"));

        List<CartSong> cartSongs = cartService.userCartView(user);
        model.addAttribute("cartList", cartSongs);
        return "cartList";
    }

    @GetMapping("/cart/delete/{cartSongId}") // 장바구니에서 노래 삭제
    public String deleteSong(@PathVariable Long cartSongId) {
        cartService.deleteCartSong(cartSongId);
        return "redirect:/cart";
    }
}
