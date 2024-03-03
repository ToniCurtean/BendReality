package com.toni.bendreality.post.controller;

import com.toni.bendreality.exceptions.BusinessValidationException;
import com.toni.bendreality.post.model.PostDTO;
import com.toni.bendreality.post.model.PostViewDTO;
import com.toni.bendreality.post.service.PostService;
import com.toni.bendreality.security.check.RolesAllowed;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @RolesAllowed(value={"ADMIN","REGULAR"})
    @PostMapping
    public ResponseEntity<PostViewDTO> createPost(@Valid @RequestBody PostDTO post){
        try{
            PostViewDTO added=postService.addPost(post);
            return new ResponseEntity<>(added, HttpStatus.OK);
        }catch (RuntimeException ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RolesAllowed(value={"ADMIN","REGULAR"})
    @PutMapping("/{id}")
    public ResponseEntity<PostViewDTO> updatePost(@PathVariable @Positive Integer id,@RequestBody @Valid PostDTO postDTO){
        try{
            PostViewDTO updated=postService.updatePost(id,postDTO);
            return new ResponseEntity<>(updated,HttpStatus.OK);
        }catch (EntityNotFoundException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (RuntimeException ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RolesAllowed(value={"ADMIN","REGULAR"})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePostById(@PathVariable @Positive Integer id){
        try{
            postService.deletePostById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (BusinessValidationException ex){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }catch (RuntimeException ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RolesAllowed(value={"ADMIN","REGULAR"})
    @GetMapping("/{userId}")
    public ResponseEntity<List<PostViewDTO>> getPostsByUserId(@PathVariable @Positive Integer userId){
        try{
            List<PostViewDTO> posts=new ArrayList<>();
            postService.getPostsForUserId(userId).forEach(posts::add);
            if(posts.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(posts,HttpStatus.OK);
        }catch (RuntimeException ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RolesAllowed(value={"ADMIN","REGULAR"})
    @GetMapping
    public ResponseEntity<List<PostViewDTO>> getAllPosts(){
        try{
            List<PostViewDTO> posts=new ArrayList<>();
            postService.getAllPosts().forEach(posts::add);
            if(posts.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(posts,HttpStatus.OK);
        }catch (RuntimeException ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
