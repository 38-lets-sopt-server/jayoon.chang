package org.sopt.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "유저 생성 요청")
public record CreateUserRequest (

    @Schema(example = "홍길동")
    String nickname,

    @Schema(example = "gildong@test.com")
    String email,

    @Schema(example = "비밀번호")
    String password
) {}
