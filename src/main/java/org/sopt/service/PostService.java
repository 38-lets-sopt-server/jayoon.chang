package org.sopt.service;

import org.sopt.domain.Post;
import org.sopt.dto.request.CreatePostRequest;
import org.sopt.dto.request.UpdatePostRequest;
import org.sopt.dto.response.CreatePostResponse;
import org.sopt.dto.response.PostResponse;
import org.sopt.exception.PostNotFoundException;
import org.sopt.repository.PostRepository;
import org.sopt.validator.PostValidator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // CREATE
    public CreatePostResponse createPost(CreatePostRequest request) {
        PostValidator.validate(request.title(), request.content());
        LocalDateTime createdAt = LocalDateTime.now();
        Post post = new Post(postRepository.generateId(), request.title(), request.content(), request.author(), createdAt);
        postRepository.save(post);
        return new CreatePostResponse(post.getId(), "게시글 등록 완료!");
    }

    // READ - 전체
    public List<PostResponse> getAllPosts() {
        List<Post> posts = postRepository.findAll();

        return posts.stream().map(PostResponse::from).toList();
    }

    // READ - 단건
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id));

        return PostResponse.from(post);
    }

    // UPDATE
    public void updatePost(Long id, UpdatePostRequest request) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id));

        PostValidator.validate(request.title(), request.content());
        post.update(request.title(), request.content());
    }

    // DELETE
    public void deletePost(Long id) {
        postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id));

        postRepository.delete(id);
    }
}
