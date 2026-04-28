package org.sopt.exception;

public class PostNotFoundException extends NotFoundException {

    public PostNotFoundException(Long id) {
        super(
                ErrorCode.POST_NOT_FOUND,
                "id = " + id + " " + ErrorCode.POST_NOT_FOUND.getMessage()
        );
    }
}
