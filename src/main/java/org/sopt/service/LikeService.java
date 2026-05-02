package org.sopt.service;

import org.sopt.domain.Like;
import org.sopt.domain.Post;
import org.sopt.domain.User;
import org.sopt.exception.DuplicateLikeException;
import org.sopt.exception.notfound.LikeNotFoundException;
import org.sopt.exception.notfound.PostNotFoundException;
import org.sopt.exception.notfound.UserNotFoundException;
import org.sopt.repository.LikeRepository;
import org.sopt.repository.PostRepository;
import org.sopt.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public LikeService(LikeRepository likeRepository, PostRepository postRepository, UserRepository userRepository) {
        this.likeRepository = likeRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void likePost(Long userId, Long postId){

        if (likeRepository.existsByUserIdAndPostId(userId, postId)){
            throw new DuplicateLikeException();
        }

        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        likeRepository.save(new Like(user, post));
    }

    @Transactional
    public void unlikePost(Long userId, Long postId){

        Like like = likeRepository.findByUserIdAndPostId(userId, postId)
                .orElseThrow(LikeNotFoundException::new);

        likeRepository.delete(like);
    }
}
