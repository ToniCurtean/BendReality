package com.toni.bendreality.post.service;

import com.toni.bendreality.post.mapper.PostMapper;
import com.toni.bendreality.post.model.Post;
import com.toni.bendreality.post.model.PostDTO;
import com.toni.bendreality.post.model.PostViewDTO;
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

@Service
@Slf4j
@AllArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostMapper postMapper;

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    @Transactional
    public PostViewDTO addPost(PostDTO post) {
        Post toSave = postMapper.toEntity(post);
        toSave.setUser(userRepository.getReferenceById(getCurrentUserId()));
        return postMapper.toViewDTO(postRepository.save(toSave));
    }

    @Transactional
    public PostViewDTO updatePost(Integer postId, PostDTO postDTO) {
        Optional<Post> toUpdate = postRepository.findById(postId);
        if (toUpdate.isPresent()) {
            Post found = toUpdate.get();
            found.setTitle(postDTO.title());
            found.setContent(postDTO.content());
            return postMapper.toViewDTO(postRepository.save(found));
        } else {
            throw new EntityNotFoundException("Couldn't find the wanted post");
        }
    }

    @Transactional
    public void deletePostById(Integer postId) {
        Optional<Post> toFind = postRepository.findById(postId);
        if (toFind.isPresent()) {
            postRepository.delete(toFind.get());
        } else {
            throw new EntityNotFoundException("Couldn't find the wanted post");
        }
    }

    public List<PostViewDTO> getPostsForUserId(Integer userId) {
        Optional<List<Post>> posts = postRepository.findPostsByUserId(userId);
        if (posts.isPresent()) {
            List<PostViewDTO> postViewDTOS = postMapper.map(posts.get());
            return postViewDTOS;
        }
        return new ArrayList<>();
    }

    public List<PostViewDTO> getAllPosts(){
        List<Post> posts=postRepository.findAll();
        return postMapper.map(posts);
    }

}
