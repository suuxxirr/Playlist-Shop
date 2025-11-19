package com.playlist_shop.dto;



import com.playlist_shop.domain.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * 회원가입 요청 DTO
 */
@Getter
@Setter
public class UserJoinRequestDto {
    @NotBlank
    @Size(min = 2, max =10, message = "닉네임은 2자 이상 10자 이하로 입력해주세요.")
    private String nickname;

    @NotBlank
    @Size(min = 8, message = "비밀번호는 8자 이상으로 입력해주세요.")
    private String password;

    @NotBlank
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private String mail;

}
