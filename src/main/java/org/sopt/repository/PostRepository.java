package org.sopt.repository;

import org.sopt.domain.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PostRepository {
    private final List<Post> postList = new ArrayList<>();
    private Long nextId = 1L;

    public Post save(Post post) {
        postList.add(post);
        return post;
    }

    public List<Post> findAll(){
        return List.copyOf(postList);
    }

    public Optional<Post> findById(Long id){
        for(Post post : postList){
            if(post.getId().equals(id)){
                return Optional.of(post);
            }
        }
        return Optional.empty();
    }

    public void delete(Long id){
        postList.removeIf(post->post.getId().equals(id));
    }

    public Long generateId() {
        return nextId++;
    }
}
