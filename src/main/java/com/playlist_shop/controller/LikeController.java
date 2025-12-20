package com.playlist_shop.controller;

import com.playlist_shop.domain.User;
import com.playlist_shop.repository.SongLikeRepository;
import com.playlist_shop.repository.UserRepository;
import com.playlist_shop.service.SongLikeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class LikeController {

    private final SongLikeService songLikeService;
    private final UserRepository userRepository;

    @GetMapping("/like/{songId}")
    public String like(@PathVariable Long songId,
                       @AuthenticationPrincipal UserDetails userDetails,
                       HttpServletRequest request) {

        if (userDetails == null) {
            return "redirect:/login";
        }

        User user = userRepository.findByNickname(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다"));

        songLikeService.actionLike(user, songId);

        String previousPage = request.getHeader("Referer");
        if (previousPage != null & !previousPage.isBlank()) {
            return "redirect:" + previousPage;
        } else {
            return "redirect:/";
        }
    }
}
