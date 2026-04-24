package org.sopt.controller;

import org.sopt.dto.request.CreatePostRequest;
import org.sopt.dto.response.ApiResponse;
import org.sopt.dto.response.CreatePostResponse;
import org.sopt.dto.response.PostResponse;
import org.sopt.exception.PostNotFoundException;
import org.sopt.service.PostService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // POST /posts
    @PostMapping
    public ApiResponse<CreatePostResponse> createPost(
            @RequestBody CreatePostRequest request
    ) {
        try {
            CreatePostResponse response = postService.createPost(request);
            return new ApiResponse<>(true, "게시글 생성 성공!", response);
        } catch (IllegalArgumentException e) {
            return new ApiResponse<>(false, "게시글 생성 실패 : " + e.getMessage(), null);
        }
    }

    // GET /posts
    public ApiResponse<List<PostResponse>> getAllPosts() {
        List<PostResponse> response = postService.getAllPosts();
        return new ApiResponse<>(true, "게시글 전체 조회 성공!", response);
    }

    // GET /posts/{id}
    public ApiResponse<PostResponse> getPost(Long id) {
        try{
            PostResponse response = postService.getPost(id);
            return new ApiResponse<>(true, "게시글 조회 성공!", response);
        } catch (PostNotFoundException e){
            return new ApiResponse<>(false, "게시글 조회 실패 : " + e.getMessage(), null);
        }
    }

    // PUT /posts/{id}
    public ApiResponse<Void> updatePost(Long id, String newTitle, String newContent) {
        try{
            postService.updatePost(id, newTitle, newContent);
            return new ApiResponse<>(true, "게시글 수정 성공!", null);
        } catch(PostNotFoundException e){
            return new ApiResponse<>(false, "게시글 수정 실패 : " + e.getMessage(), null);
        }
    }

    // DELETE /posts/{id}
    public ApiResponse<Void> deletePost(Long id) {
        try {
            postService.deletePost(id);
            return new ApiResponse<>(true, "게시글 삭제 성공!", null);
        } catch (PostNotFoundException e){
            return new ApiResponse<>(false, "게시글 삭제 실패 : " + e.getMessage(), null);
        }
    }
}
