package org.sopt.exception;

public class PostNotFoundException extends NotFoundException {

    public PostNotFoundException(Long id) {
        super("id : " + id + " 존재하지 않는 게시글입니다.", "POST_001");
    }

}
