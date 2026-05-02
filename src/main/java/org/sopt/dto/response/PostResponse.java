package org.sopt.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import org.sopt.domain.BoardType;
import org.sopt.domain.Post;

import java.time.LocalDateTime;

@Schema(description = "게시글 응답")
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