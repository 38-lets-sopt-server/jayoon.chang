package org.sopt.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.sopt.domain.BoardType;
import org.sopt.dto.request.CreatePostRequest;
import org.sopt.dto.request.UpdatePostRequest;
import org.sopt.dto.response.BaseResponse;
import org.sopt.dto.response.CreatePostResponse;
import org.sopt.dto.response.PostResponse;
import org.sopt.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "게시글", description = "게시글 CRUD 관련 API")
@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @Operation(summary = "게시글 생성", description = "새로운 게시글을 생성한다")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "생성 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청")
    })
    @PostMapping
    public ResponseEntity<BaseResponse<CreatePostResponse>> createPost(
           @Valid @RequestBody CreatePostRequest request
    ) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(BaseResponse.success("게시글 생성 성공!", postService.createPost(request)));
    }

    @Operation(summary = "게시글 전체 조회", description = "게시글 타입 + 페이징 기반으로 전체 게시글 목록을 조회한다")
    @ApiResponse(responseCode = "200", description = "조회 성공")
    @GetMapping
    public ResponseEntity<BaseResponse<List<PostResponse>>> getAllPosts(

            @Parameter(description = "게시판 타입 (FREE/HOT/SECRET)")
            @RequestParam(required = false)BoardType boardType,

            @Parameter(description = "페이지 번호")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "페이지 크기")
            @RequestParam(defaultValue = "10") int size
    ) {

        return ResponseEntity.ok(
                BaseResponse.success("게시글 전체 조회 성공!", postService.getAllPosts(boardType, page, size))
        );
    }

    @Operation(summary = "게시글 단건 조회", description = "id로 게시글을 조회한다")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "게시글 없음")
    })
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<PostResponse>> getPost(
            @Parameter(description = "게시글 ID")
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                BaseResponse.success("게시글 조회 성공!", postService.getPost(id))
        );
    }

    @Operation(summary = "게시글 수정", description = "게시글의 제목, 본문을 수정한다")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "수정 성공"),
            @ApiResponse(responseCode = "404", description = "게시글 없음")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> updatePost(
            @Parameter(description = "게시글 ID")
            @PathVariable Long id,

            @Valid @RequestBody UpdatePostRequest request) {

        postService.updatePost(id, request);

        return ResponseEntity.ok(
                BaseResponse.success("게시글 수정 성공!", null)
        );
    }

    @Operation(summary = "게시글 삭제", description = "게시글을 삭제한다")
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> deletePost(
            @Parameter(description = "게시글 ID")
            @PathVariable Long id
    ) {

        postService.deletePost(id);

        return ResponseEntity.noContent().build();
    }
}
