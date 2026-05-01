package org.sopt.exception;

import org.sopt.exception.common.BusinessException;

public class DuplicateLikeException extends BusinessException {

    public DuplicateLikeException() {
        super(ErrorCode.DUPLICATE_LIKE);
    }
}
