package org.sopt.exception;

public class PostNotFoundException extends RuntimeException {

    private final ErrorCode errorCode;

    public PostNotFoundException(Long id) {
        super("id = " + id + ErrorCode.POST_NOT_FOUND.getMessage());
        this.errorCode = ErrorCode.POST_NOT_FOUND;
    }

    public ErrorCode getErrorCode(){
        return errorCode;
    }
}
