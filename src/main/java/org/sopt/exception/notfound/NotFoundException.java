package org.sopt.exception.notfound;

import org.sopt.exception.ErrorCode;
import org.sopt.exception.common.BusinessException;

public abstract class NotFoundException extends BusinessException {

    protected NotFoundException(ErrorCode errorCode){
        super(errorCode);
    }
}
