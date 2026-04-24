package org.sopt.controller;

import org.sopt.dto.request.CreatePostRequest;
import org.sopt.dto.response.ApiResponse;
import org.sopt.dto.response.CreatePostResponse;
import org.sopt.dto.response.PostResponse;
import org.sopt.exception.PostNotFoundException;
import org.sopt.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

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

    @GetMapping
    public ApiResponse<List<PostResponse>> getAllPosts() {
        List<PostResponse> response = postService.getAllPosts();
        return new ApiResponse<>(true, "게시글 전체 조회 성공!", response);
    }

    @GetMapping("/{id}")
    public ApiResponse<PostResponse> getPost(@PathVariable Long id) {
        try{
            PostResponse response = postService.getPost(id);
            return new ApiResponse<>(true, "게시글 조회 성공!", response);
        } catch (PostNotFoundException e){
            return new ApiResponse<>(false, "게시글 조회 실패 : " + e.getMessage(), null);
        }
    }

    @PatchMapping("/{id}")
    public ApiResponse<Void> updatePost( @PathVariable Long id,
                                         @RequestBody CreatePostRequest request) {
        try{
            postService.updatePost(id, request.title(), request.content());
            return new ApiResponse<>(true, "게시글 수정 성공!", null);
        } catch(PostNotFoundException e){
            return new ApiResponse<>(false, "게시글 수정 실패 : " + e.getMessage(), null);
        }
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deletePost(@PathVariable Long id) {
        try {
            postService.deletePost(id);
            return new ApiResponse<>(true, "게시글 삭제 성공!", null);
        } catch (PostNotFoundException e){
            return new ApiResponse<>(false, "게시글 삭제 실패 : " + e.getMessage(), null);
        }
    }
}
