package com.playlist_shop.service;

import com.playlist_shop.domain.Comment;
import com.playlist_shop.domain.Song;
import com.playlist_shop.domain.User;
import com.playlist_shop.repository.CommentRepository;
import com.playlist_shop.repository.SongRepository;
import com.playlist_shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final SongRepository songRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    // 댓글 작성
    @Transactional
    public void writeComment(Long songId, String nickname, String content, Integer rating) {
        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new IllegalArgumentException("해당 노래를 찾을 수 없습니다."));

        User user = userRepository.findByNickname(nickname)
                .orElseThrow(() -> new IllegalArgumentException("해다 유저를 찾을 수 없습니다."));

        Comment comment = Comment.builder()
                .content(content)
                .user(user)
                .song(song)
                .rating(rating)
                .build();

        commentRepository.save(comment);

    }


    // 댓글 목록 조회
    @Transactional
    public List<Comment> findAll(Long songId) {
        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new IllegalArgumentException("해당 노래를 찾을 수 없습니다."));

        return commentRepository.findBySong(song);
    }

    // 댓글 삭제
    @Transactional
    public void deleteComment(Long commentId, String username) {

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글을 찾을 수 없습니다."));

        if (!comment.getUser().getUsername().equals(username)) {
            throw new IllegalArgumentException("작성자만 삭제할 수 있습니다.");
        }

        commentRepository.delete(comment);
    }

}
