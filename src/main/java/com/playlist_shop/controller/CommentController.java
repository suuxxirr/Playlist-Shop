package com.playlist_shop.controller;

import com.playlist_shop.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/song/comment")
    public String writeComment(@RequestParam Long songId, @RequestParam String content, @RequestParam Integer rating,
                               @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return "redirect:/login";
        }

        commentService.writeComment(songId, userDetails.getUsername(), content, rating);

        return "redirect:/song/" + songId;
    }

    @GetMapping("/song/comment/delete/{commentId}")
    public String deleteComment(@PathVariable Long commentId, @RequestParam Long songId,
                                @AuthenticationPrincipal UserDetails userDetails) {
        commentService.deleteComment(commentId, userDetails.getUsername());

        return "redirect:/song/" + songId;
    }
}
