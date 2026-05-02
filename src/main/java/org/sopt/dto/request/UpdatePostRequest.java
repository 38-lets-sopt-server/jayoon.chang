package org.sopt.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "게시글 수정 요청")
public record UpdatePostRequest(
        
        @NotBlank(message = "제목은 필수입니다.")
        @Size(max = 50, message = "제목은 50자를 초과할 수 없습니다.")
        @Schema(description = "제목", example = "수정된 제목")
        String title,

        @NotBlank(message = "내용은 필수입니다.")
        @Schema(description = "내용", example = "수정된 내용")
        String content
) { }