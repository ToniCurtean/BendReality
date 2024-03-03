package com.toni.bendreality.comment.mapper;

import com.toni.bendreality.comment.model.Comment;
import com.toni.bendreality.comment.model.CommentDTO;
import com.toni.bendreality.comment.model.CommentViewDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel="spring")
public abstract class CommentMapper {

    public abstract CommentDTO toDTO(Comment comment);

    @Mapping(source="userId",target="user.id")
    @Mapping(source="postId",target="post.id")
    public abstract Comment toEntity(CommentDTO commentDTO);

    public abstract CommentViewDTO toViewDTO(Comment comment);

    public abstract Comment toEntity(CommentViewDTO commentViewDTO);

    public abstract List<CommentViewDTO> map(List<Comment> comments);
}
