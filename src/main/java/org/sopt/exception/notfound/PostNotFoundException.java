package org.sopt.exception.notfound;

import org.sopt.exception.ErrorCode;

public class PostNotFoundException extends NotFoundException {

    public PostNotFoundException() {
        super(ErrorCode.POST_NOT_FOUND);
    }
}
