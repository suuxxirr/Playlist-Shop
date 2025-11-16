package com.playlist_shop.controller;

import com.playlist_shop.dto.UserJoinRequestDto;
import com.playlist_shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/join")
    public String joinPage() {
        return "joinForm";
    }

    @PostMapping("/join")
    public String joinProcess(UserJoinRequestDto requestDto){
        userService.join(requestDto);
        return "redirect:/";
    }
}
