package org.sopt.dto.response;

// 게시글 작성 응답 (서버 → 클라이언트)
public class CreatePostResponse {
    private final Long id;
    private final String message;

    public Long getId() { return id; }
    public String getMessage() { return message; }

    public CreatePostResponse(Long id, String message) {
        this.id = id;
        this.message = message;
    }
}
