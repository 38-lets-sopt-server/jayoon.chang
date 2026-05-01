package org.sopt.controller;

import jakarta.validation.Valid;
import org.sopt.domain.BoardType;
import org.sopt.dto.request.CreatePostRequest;
import org.sopt.dto.request.UpdatePostRequest;
import org.sopt.dto.response.ApiResponse;
import org.sopt.dto.response.CreatePostResponse;
import org.sopt.dto.response.PostResponse;
import org.sopt.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ApiResponse<CreatePostResponse>> createPost(
           @Valid @RequestBody CreatePostRequest request
    ) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("게시글 생성 성공!", postService.createPost(request)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PostResponse>>> getAllPosts(
            @RequestParam(required = false)BoardType boardType,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        return ResponseEntity.ok(
           ApiResponse.success("게시글 전체 조회 성공!", postService.getAllPosts(boardType, page, size))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PostResponse>> getPost(@PathVariable Long id) {

        return ResponseEntity.ok(
            ApiResponse.success("게시글 조회 성공!", postService.getPost(id))
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> updatePost(@PathVariable Long id,
                                       @Valid @RequestBody UpdatePostRequest request) {

        postService.updatePost(id, request);

        return ResponseEntity.ok(
            ApiResponse.success("게시글 수정 성공!", null)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePost(@PathVariable Long id) {

        postService.deletePost(id);

        return ResponseEntity.noContent().build();
    }
}
