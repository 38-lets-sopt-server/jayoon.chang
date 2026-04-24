package org.sopt.controller;

import org.sopt.dto.request.CreatePostRequest;
import org.sopt.dto.request.UpdatePostRequest;
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
        return new ApiResponse<>(
                true,
                "게시글 생성 성공!",
                postService.createPost(request)
        );
    }

    @GetMapping
    public ApiResponse<List<PostResponse>> getAllPosts() {
        return new ApiResponse<>(
                true,
                "게시글 전체 조회 성공!",
                postService.getAllPosts()
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<PostResponse> getPost(@PathVariable Long id) {
        return new ApiResponse<>(
                true,
                "게시글 조회 성공!",
                postService.getPost(id)
        );
    }

    @PatchMapping("/{id}")
    public ApiResponse<Void> updatePost(@PathVariable Long id,
                                        @RequestBody UpdatePostRequest request) {
        postService.updatePost(id, request);

        return new ApiResponse<>(
                true,
                "게시글 수정 성공!",
                null
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);

        return new ApiResponse<>(
                true,
                "게시글 삭제 성공!",
                null
        );
    }
}
