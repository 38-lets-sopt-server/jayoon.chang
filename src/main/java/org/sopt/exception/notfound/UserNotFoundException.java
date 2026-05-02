package org.sopt.exception.notfound;

import org.sopt.exception.ErrorCode;

public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
