package com.playlist_shop.controller;

import com.playlist_shop.domain.Playlist;
import com.playlist_shop.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PlaylistController {
    private final PlaylistService playlistService;

    @PostMapping("/playlist/create") // 플레이리스트 생성
    public String createPlaylist(@RequestParam String title, @RequestParam String description,
                                 @AuthenticationPrincipal UserDetails userDetails) {
        Long id = playlistService.createPlaylistFromCart(userDetails.getUsername(), title,description);

        return "redirect:/";
    }

    @GetMapping("/playlist") // 플레이리스트 목록 페이지
    public String playlistPage(Model model) {
        List<Playlist> playlists = playlistService.findAllPlaylists();
        model.addAttribute("playlists", playlists);
        return "playlistList";
    }
}
