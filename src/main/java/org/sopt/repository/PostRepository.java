package org.sopt.repository;

import org.sopt.domain.Post;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository {

    Post save(Post post);

    List<Post> findAll();

    List<Post> findAllByPage(int page, int size);

    int countAll();

    Optional<Post> findById(Long id);

    void delete(Long id);

    Long generateId();
}
