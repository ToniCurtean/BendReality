package com.toni.bendreality.comment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

@Valid
public record CommentDTO(

        @JsonProperty(access=JsonProperty.Access.READ_ONLY)
        Integer id,

        @NotBlank
        String text,

        @NotNull
        @Positive
        Integer postId,


        @JsonProperty(access=JsonProperty.Access.READ_ONLY)
        Integer userId

) {
}
