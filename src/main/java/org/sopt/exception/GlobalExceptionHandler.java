package org.sopt.exception;

import org.sopt.dto.response.ApiResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(PostNotFoundException.class)
    public ApiResponse<Void> handlePostNotFound(PostNotFoundException e){
        return new ApiResponse<>(
                false,
                e.getErrorCode() + ": " + e.getMessage(),
                null
        );
    }
}
