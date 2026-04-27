package org.sopt.exception;

public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException(Long id) {
        super("id : " + id + "존재하지 않는 유저입니다.", "USER_001");
    }
}
