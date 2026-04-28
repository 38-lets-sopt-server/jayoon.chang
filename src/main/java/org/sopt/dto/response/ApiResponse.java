package org.sopt.dto.response;

public record ApiResponse<T>(boolean success, String message, T data, String errorCode) {

    // 성공
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, "요청이 성공했습니다.", data, null);
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(true, message, data, null);
    }

    // 실패
    public static <T> ApiResponse<T> failure(String code, String message){
        return new ApiResponse<>(false, message, null, code);
    }
}
