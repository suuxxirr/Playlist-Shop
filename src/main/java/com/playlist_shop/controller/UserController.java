package com.playlist_shop.controller;

import com.playlist_shop.dto.UserJoinRequestDto;
import com.playlist_shop.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/join")
    public String joinPage(Model model) {

        model.addAttribute("userJoinRequestDto", new UserJoinRequestDto());
        return "joinForm";
    }

    @PostMapping("/join")
    public String joinProcess(@Valid @ModelAttribute UserJoinRequestDto userJoinRequestDto, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return "joinForm";
        }
        userService.join(userJoinRequestDto);
        return "redirect:/login";
    }
}
