package org.sopt.exception;

import org.sopt.dto.response.ApiResponse;
import org.sopt.exception.common.BusinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<Void>> handleBusinessException(BusinessException e){

        ErrorCode errorCode = e.getErrorCode();

        return ResponseEntity.status(errorCode.getStatus()).body(
                ApiResponse.failure(errorCode.getCode(), errorCode.getMessage())
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Void>> handleIllegalArgumentException(IllegalArgumentException e){

        return ResponseEntity.status(ErrorCode.INVALID_INPUT.getStatus()).body(
                ApiResponse.failure(ErrorCode.INVALID_INPUT.getCode(), ErrorCode.INVALID_INPUT.getMessage())
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationException(MethodArgumentNotValidException e){
        String message = e.getBindingResult().getFieldErrors().stream()
                .map(fieldError ->
                        fieldError.getDefaultMessage() != null
                                ? fieldError.getDefaultMessage()
                                : "유효성 검증에 실패했습니다.")
                .findFirst().orElse("유효성 검증에 실패했습니다.");

        return ResponseEntity.status(ErrorCode.INVALID_INPUT.getStatus()).body(
                ApiResponse.failure(ErrorCode.INVALID_INPUT.getCode(), message)
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(Exception e){

        return ResponseEntity.status(ErrorCode.INTERNAL_SERVER_ERROR.getStatus()).body(
                ApiResponse.failure(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), ErrorCode.INTERNAL_SERVER_ERROR.getMessage())
        );
    }
}
