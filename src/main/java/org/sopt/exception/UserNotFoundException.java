package org.sopt.exception;

public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException(Long id) {
        super(ErrorCode.USER_NOT_FOUND, "id = " + id + " " + ErrorCode.USER_NOT_FOUND.getMessage());
    }
}
