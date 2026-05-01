package org.sopt.dto.response;

import org.sopt.domain.BoardType;
import org.sopt.domain.Post;

import java.time.LocalDateTime;

public record PostResponse(
        Long id,
        String title,
        String content,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        BoardType boardType
) {

    public static PostResponse from(Post post) {

        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getCreatedAt(),
                post.getUpdatedAt(),
                post.getBoardType()
        );
    }
}