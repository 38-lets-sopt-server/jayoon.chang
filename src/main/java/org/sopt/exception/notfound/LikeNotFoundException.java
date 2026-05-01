package org.sopt.exception.notfound;

import org.sopt.exception.ErrorCode;

public class LikeNotFoundException extends NotFoundException{
    public LikeNotFoundException() {
        super(ErrorCode.LIKE_NOT_FOUND);
    }
}
