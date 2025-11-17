package com.playlist_shop.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String mail;

    @Builder
    public User(String nickname, String password, String mail) {
        this.nickname = nickname;
        this.password = password;
        this.mail = mail;
    }

    @Override // 계정이 만료되지 않았는가 반환(true: 만료 안됨)
    public boolean isAccountNonExpired() {
        return true; // 일단 하드 코딩
//        return UserDetails.super.isAccountNonExpired();
    }

    @Override // 계정이 잠기지 않았는가 반환
    public boolean isAccountNonLocked() {
        return true;
//        return UserDetails.super.isAccountNonLocked();
    }

    @Override // 비밀번호가 만료되지 않았는가 반환
    public boolean isCredentialsNonExpired() {
        return true;
//        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override //계정이 활성화 되어있는가
    public boolean isEnabled() {
        return true;
//        return UserDetails.super.isEnabled();
    }

    @Override // 사용자 권한 반환
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override // 사용자 로그인id(nickname) 반환
    public String getUsername() {
        return nickname;
    }

    @Override // 사용자 비밀번호 반환
    public String getPassword() {
        return password;
    }
}
