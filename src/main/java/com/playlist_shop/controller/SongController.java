package com.playlist_shop.controller;

import com.playlist_shop.domain.Comment;
import com.playlist_shop.domain.Song;
import com.playlist_shop.service.CommentService;
import com.playlist_shop.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class SongController{
    private final SongService songService;
    private final CommentService commentService;

    @GetMapping("/song/{id}") // 노래 상세 페이지
    public String songDetailPage(@PathVariable Long id, Model model) {
        Song song = songService.getSong(id);
        List<Comment> comments = commentService.findAll(id);
        model.addAttribute("song", song);
        model.addAttribute("comments", comments);
        return "songDetail";
    }
}
