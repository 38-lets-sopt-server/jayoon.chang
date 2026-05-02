package org.sopt.dto.response;

public record BaseResponse<T>(boolean success, String message, T data, String errorCode) {

    // 성공
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(true, "요청이 성공했습니다.", data, null);
    }

    public static <T> BaseResponse<T> success(String message, T data) {
        return new BaseResponse<>(true, message, data, null);
    }

    // 실패
    public static <T> BaseResponse<T> failure(String code, String message){
        return new BaseResponse<>(false, message, null, code);
    }
}
