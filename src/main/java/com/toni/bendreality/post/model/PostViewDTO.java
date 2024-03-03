package com.toni.bendreality.post.model;

import com.toni.bendreality.comment.model.CommentViewDTO;
import com.toni.bendreality.user.model.UserViewDTO;

import java.time.LocalDate;
import java.util.List;

public record PostViewDTO(
        Integer id,
        String title,
        String content,
        LocalDate createdAt,
        LocalDate updatedAt,
        List<CommentViewDTO> comments
) {

}
