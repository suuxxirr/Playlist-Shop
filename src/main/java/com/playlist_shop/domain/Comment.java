package com.playlist_shop.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
    @Id
    @Column(updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    private LocalDateTime createdAt;
    private Integer rating;
    private Integer likes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "song_id")
    private Song song;

    @Builder
    public Comment(String content, User user, Song song, Integer rating) {
        this.content = content;
        this.user = user;
        this.song = song;
        this.rating = rating;
        this.createdAt =LocalDateTime.now();
        this.likes = 0;
    }
}
