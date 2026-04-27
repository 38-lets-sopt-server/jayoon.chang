package org.sopt.repository;

import org.sopt.domain.BoardType;
import org.sopt.domain.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryPostRepository implements PostRepository {

    private final List<Post> postList = new ArrayList<>();
    private Long nextId = 1L;

    @Override
    public Post save(Post post) {
        postList.add(post);
        return post;
    }

    // pagination
    @Override
    public List<Post> findAllByPage(int page, int size){
        int start = page * size;
        int end = Math.min(start + size, postList.size());

        if (start >= postList.size()){
            return new ArrayList<>();
        }

        return postList.subList(start, end);
    }

    @Override
    public List<Post> findByBoardType(BoardType boardType){
        return postList.stream()
                .filter(p -> p.getBoardType() == boardType).toList();
    }

    @Override
    public List<Post> findByBoardTypeAndPage(BoardType boardType, int page, int size){
        List<Post> filtered = postList.stream()
                .filter(p -> p.getBoardType() == boardType).toList();

        int start = page * size;
        int end = Math.min(start + size, filtered.size());

        if (start >= filtered.size()){
            return new ArrayList<>();
        }

        return filtered.subList(start, end);
    }

    @Override
    public int countAll(){ return postList.size(); }

    @Override
    public Optional<Post> findById(Long id){
        return postList.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    @Override
    public void delete(Long id){
        postList.removeIf(post->post.getId().equals(id));
    }

    @Override
    public Long generateId() {
        return nextId++;
    }
}
