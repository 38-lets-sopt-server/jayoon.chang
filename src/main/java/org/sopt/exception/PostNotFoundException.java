package org.sopt.exception;

public class PostNotFoundException extends RuntimeException {
    private final String errorCode;

    public PostNotFoundException(Long id) {
        super("id : " + id + " 존재하지 않는 게시글입니다.");
        this.errorCode = "POST_001";
    }

    public String getErrorCode(){
        return errorCode;
    }
}
