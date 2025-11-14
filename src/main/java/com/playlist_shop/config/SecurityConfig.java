package com.playlist_shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // 이 파일이 설정 파일임을 알려줍니다.
@EnableWebSecurity // 스프링 시큐리티를 활성화합니다.
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/**").permitAll() // "/**"는 모든 요청을 의미, .permitAll()은 모두 허용
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin.disable()) // 기본 폼 로그인 비활성화
                .csrf(csrf -> csrf.disable()) // CSRF 보호 비활성화 (개발 편의를 위해)
                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin) // H2 콘솔사용
                );

        return http.build();
    }
}
