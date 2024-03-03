package com.toni.bendreality.post.repository;

import com.toni.bendreality.post.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    Optional<List<Post>> findPostsByUserId(Integer userId);
}
