package org.sopt.service;

import org.sopt.domain.Post;
import org.sopt.dto.request.CreatePostRequest;
import org.sopt.dto.response.CreatePostResponse;
import org.sopt.dto.response.PostResponse;
import org.sopt.exception.PostNotFoundException;
import org.sopt.repository.PostRepository;
import org.sopt.validator.PostValidator;

import java.time.LocalDateTime;
import java.util.List;

public class PostService {
    private final PostRepository postRepository = new PostRepository();

    // CREATE
    public CreatePostResponse createPost(CreatePostRequest request) {
        PostValidator.validate(request.title, request.content);
        LocalDateTime createdAt = LocalDateTime.now();
        Post post = new Post(postRepository.generateId(), request.title, request.content, request.author, createdAt);
        postRepository.save(post);
        return new CreatePostResponse(post.getId(), "게시글 등록 완료!");
    }

    // READ - 전체
    public List<PostResponse> getAllPosts() {
        List<Post> posts = postRepository.findAll();

        return posts.stream().map(post -> new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getAuthor(),
                post.getCreatedAt().toString()
                ))
                .toList();
    }

    // READ - 단건
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id));

        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getAuthor(),
                post.getCreatedAt().toString());
    }

    // UPDATE
    public void updatePost(Long id, String newTitle, String newContent) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id));

        PostValidator.validate(newTitle, newContent);
        post.update(newTitle, newContent);
    }

    // DELETE
    public void deletePost(Long id) {
        postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id));

        postRepository.delete(id);
    }
}
