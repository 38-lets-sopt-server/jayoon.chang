package org.sopt.exception;

public class PostNotFoundException extends RuntimeException{
    public PostNotFoundException(Long id){
        super("id : " + id + " 존재하지 않는 게시글입니다.");
    }
}
