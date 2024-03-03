package com.toni.bendreality.comment.service;


import com.toni.bendreality.comment.mapper.CommentMapper;
import com.toni.bendreality.comment.model.Comment;
import com.toni.bendreality.comment.model.CommentDTO;
import com.toni.bendreality.comment.model.CommentViewDTO;
import com.toni.bendreality.comment.repository.CommentRepository;
import com.toni.bendreality.post.model.Post;
import com.toni.bendreality.post.repository.PostRepository;
import com.toni.bendreality.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.toni.bendreality.security.ApplicationConfiguration.getCurrentUserId;

@Slf4j
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final CommentMapper commentMapper;

    @Transactional
    public CommentViewDTO addComment(CommentDTO commentDTO) {
        Optional<Post> postToFind = postRepository.findById(commentDTO.postId());
        if (postToFind.isPresent()) {
            Post post = postToFind.get();
            Comment toAdd = commentMapper.toEntity(commentDTO);
            toAdd.setPost(post);
            toAdd.setUser(userRepository.getReferenceById(getCurrentUserId()));
            return commentMapper.toViewDTO(commentRepository.save(toAdd));
        } else {
            throw new EntityNotFoundException("Couldn't find the specified post");
        }
    }

    @Transactional
    public CommentViewDTO updateComment(Integer id, String text) {
        Optional<Comment> toFind = commentRepository.findById(id);
        if (toFind.isPresent()) {
            Comment found = toFind.get();
            found.setText(text);
            return commentMapper.toViewDTO(commentRepository.save(found));
        } else {
            throw new EntityNotFoundException("Couldn't find the specified comment");
        }
    }

    @Transactional
    public void deleteComment(Integer id) {
        Optional<Comment> toFind = commentRepository.findById(id);
        if (toFind.isPresent()) {
            commentRepository.delete(toFind.get());
        } else {
            throw new EntityNotFoundException("Couldn't find the specified comment");
        }
    }

    public List<CommentViewDTO> getCommentsForPost(Integer postId) {
        Optional<List<Comment>> comments = commentRepository.getCommentsByPostId(postId);
        if (comments.isPresent()) {
            return commentMapper.map(comments.get());
        }
        return new ArrayList<>();
    }
}

