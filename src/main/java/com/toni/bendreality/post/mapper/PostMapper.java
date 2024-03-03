package com.toni.bendreality.post.mapper;

import com.toni.bendreality.post.model.Post;
import com.toni.bendreality.post.model.PostDTO;
import com.toni.bendreality.post.model.PostViewDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class PostMapper {
    public abstract PostDTO toDTO(Post post);

    @Mapping(source="userId",target="user.id")
    public abstract Post toEntity(PostDTO post);

    public abstract PostViewDTO toViewDTO(Post post);

    public abstract Post toEntity(PostViewDTO postViewDTO);

    public abstract List<PostViewDTO> map(List<Post> posts);
}
