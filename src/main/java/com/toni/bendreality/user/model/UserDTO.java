package com.toni.bendreality.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Valid
public record UserDTO(
    @JsonProperty(access= JsonProperty.Access.READ_ONLY)
    Integer id,

    @NotBlank
    String firstName,

    @NotBlank
    String lastName,

    @NotBlank
    String phoneNumber,

    @NotBlank
    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",message="Enter a valid email")
    String email,

    @NotBlank
    String password,

    @NotNull
    UserRole role
) {

}
