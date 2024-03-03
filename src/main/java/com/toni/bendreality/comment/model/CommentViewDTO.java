package com.toni.bendreality.comment.model;

import com.toni.bendreality.post.model.PostViewDTO;
import com.toni.bendreality.user.model.UserViewDTO;

public record CommentViewDTO(
        Integer id,
        String text,
        UserViewDTO user
) {
}
