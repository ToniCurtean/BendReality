package com.toni.bendreality.comment.controller;

import com.toni.bendreality.comment.model.Comment;
import com.toni.bendreality.comment.model.CommentDTO;
import com.toni.bendreality.comment.model.CommentViewDTO;
import com.toni.bendreality.comment.service.CommentService;
import com.toni.bendreality.security.check.RolesAllowed;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/comments")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @RolesAllowed(value={"ADMIN","REGULAR"})
    @PostMapping
    public ResponseEntity<CommentViewDTO> addComment(@RequestBody @Valid CommentDTO commentDTO){
        try{
            CommentViewDTO added=commentService.addComment(commentDTO);
            return new ResponseEntity<>(added, HttpStatus.OK);
        }catch (EntityNotFoundException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(RuntimeException ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RolesAllowed(value={"ADMIN","REGULAR"})
    @PutMapping("/{id}")
    public ResponseEntity<CommentViewDTO> updateComment(@PathVariable @Positive Integer id,@RequestParam String text){
        try{
            CommentViewDTO updated=commentService.updateComment(id,text);
            return new ResponseEntity<>(updated,HttpStatus.OK);
        }catch(EntityNotFoundException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (RuntimeException ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RolesAllowed(value={"ADMIN","REGULAR"})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable @Positive Integer id){
        try{
            commentService.deleteComment(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (EntityNotFoundException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (RuntimeException ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RolesAllowed(value={"ADMIN","REGULAR"})
    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentViewDTO>> getCommentsForPost(@PathVariable @Positive Integer postId){
        try{
            List<CommentViewDTO> comments=new ArrayList<>();
            commentService.getCommentsForPost(postId).forEach(comments::add);
            if(comments.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(comments,HttpStatus.OK);
        }catch (RuntimeException ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }    
}
