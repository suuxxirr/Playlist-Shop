package com.playlist_shop.controller;

import com.playlist_shop.domain.Song;
import com.playlist_shop.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final SongService songService;

    @GetMapping("/")
    public String mainPage(@RequestParam(required = false) String keyword,  Model model) {
        List<Song> songs;

        if (keyword != null && !keyword.isEmpty()) {
            songs = songService.searchSongs(keyword);
        } else {
            songs = songService.findAllSongs();
        }
        model.addAttribute("songs", songs);
        model.addAttribute("keyword", keyword);

        return "index";
    }
}
