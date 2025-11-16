package com.playlist_shop.dto;



import com.playlist_shop.domain.User;
import lombok.Getter;
import lombok.Setter;

/**
 * 회원가입 요청 DTO
 */
@Getter
@Setter
public class UserJoinRequestDto {
    private String nickname;
    private String password;
    private String mail;

}
