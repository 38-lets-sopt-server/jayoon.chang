package org.sopt.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.sopt.dto.response.BaseResponse;
import org.sopt.service.LikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "좋아요", description = "게시글 좋아요 관련 API")
@RestController
@RequestMapping("/likes")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @Operation(summary = "좋아요 추가", description = "게시글에 좋아요를 누른다")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "좋아요 성공"),
            @ApiResponse(responseCode = "400", description = "좋아요 중복")
    })
    @PostMapping
    public ResponseEntity<BaseResponse<Void>> likePost(
           @RequestParam Long userId,
           @RequestParam Long postId
    ){
        likeService.likePost(userId, postId);

        return ResponseEntity.ok(
                BaseResponse.success("좋아요 성공!", null)
        );
    }

    @Operation(summary = "좋아요 취소", description = "게시글에 좋아요를 취소한다")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "좋아요 취소 성공"),
            @ApiResponse(responseCode = "404", description = "좋아요 없음")
    })
    @DeleteMapping
    public ResponseEntity<BaseResponse<Void>> unlikePost(
            @RequestParam Long userId,
            @RequestParam Long postId
    ){
        likeService.unlikePost(userId, postId);

        return ResponseEntity.ok(
                BaseResponse.success("좋아요 취소 성공!", null)
        );
    }
}
