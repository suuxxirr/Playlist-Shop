package com.playlist_shop.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "songs")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String artist;
    private Integer price;
    private LocalDate releaseDate;
    private String album;
    private String albumartUrl;

    @OneToMany(mappedBy = "song", cascade = CascadeType.ALL)
    private List<SongLike> likes = new ArrayList<>();

    public int getLikeCount() {
        return likes.size();
    }

    @Builder
    public Song(String title, String artist, Integer price, LocalDate releaseDate, String album, String albumartUrl) {
        this.title = title;
        this.artist = artist;
        this.price = price;
        this.releaseDate = releaseDate;
        this.album = album;
        this.albumartUrl = albumartUrl;
    }


}
