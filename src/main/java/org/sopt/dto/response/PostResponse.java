package org.sopt.dto.response;

import org.sopt.domain.Post;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record PostResponse(
        Long id,
        String title,
        String content,
        String createdAt
) {

    public static PostResponse from(Post post) {

        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
        );
    }
}