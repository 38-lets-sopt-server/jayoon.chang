package org.sopt.exception;

public abstract class NotFoundException extends RuntimeException {

    private final ErrorCode errorCode;

    protected NotFoundException(ErrorCode errorCode, String message){
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
