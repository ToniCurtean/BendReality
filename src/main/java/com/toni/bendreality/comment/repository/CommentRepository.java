package com.toni.bendreality.comment.repository;

import com.toni.bendreality.comment.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {

    Optional<List<Comment>> getCommentsByPostId(Integer postId);
}
