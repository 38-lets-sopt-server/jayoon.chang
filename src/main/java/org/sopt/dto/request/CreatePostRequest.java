package org.sopt.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.sopt.domain.BoardType;

// 게시글 작성 요청 (클라이언트 → 서버)
@Schema(description = "게시글 생성 요청")
public record CreatePostRequest(

        @Schema(description = "유저 ID", example = "1")
        Long userId,

        @NotBlank(message = "제목은 필수입니다.")
        @Size(max=50, message = "제목은 50자를 초과할 수 없습니다.")
        @Schema(description = "제목", example = "게시글 제목")
        String title,

        @NotBlank(message = "내용은 필수입니다.")
        @Schema(description = "내용", example = "게시글 내용 게시글 내용")
        String content,

        @NotBlank(message = "작성자는 필수입니다.")
        @Schema(description = "작성자", example = "홍길동")
        String author,

        @Schema(description = "게시글 타입", example = "FREE")
        BoardType boardType
) {}