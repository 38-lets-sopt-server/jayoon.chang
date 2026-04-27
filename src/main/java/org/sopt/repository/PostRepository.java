package org.sopt.repository;

import org.sopt.domain.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
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
        return postList.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    public void delete(Long id){
        postList.removeIf(post->post.getId().equals(id));
    }

    public Long generateId() {
        return nextId++;
    }

    // pagination
    public List<Post> findAllByPage(int page, int size){
        int start = page * size;
        int end = Math.min(start + size, postList.size());

        if (start >= postList.size()){
            return new ArrayList<>();
        }

        return postList.subList(start, end);
    }

    public int countAll(){ return postList.size(); }
}
