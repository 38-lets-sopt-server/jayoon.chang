package org.sopt.service;

import org.sopt.domain.BoardType;
import org.sopt.domain.Post;
import org.sopt.domain.User;
import org.sopt.dto.request.CreatePostRequest;
import org.sopt.dto.request.UpdatePostRequest;
import org.sopt.dto.response.CreatePostResponse;
import org.sopt.dto.response.PostResponse;
import org.sopt.exception.PostNotFoundException;
import org.sopt.exception.UserNotFoundException;
import org.sopt.repository.PostRepository;
import org.sopt.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository,  UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    // CREATE
    @Transactional
    public CreatePostResponse createPost(CreatePostRequest request) {

        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new UserNotFoundException(request.userId()));

        Post post = new Post(request.title(), request.content(), user, request.boardType());
        postRepository.save(post);
        return new CreatePostResponse(post.getId(), "게시글 등록 완료!");
    }

    // READ - 전체
    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts(BoardType boardType, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        List<Post> posts;

        if (boardType == null) {
            posts = postRepository.findAll(pageable).getContent();
        } else {
            posts = postRepository.findByBoardType(boardType, pageable);
        }

        return posts.stream().map(PostResponse::from).toList();
    }

    // READ - 단건
    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id));

        return PostResponse.from(post);
    }

    // UPDATE
    @Transactional
    public void updatePost(Long id, UpdatePostRequest request) {

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id));

        post.update(request.title(), request.content());
    }

    // DELETE
    @Transactional
    public void deletePost(Long id) {

        postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id));

        postRepository.deleteById(id);
    }
}
