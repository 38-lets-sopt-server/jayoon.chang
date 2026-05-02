package org.sopt.repository;

import org.sopt.domain.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    // 중복 체크
    boolean existsByUserIdAndPostId(Long userId, Long postId);

    // 좋아요 객체 가져오기
    Optional<Like> findByUserIdAndPostId(Long userId, Long postId);
}