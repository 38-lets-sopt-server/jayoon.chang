package org.sopt.service;

import org.sopt.domain.Post;
import org.sopt.dto.request.CreatePostRequest;
import org.sopt.dto.response.CreatePostResponse;
import org.sopt.repository.PostRepository;

import java.util.List;

public class PostService {
    private final PostRepository postRepository = new PostRepository();

    // CREATE
    public CreatePostResponse createPost(CreatePostRequest request) {
        if (request.title == null || request.title.isBlank()) {
            throw new IllegalArgumentException("제목은 필수입니다!");
        }
        if (request.content == null || request.content.isBlank()) {
            throw new IllegalArgumentException("내용은 필수입니다!");
        }
        String createdAt = java.time.LocalDateTime.now().toString();
        Post post = new Post(postRepository.generateId(), request.title, request.content, request.author, createdAt);
        postRepository.save(post);
        return new CreatePostResponse(post.getId(), "게시글 등록 완료!");
    }

    // READ - 전체
    public List<CreatePostResponse> getAllPosts() {
        List<Post> posts = postRepository.findAll();

        return posts.stream().map(post -> new CreatePostResponse(post.getId(), post.getInfo())).toList();
    }

    // READ - 단건
    public CreatePostResponse getPost(Long id) {
        Post post = postRepository.findById(id);

        if(post==null){
            throw new IllegalArgumentException("게시글 없음!");
        }

        return new CreatePostResponse(post.getId(), post.getInfo());
    }

    // UPDATE
    public void updatePost(Long id, String newTitle, String newContent) {
        Post post = postRepository.findById(id);

        if(post==null){
            throw new IllegalArgumentException("게시글 없음!");
        }

        post.update(newTitle, newContent);
    }

    // DELETE
    public void deletePost(Long id) {
        Post post = postRepository.findById(id);

        if(post==null){
            throw new IllegalArgumentException("게시글 없음!");
        }

        postRepository.delete(post);
    }
}