package com.toni.bendreality.post.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Valid
public record PostDTO(
        @JsonProperty(access=JsonProperty.Access.READ_ONLY)
        Integer id,

        @NotBlank
        String title,

        @NotBlank
        String content,

        @JsonProperty(access=JsonProperty.Access.READ_ONLY)
        Integer userId

) {

}
